package com.example.wigellssushi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sushi_dishes")
@JsonIgnoreProperties({"customerOrders"})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "price_sek")
    private double priceSEK;

    @Column(name = "price_euro")
    private double priceEuro;

    @ManyToMany(mappedBy = "dishes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<CustomerOrder> customerOrders;

    public Dish() {
    }

    public Dish(Long id, String name, double priceSEK, double priceEuro, List<CustomerOrder> customerOrders) {
        this.id = id;
        this.name = name;
        this.priceSEK = priceSEK;
        this.priceEuro = priceEuro;
        this.customerOrders = customerOrders;
    }

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

    public double getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(double priceSEK) { this.priceSEK = priceSEK; }

    public double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}
