package sk.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ReportDto {
    private int month;
    private int year;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance; //income-expense difference
    private Map<String, BigDecimal> expensesByCategory;
    
    //constructors
    public ReportDto(int month, int year, BigDecimal income, BigDecimal expense, BigDecimal balance, Map<String, BigDecimal> expensesByCategory) {
        this.month = month;
        this.year = year;
        this.totalIncome = income;
        this.totalExpense = expense;
        this.balance = balance;
        this.expensesByCategory = expensesByCategory;
    }
    
    //getters & setters
    
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public BigDecimal getTotalIncome() {
        return totalIncome;
    }
    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
    public BigDecimal getTotalExpense() {
        return totalExpense;
    }
    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public Map<String, BigDecimal> getExpensesByCategory() {
        return expensesByCategory;
    }
    public void setExpensesByCategory(Map<String, BigDecimal> expensesByCategory) {
        this.expensesByCategory = expensesByCategory;
    }

    
}
