package com.example.wigellssushi.controller;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.Customer;
import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.service.BookingService;
import com.example.wigellssushi.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class CustomerController {
    private final BookingService bookingService;
    private final DishService dishService;

    @Autowired
    public CustomerController(BookingService bookingService, DishService dishService) {
        this.bookingService = bookingService;
        this.dishService = dishService;
    }

    @GetMapping("/sushis")
    @PreAuthorize("hasRole('USER')")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @PostMapping("/bookroom")
    @PreAuthorize("hasRole('USER')")
    public Booking bookRoom(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @PutMapping("/updatebooking/{id}")
    @PreAuthorize("hasRole('USER')")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        return bookingService.updateBooking(id, bookingDetails);
    }

    @GetMapping("/mybookings")
    @PreAuthorize("hasRole('USER')")
    public List<Booking> getBookingsByUser(@RequestBody Customer customerDetails) {
        return bookingService.getBookingsByUser(customerDetails);
    }
}
