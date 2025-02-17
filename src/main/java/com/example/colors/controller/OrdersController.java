package com.example.colors.controller;

import com.example.colors.dto.CustomerOrderDTO;
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

    @GetMapping(value = "/loadcus")
    public ResponseDTO loadCustommerOrders(@RequestParam ("userId") String  userId,@RequestParam ("searchText") String  searchText) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<CustomerOrderDTO> customerOrderDTOS = orderService.loadCustomerOrders(userId,searchText);

        if (customerOrderDTOS != null) {
            responseDTO.setSuccess(true);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerOrderDTOS);
        }
        return responseDTO;

    }

    @GetMapping(value = "/changests")
    public ResponseDTO UpdateOrdersStatus(@RequestParam ("orderId") String  orderId,@RequestParam ("status") String  status) {

        ResponseDTO responseDTO = new ResponseDTO();
        String sts = orderService.updateStatus(orderId,status);


            responseDTO.setSuccess(true);
            responseDTO.setMessage(sts);
        System.out.println(sts);
        return responseDTO;

    }
}
