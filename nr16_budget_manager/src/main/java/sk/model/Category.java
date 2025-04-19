package sk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity = object will be mapped to the db table
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@Enumerated(EnumType.STRING) = save enum in the db *as string* (otherwise uses default = ordinal)
    @Enumerated(EnumType.STRING)
    private TransactionType type; //INCOME or EXPENSE

    //constructor
    public Category() {}
    
    public Category(String name, TransactionType type) {
        this.name = name;
        this.type = type;
    }
    
    //getters & setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    
}
