package sk.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import sk.model.TransactionType;

public class TransactionDto {
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type; //INCOME or EXPENSE
    private Long categoryId;
    private String description;
    
    //getters & setters
    
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }    
}
