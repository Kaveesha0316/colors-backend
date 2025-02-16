package com.example.colors.repo;

import com.example.colors.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    public List<Orders> findOrdersByUserIdOrderByIdDesc(int userId);
}
