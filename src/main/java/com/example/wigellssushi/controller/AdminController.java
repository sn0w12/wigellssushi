package com.example.wigellssushi.controller;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.Customer;
import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.entity.Room;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.CustomerRepository;
import com.example.wigellssushi.repository.DishRepository;
import com.example.wigellssushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class AdminController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/add-dish")
    public Optional<Dish> addDish(@RequestBody Dish dish) {
        return Optional.of(dishRepository.save(dish));
    }

    @DeleteMapping("/deletedish/{id}")
    public ResponseEntity<Object> deleteDish(@PathVariable Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        dishRepository.delete(dish);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateroom/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            if (roomDetails.getName() != null) {
                room.setName(roomDetails.getName());
            }
            if (roomDetails.getMaxGuests() != 0) {
                room.setMaxGuests(roomDetails.getMaxGuests());
            }
            if (roomDetails.getEquipment() != null) {
                room.setEquipment(roomDetails.getEquipment());
            }

            Room updatedRoom = roomRepository.save(room);
            return ResponseEntity.ok(updatedRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
