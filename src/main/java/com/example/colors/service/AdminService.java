package com.example.colors.service;

import com.example.colors.dto.ProfitResponseDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.entity.*;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    AdminRepo adminRepo;

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

    public List<ProfitResponseDTO> LoadBarChart() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");

        List<Order_item> userOrders = orderItemRepo.findAll();

        Map<String, Integer> profitByDate = userOrders.stream()
                .collect(Collectors.groupingBy(
                        order -> sdf.format(order.getOrders().getDate_created()), // Group by order date
                        Collectors.summingInt(order -> (int) ((order.getProduct().getPrice()-(order.getProduct().getPrice()*90/100)) * Integer.parseInt(order.getQty()))) // Calculate profit
                ));

        // Convert map to list, sort by date ASC, and limit to the last 5 entries
        return profitByDate.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)) // Sort by date ASC
                .skip(Math.max(0, profitByDate.size() - 5)) // Keep only the last 5 entries
                .map(entry -> new ProfitResponseDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public String signin(String email,String password){

        if(email.equals("")){
            return "enter email";
        }else if(password.equals("")){
            return "enter password";
        }else {
            Admin admin = adminRepo.findByEmailAndPassword(email,password);
            if (admin != null){
                return admin.getName();
            }
        }

        return null;

    }

}
