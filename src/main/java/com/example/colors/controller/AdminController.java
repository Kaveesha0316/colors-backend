package com.example.colors.controller;

import com.example.colors.entity.Order_item;
import com.example.colors.entity.Product;
import com.example.colors.entity.User;
import com.example.colors.service.AdminService;
import com.example.colors.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "colors/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/loadusers")
    public  List<User> LoadUsers(){
        List<User> userList = adminService.loadusers();
        return  userList;
    }

    @RequestMapping(value = "/chngsts")
    public void changests(@RequestParam("id") String id){
        adminService.chngsts(id);
    }

    @GetMapping(value = "/loadproducts")
    public  List<Product> Loadproducts(){
        List<Product> productList = adminService.loadproducts();
        return  productList;
    }

    @RequestMapping(value = "/chngproductsts")
    public void changeproductsts(@RequestParam("id") String id){
        adminService.chngproductsts(id);
    }

    @GetMapping(value = "/loadorders")
    public  List<Order_item> Loadorders(){
        List<Order_item> orderItems = adminService.loadorders();
        return  orderItems;
    }

    @GetMapping(value = "/totalerning")
    public  String totalErning(){
       String total = adminService.totalErning();
        return  total;
    }

    @GetMapping(value = "/totalsales")
    public  String totalSales(){
        String total = adminService.totalsales();
        return  total;
    }
    @GetMapping(value = "/totalusers")
    public  String totalUsers(){
        String total = adminService.totaluser();
        return  total;
    }
    @GetMapping(value = "/totalcart")
    public  String totalCarts(){
        String total = adminService.totalCart();
        return  total;
    }
}
