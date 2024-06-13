package com.example.wigellssushi.repository;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
