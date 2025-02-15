package com.example.colors.repo;

import com.example.colors.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Orders, Integer> {
}
