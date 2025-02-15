package com.example.colors.service;

import com.example.colors.entity.Cart;
import com.example.colors.entity.Product;
import com.example.colors.entity.User;
import com.example.colors.repo.CartRepo;
import com.example.colors.repo.ProductRepo;
import com.example.colors.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Service
@Transactional
public class CartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    public String addToCart(String qty, String product_id,String user_id) {

        Cart existCart = cartRepo.findCartByUser_IdAndProduct_Id(Integer.parseInt(user_id), Integer.parseInt(product_id));
        if (existCart == null) {
            Product product = productRepo.findById(Integer.parseInt(product_id)).get();
            User user = userRepo.findById(Integer.parseInt(user_id)).get();

            Cart cart = new Cart();
            cart.setQty(qty);
            cart.setProduct(product);
            cart.setUser(user);
            cart.setDate(new Date());

            cartRepo.save(cart);
            return "add";
        }else {
            existCart.setQty(String.valueOf(Integer.parseInt(existCart.getQty()) + Integer.parseInt(qty)));
            cartRepo.save(existCart);
            return "Updated";
        }




    }
}
