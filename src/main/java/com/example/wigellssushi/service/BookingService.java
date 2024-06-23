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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final DishRepository dishRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private RestTemplate restTemplate;

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

        double totalDishPriceSek = 0;

        CustomerOrder customerOrder = booking.getCustomerOrder();
        if (customerOrder != null) {
            customerOrder.setCustomer(booking.getCustomer());
            List<Dish> dishes = new ArrayList<>();

            for (Dish dish : customerOrder.getDishes()) {
                if (dish.getId() != null) {
                    Dish existingDish = dishRepository.findById(dish.getId())
                            .orElseThrow(() -> new RuntimeException("Dish not found with id " + dish.getId()));
                    dishes.add(existingDish);
                    totalDishPriceSek += existingDish.getPriceSEK();
                } else if (dish.getName() != null) {
                    Dish existingDish = dishRepository.findByName(dish.getName())
                            .orElseThrow(() -> new RuntimeException("Dish not found with name " + dish.getName()));
                    dishes.add(existingDish);
                    totalDishPriceSek += existingDish.getPriceSEK();
                } else {
                    throw new RuntimeException("Dish must have either an ID or a name");
                }
            }

            customerOrder.setDishes(dishes);
        } else {
            throw new RuntimeException("Booking must have a CustomerOrder");
        }

        if (booking.getTotalPriceSEK() == 0.0) {
            booking.setTotalPriceSEK(totalDishPriceSek);
        }
        if (booking.getTotalPriceEuro() == 0.0) {
            booking.setTotalPriceEuro(restTemplate.getForObject("http://currency-service/api/v3/sektoeuro?amountInSEK=" + totalDishPriceSek, double.class));
        }

        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking with id: " + savedBooking.getId() + " successfully added.");
        return savedBooking;
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
                double totalPriceSEK = bookingDetails.getTotalPriceSEK();
                existingBooking.setTotalPriceSEK(totalPriceSEK);
                existingBooking.setTotalPriceEuro(restTemplate.getForObject("http://currency-service/api/v3/sektoeuro?amountInSEK=" + totalPriceSEK, double.class));
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

            Booking updatedBooking = bookingRepository.save(existingBooking);
            logger.info("Booking with id: " + updatedBooking.getId() + " successfully updated.");
            return updatedBooking;
        } else {
            throw new ResourceNotFoundException("Booking not found with id " + id);
        }
    }

    public List<Booking> getBookingsByUser(Customer customer) {
        return bookingRepository.findByCustomerId(customer.getId());
    }
}
