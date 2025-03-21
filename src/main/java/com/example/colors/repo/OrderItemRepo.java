package com.example.colors.repo;

import com.example.colors.entity.Order_item;
import com.example.colors.entity.Orders;
import com.example.colors.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<Order_item,Integer> {


    List<Order_item> findOrder_itemsByProductAndProduct_NameStartingWith(@Param("product") Product product,String name);
    List<Order_item> findOrder_itemsByOrdersAndProduct_NameStartingWith(Orders order, String name);
    List<Order_item> findAllByOrderByIdDesc();

}
