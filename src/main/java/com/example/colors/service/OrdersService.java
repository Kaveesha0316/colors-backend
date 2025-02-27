package com.example.colors.service;

import com.example.colors.dto.CustomerOrderDTO;
import com.example.colors.dto.MyOrderDTO;
import com.example.colors.entity.*;
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

           List<Order_item> orderItemList = orderItemRepo.findOrder_itemsByOrdersAndProduct_NameStartingWith(order,"");
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

    public List<CustomerOrderDTO> loadCustomerOrders(String userId, String searchText) {

        List<Product> productList = productRepo.findProductsByUser_IdAndNameStartingWithOrderByIdDesc(Integer.parseInt(userId),"");

        List<CustomerOrderDTO> customerOrderDTOS = new ArrayList<>();
        if (productList != null){

            for (Product product: productList){

                List<Order_item> orderItems = orderItemRepo.findOrder_itemsByProductAndProduct_NameStartingWith(product,searchText);

                for (Order_item orderItem:orderItems){
                    CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

                    customerOrderDTO.setName(orderItem.getProduct().getName());
                    customerOrderDTO.setOrderId(orderItem.getId());
                    customerOrderDTO.setQty(orderItem.getQty());
                    customerOrderDTO.setPrice(String.valueOf(orderItem.getPrice()));
                    customerOrderDTO.setDate(String.valueOf(orderItem.getOrders().getDate_created()));
                    customerOrderDTO.setStatus(orderItem.getOrder_status().getName());

                    Product_image productImage = productImageRepo.findProduct_imageByProduct_id(orderItem.getProduct().getId());
                    customerOrderDTO.setImageUrl(productImage.getImage_path1());
                    customerOrderDTO.setCname(orderItem.getOrders().getUser().getName());
                    customerOrderDTO.setCmobile(orderItem.getOrders().getUser().getMobile());
                    customerOrderDTO.setCcity(orderItem.getOrders().getUser().getCity());
                    customerOrderDTO.setCaddress(orderItem.getOrders().getUser().getAddress());
                    customerOrderDTO.setClocation(orderItem.getOrders().getUser().getLocation());
                    customerOrderDTOS.add(customerOrderDTO);
                }
            }
        }
        return customerOrderDTOS;

    }

    public String updateStatus(String orderId, String status) {

        Order_item orderItem = orderItemRepo.findById(Integer.parseInt(orderId)).get();

        if (status.equals("Processing")){
            Order_status  orderStatus = orderStatusRepo.findById(2).get();
            orderItem.setOrder_status(orderStatus);
            return  "Packed";
        }else if(status.equals("Packed")){
            Order_status  orderStatus = orderStatusRepo.findById(3).get();
            orderItem.setOrder_status(orderStatus);
            return  "Dispatch";
        }else if(status.equals("Dispatch")){
            Order_status  orderStatus = orderStatusRepo.findById(4).get();
            orderItem.setOrder_status(orderStatus);
            return  "Deleverd";
        }else {
            return  "Deleverd";
        }



    }

}
