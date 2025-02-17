package com.example.colors.repo;

import com.example.colors.entity.Order_item;
import com.example.colors.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    public List<Orders> findOrdersByUserIdOrderByIdDesc(int userId);
    public List<Orders> findByUser_IdNotOrderByIdDesc(int userId);

    @Query("SELECT o FROM Order_item o WHERE o.product.user.id = :userId")
    List<Order_item> findOrdersByUser_Id(int userId);
}
