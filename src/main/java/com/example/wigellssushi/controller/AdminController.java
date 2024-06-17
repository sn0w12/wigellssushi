package com.example.wigellssushi.controller;

import com.example.wigellssushi.entity.Customer;
import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.entity.Room;
import com.example.wigellssushi.service.CustomerService;
import com.example.wigellssushi.service.DishService;
import com.example.wigellssushi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class AdminController {
    private final DishService dishService;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Autowired
    public AdminController(DishService dishService, CustomerService customerService, RoomService roomService) {
        this.dishService = dishService;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/add-dish")
    public Dish addDish(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @DeleteMapping("/deletedish/{id}")
    public String deleteDish(@PathVariable Long id) {
        return dishService.deleteDishById(id);
    }

    @PutMapping("/updateroom/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        return roomService.updateRoom(id, roomDetails);
    }
}
