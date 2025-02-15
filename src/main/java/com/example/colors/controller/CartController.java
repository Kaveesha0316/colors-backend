package com.example.colors.controller;

import com.example.colors.dto.ResponseDTO;
import com.example.colors.repo.CartRepo;
import com.example.colors.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "colors/cart")
public class CartController {

    @Autowired
    CartService CartService;

    @GetMapping(value = "add")
    public ResponseDTO addToCart(@RequestParam("qty") String qty,@RequestParam("product_id") String product_id,@RequestParam("user_id") String user_id) {

        ResponseDTO responseDTO = new ResponseDTO();
       String text = CartService.addToCart(qty,product_id,user_id);
            responseDTO.setMessage(text);
            responseDTO.setSuccess(true);

        return responseDTO;
    }
}
