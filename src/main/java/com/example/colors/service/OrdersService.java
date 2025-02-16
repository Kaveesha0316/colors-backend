package com.example.colors.service;

import com.example.colors.dto.MyOrderDTO;
import com.example.colors.entity.Order_item;
import com.example.colors.entity.Orders;
import com.example.colors.entity.Product_image;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    OrdersRepo orderRepo;

    @Autowired
    ProductImageRepo productImageRepo;

    @Autowired
    OrderStatusRepo orderStatusRepo;

    public List<MyOrderDTO> loadOrders(String userId) {

        List<Orders> orders = orderRepo.findOrdersByUserIdOrderByIdDesc(Integer.parseInt(userId));
        if (orders != null) {

        List<MyOrderDTO> myOrderDTOS = new ArrayList<>();
        for (Orders order : orders) {

           List<Order_item> orderItemList = orderItemRepo.findOrder_itemsByOrders(order);
           for (Order_item orderItem : orderItemList) {

               MyOrderDTO myOrderDTO = new MyOrderDTO();

               myOrderDTO.setName(orderItem.getProduct().getName());
               myOrderDTO.setQty(orderItem.getQty());
               myOrderDTO.setPrice(String.valueOf(orderItem.getPrice()));
               myOrderDTO.setDate(String.valueOf(order.getDate_created()));
               myOrderDTO.setStatus(orderItem.getOrder_status().getName());

               Product_image productImage = productImageRepo.findProduct_imageByProduct_id(orderItem.getProduct().getId());
               myOrderDTO.setImageUrl(productImage.getImage_path1());
               myOrderDTOS.add(myOrderDTO);
           }
        }
            return myOrderDTOS;
        }


        return null;
    }
}
