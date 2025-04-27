package sk.service;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sk.dto.ReportDto;
import sk.model.Transaction;
import sk.model.TransactionType;
import sk.repository.TransactionRepository;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;

    //def.: constructor DI
    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    //def.: create new ReportDto for given time (use transactionRepository for transactions list)
    public ReportDto generateMonthlyReport(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        
        List<Transaction> transactions = transactionRepository.findByDateBetween(start, end); //all transactions matching given time period
        BigDecimal income = getIncome(transactions);
        BigDecimal expense = getExpense(transactions);
        Map<String, BigDecimal> expensesByCategory = getExpensesByCategory(transactions);
        
        return new ReportDto(month, year, income, expense, income.subtract(expense), expensesByCategory);
    }

    //def.: create new ReportDto for given time (use transactionRepository for transactions list)
    public ReportDto generateYearlyReport(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        List<Transaction> transactions = transactionRepository.findByDateBetween(start, end); //all transactions matching given time period
        BigDecimal income = getIncome(transactions);
        BigDecimal expense = getExpense(transactions);
        Map<String, BigDecimal> expensesByCategory = getExpensesByCategory(transactions);
        
        return new ReportDto(1, year, income, expense, income.subtract(expense), expensesByCategory);
    }

    //def.: save report data to provided PrintWriter, which is part of HTTP response (=CSV attachment)
    public void writeCsvReport(ReportDto report, PrintWriter writer) {
        writer.println("Report for," +  report.getMonth() + "/" + report.getYear());
        writer.println();
        writer.println("Total Income," + report.getTotalIncome());
        writer.println("Total Expense," + report.getTotalExpense());
        writer.println("Balance," + report.getBalance());
        writer.println();
        writer.println("Expenses by Category:");
        writer.println("Category,Amount");

        report.getExpensesByCategory().forEach((category,amount) -> {
            writer.println(category + "," + amount);
        });
    }

    private BigDecimal getIncome(List<Transaction> transactions) {
        BigDecimal income = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.INCOME) //filter income transactions
            .map(Transaction::getAmount) //convert transaction (object) --> amount (BigDecimal)
            .reduce(BigDecimal.ZERO, BigDecimal::add); //sum all
        return income;
    }

    private BigDecimal getExpense(List<Transaction> transactions) {
        BigDecimal expense = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.EXPENSE) //filter expense transactions
            .map(Transaction::getAmount) //convert transaction (object) --> amount (BigDecimal)
            .reduce(BigDecimal.ZERO, BigDecimal::add); //sum all
        return expense;
    }

    private Map<String,BigDecimal> getExpensesByCategory(List<Transaction> transactions) {
        Map<String, BigDecimal> expensesByCategory = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.EXPENSE) //filter expense transactions
            .collect(Collectors.groupingBy( //collect data to Map<String, BigDecimal> structure
                tx -> tx.getCategory().getName(), //key (String) = category name
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add) //value (BigDecimal) = sum all, which match category
            ));
        return expensesByCategory;
    }
}
