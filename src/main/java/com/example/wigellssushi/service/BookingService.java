package com.example.wigellssushi.service;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.Customer;
import com.example.wigellssushi.entity.CustomerOrder;
import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.entity.Room;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.BookingRepository;
import com.example.wigellssushi.repository.DishRepository;
import com.example.wigellssushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final DishRepository dishRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, DishRepository dishRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.dishRepository = dishRepository;
    }

    public Booking addBooking(Booking booking) {
        if (booking.getRoom() != null) {
            Room room = roomRepository.findById(booking.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id " + booking.getRoom().getId()));
            booking.setRoom(room);
        }

        double totalPriceSek = 0;

        CustomerOrder customerOrder = booking.getCustomerOrder();
        if (customerOrder != null) {
            customerOrder.setCustomer(booking.getCustomer());
            List<Dish> dishes = new ArrayList<>();

            for (Dish dish : customerOrder.getDishes()) {
                if (dish.getId() != null) {
                    Dish existingDish = dishRepository.findById(dish.getId())
                            .orElseThrow(() -> new RuntimeException("Dish not found with id " + dish.getId()));
                    dishes.add(existingDish);
                    totalPriceSek += existingDish.getPriceSEK();
                } else if (dish.getName() != null) {
                    Dish existingDish = dishRepository.findByName(dish.getName())
                            .orElseThrow(() -> new RuntimeException("Dish not found with name " + dish.getName()));
                    dishes.add(existingDish);
                    totalPriceSek += existingDish.getPriceSEK();
                } else {
                    throw new RuntimeException("Dish must have either an ID or a name");
                }
            }

            customerOrder.setDishes(dishes);
        } else {
            throw new RuntimeException("Booking must have a CustomerOrder");
        }

        if (booking.getCustomerOrder().getTotalPriceSEK() == 0.0) {
            booking.getCustomerOrder().setTotalPriceSEK(totalPriceSek);
        }

        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
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
            if (bookingDetails.getCustomerOrder() != null) {
                existingBooking.setCustomerOrder(bookingDetails.getCustomerOrder());
            }

            return bookingRepository.save(existingBooking);
        } else {
            throw new ResourceNotFoundException("Booking not found with id " + id);
        }
    }

    public List<Booking> getBookingsByUser(Customer customer) {
        return bookingRepository.findByCustomerId(customer.getId());
    }
}
