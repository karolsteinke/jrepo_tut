package sk;

import java.math.BigDecimal;

public class Car {
    private int year;
    private String model;
    private String manufacturer;
    private BigDecimal price;

    //constructors
    public Car(int year, String model, String manufacturer, BigDecimal price) {
        this.year = year;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }
    
    //getters/setters
    
    //toString()
    @Override
    public String toString() {
        return this.manufacturer + ", " + this.model + ", " + this.year + ", " + this.price;
    }
}
