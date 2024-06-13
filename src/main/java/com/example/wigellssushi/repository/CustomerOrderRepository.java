package com.example.wigellssushi.repository;

import com.example.wigellssushi.entity.Booking;
import com.example.wigellssushi.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
