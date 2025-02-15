package com.example.colors.repo;

import com.example.colors.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    public Cart findCartByUser_IdAndProduct_Id(Integer user_id, Integer product_id);
}
