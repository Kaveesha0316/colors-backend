package com.example.colors.repo;

import com.example.colors.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    public Cart findCartByUser_IdAndProduct_Id(Integer user_id, Integer product_id);
    public List<Cart> findCartByUser_IdOrderByDateDesc(Integer user_id);
}
