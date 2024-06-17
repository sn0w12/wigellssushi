package com.example.wigellssushi.entity;

import com.example.wigellssushi.util.CurrencyConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"customer", "room"})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-booking")
    private Customer customer;

    private int numberOfGuests;
    private Date date;
    @Column(name = "total_price_sek")
    private double totalPriceSEK;
    @Column(name = "total_price_euro")
    private double totalPriceEuro;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "booking")
    @JsonManagedReference("booking-dish")
    private List<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
        this.totalPriceEuro = CurrencyConverter.convertSEKToEuro(totalPriceSEK);
    }

    public double getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(double totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}

