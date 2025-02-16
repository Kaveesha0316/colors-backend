package com.example.colors.controller;

import com.example.colors.dto.MyOrderDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "colors/order")
public class OrdersController {

    @Autowired
    OrdersService orderService;

    @GetMapping(value = "/load")
    public ResponseDTO loadOrders(@RequestParam ("userId") String  userId) {

        ResponseDTO responseDTO = new ResponseDTO();
         List<MyOrderDTO> myOrderDTOList = orderService.loadOrders(userId);

         if (myOrderDTOList != null) {
             responseDTO.setSuccess(true);
             responseDTO.setMessage("Success");
             responseDTO.setContent(myOrderDTOList);
         }
         return responseDTO;

    }
}
