package com.example.colors.service;

import com.example.colors.entity.*;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrdersRepo ordersRepo;

    public List<User> loadusers(){

       List<User> uerlist = userRepo.findAllByOrderByIdDesc();
       for (User user:uerlist){
           user.setPassword("");
       }
        return uerlist;
    }

    public void chngsts(String id){
        User user = userRepo.findById(Integer.parseInt(id)).get();

        if (user.getStatus().getName().equals("active")){
            Status status = statusRepo.findById(2).get();
            user.setStatus(status);
        }else {
            Status status = statusRepo.findById(1).get();
            user.setStatus(status);
        }
        userRepo.save(user);
    }

    public List<Product> loadproducts(){

        List<Product> productList = productRepo.findAllByOrderByIdDesc();

        return productList;
    }

    public void chngproductsts(String id){
        Product product = productRepo.findById(Integer.parseInt(id)).get();

        if (product.getStatus().getName().equals("active")){
            Status status = statusRepo.findById(2).get();
            product.setStatus(status);
        }else {
            Status status = statusRepo.findById(1).get();
            product.setStatus(status);
        }
        productRepo.save(product);
    }

    public List<Order_item> loadorders(){

        List<Order_item> orderItems = orderItemRepo.findAllByOrderByIdDesc();

        return orderItems;
    }

    public String totalErning(){
        List<Orders> ordersList = ordersRepo.findAll();

        Double total = 0.0;

        for (Orders orders:ordersList){
            total += orders.getTotal_price();
        }

        Integer finaltotal = (int) (total-(total*90/100));

        return String.valueOf(finaltotal);

    }

    public String totalsales(){
        List<Order_item> orderItemList = orderItemRepo.findAll();

        Integer total = 0;

        for (Order_item orderItem:orderItemList){
            total += Integer.parseInt(orderItem.getQty());
        }

        return String.valueOf(total);

    }

    public String totaluser(){
        long qty = userRepo.count();

        return String.valueOf(qty);

    }

    public String totalCart(){
        List<Cart> cartList = cartRepo.findAll();

        Integer total = 0;

        for (Cart cart:cartList){
            total += Integer.parseInt(cart.getQty());
        }

        return String.valueOf(total);

    }

}
