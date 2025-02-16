package com.example.colors.controller;

import com.example.colors.dto.Cart_DTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.repo.CartRepo;
import com.example.colors.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "loadcart")
    public ResponseDTO loadCart(@RequestParam("userId") String userId) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<Cart_DTO> cartDtoList = CartService.findCartByUser_Id(Integer.parseInt(userId));

        responseDTO.setContent(cartDtoList);
        responseDTO.setSuccess(true);

        return responseDTO;
    }
    @GetMapping(value = "delete")
    public ResponseDTO deleteCart(@RequestParam("cartItemId") String cartItemId) {

        ResponseDTO responseDTO = new ResponseDTO();
       CartService.deleteCart(cartItemId);
           responseDTO.setSuccess(true);
           responseDTO.setMessage("Success");



        return responseDTO;
    }

    @GetMapping(value = "update")
    public ResponseDTO UpdateToCart(@RequestParam("qty") String qty,@RequestParam("product_id") String product_id,@RequestParam("user_id") String user_id,@RequestParam("status") String status) {

        ResponseDTO responseDTO = new ResponseDTO();
        String text = CartService.updateToCart(qty,product_id,user_id,status);
        responseDTO.setMessage(text);
        responseDTO.setSuccess(true);

        return responseDTO;
    }
}
