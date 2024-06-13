package com.example.wigellssushi.controller;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.Customer;
import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.entity.Room;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.BookingRepository;
import com.example.wigellssushi.repository.DishRepository;
import com.example.wigellssushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class CustomerController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/sushis")
    public List<Dish> getAllPosts() {
        return dishRepository.findAll();
    }

    @PostMapping("/bookroom")
    public Optional<Booking> bookRoom(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return Optional.of(savedBooking);
    }

    @PutMapping("/updatebooking/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();

            // Update fields if they are not null
            if (bookingDetails.getCustomer() != null) {
                existingBooking.setCustomer(bookingDetails.getCustomer());
            }
            if (bookingDetails.getNumberOfGuests() != 0) {
                existingBooking.setNumberOfGuests(bookingDetails.getNumberOfGuests());
            }
            if (bookingDetails.getDate() != null) {
                existingBooking.setDate(bookingDetails.getDate());
            }
            if (bookingDetails.getTotalPriceSEK() != 0) {
                existingBooking.setTotalPriceSEK(bookingDetails.getTotalPriceSEK());
            }
            if (bookingDetails.getTotalPriceEuro() != 0) {
                existingBooking.setTotalPriceEuro(bookingDetails.getTotalPriceEuro());
            }
            if (bookingDetails.getRoom() != null) {
                existingBooking.setRoom(bookingDetails.getRoom());
            }
            if (bookingDetails.getDishes() != null) {
                existingBooking.setDishes(bookingDetails.getDishes());
            }

            return bookingRepository.save(existingBooking);
        } else {
            throw new ResourceNotFoundException("Booking not found with id " + id);
        }
    }

    @GetMapping("/mybookings")
    public List<Booking> getBookingsByUser(@RequestBody Customer customerDetails) {
        return bookingRepository.findByCustomerId(customerDetails.getId());
    }
}
