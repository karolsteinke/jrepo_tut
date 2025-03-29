package sk.bookings.models;

import jakarta.validation.constraints.Min;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Apartment {
    //@Id = marks 'id' as primary key;
    //@GeneratedValue = auto-generate this value
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Min(value = 1, message = "Value must be a positive integer")
    private int capacity;

    private String name;

    @OneToMany(mappedBy = "apartment")
    private List<Booking> bookings;

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
