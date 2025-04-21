package sk.service;

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

    //def.: create new ReportDto for selected time (use transactionRepository for data)
    public ReportDto generateMonthlyReport(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Transaction> transactions = transactionRepository.findByDateBetween(start, end); //all transactions matching selected time period

        BigDecimal income = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.INCOME) //filter income transactions
            .map(Transaction::getAmount) //convert transaction (object) --> amount (BigDecimal)
            .reduce(BigDecimal.ZERO, BigDecimal::add); //sum all

        BigDecimal expense = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.EXPENSE) //filter expense transactions
            .map(Transaction::getAmount) //convert transaction (object) --> amount (BigDecimal)
            .reduce(BigDecimal.ZERO, BigDecimal::add); //sum all

        Map<String, BigDecimal> expensesByCategory = transactions.stream()
            .filter(tx -> tx.getType() == TransactionType.EXPENSE) //filter expense transactions
            .collect(Collectors.groupingBy( //collect data to Map<String, BigDecimal> structure
                tx -> tx.getCategory().getName(), //key = category name (=String)
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add) //value = sum all, which match category (=BigDecimal)
            ));
        
        return new ReportDto(month, year, income, expense, income.subtract(expense), expensesByCategory);
    }

    public void generateYearlyReport(int year) {
        //work in progress
    }

    public void getSummaryPerCategory(int month, int year) {
        //work in progress
    }

    public void calculateBalance(int month, int year) {
        //work in progress
    }
}
