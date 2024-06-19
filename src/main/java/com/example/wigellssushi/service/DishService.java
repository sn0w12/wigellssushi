package com.example.wigellssushi.service;

import com.example.wigellssushi.entity.Dish;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private static final Logger logger = LoggerFactory.getLogger(DishService.class);

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish addDish(Dish dish) {
        Dish savedDish = dishRepository.save(dish);
        logger.info("Dish with id: " + savedDish.getId() + " successfully added.");
        return savedDish;
    }

    public String deleteDishById(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        dishRepository.delete(dish);
        logger.info("Dish with id: " + dish.getId() + " successfully deleted.");
        return "Dish " + dish.getId() + " deleted.";
    }
}
