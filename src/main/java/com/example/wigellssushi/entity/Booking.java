package com.example.wigellssushi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sushi_bookings")
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    public Booking() {
    }

    public Booking(Long id, Customer customer, int numberOfGuests, Date date, double totalPriceSEK, double totalPriceEuro, Room room, CustomerOrder customerOrder) {
        this.id = id;
        this.customer = customer;
        this.numberOfGuests = numberOfGuests;
        this.date = date;
        this.totalPriceSEK = totalPriceSEK;
        this.totalPriceEuro = totalPriceEuro;
        this.room = room;
        this.customerOrder = customerOrder;
    }

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

    public void setTotalPriceSEK(double totalPriceSEK) { this.totalPriceSEK = totalPriceSEK; }

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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
