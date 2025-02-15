package com.example.colors.repo;

import com.example.colors.entity.Order_item;
import com.example.colors.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<Order_item,Integer> {


    List<Order_item> findOrder_itemsByProduct(@Param("product") Product product);
}
