package com.example.colors.repo;

import com.example.colors.entity.Order_status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepo extends JpaRepository<Order_status,Integer>{
}
