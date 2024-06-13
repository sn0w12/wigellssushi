package com.example.wigellssushi.repository;

import com.example.wigellssushi.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
