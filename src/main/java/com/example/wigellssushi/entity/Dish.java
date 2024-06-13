package com.example.wigellssushi.entity;

import com.example.wigellssushi.util.CurrencyConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"customerOrder", "booking"})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "price_sek")
    private double priceSEK;

    @Column(name = "price_euro")
    private double priceEuro;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference("order-dish")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference("booking-dish")
    private Booking booking;

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

    public void setPriceSEK(double priceSEK) {
        this.priceSEK = priceSEK;
        this.priceEuro = CurrencyConverter.convertSEKToEuro(priceSEK);
    }

    public double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
