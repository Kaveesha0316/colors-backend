package com.example.colors.service;

import com.example.colors.entity.Product;
import com.example.colors.entity.User;
import com.example.colors.repo.ProductRepo;
import com.example.colors.repo.StatusRepo;
import com.example.colors.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HomeService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    public  List<Product> SearchAllProducts(String name) {
       List<Product> productList = productRepo.findProductByStatus_NameAndNameStartingWith("active", name);
       return productList;
    }

}
