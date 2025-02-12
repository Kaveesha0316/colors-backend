package com.example.colors.repo;

import com.example.colors.entity.Order_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<Order_item,Integer> {
}
