package com.example.colors.service;

import com.example.colors.dto.ProductDTO;
import com.example.colors.entity.*;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductImageRepo productImageRepo;

    public boolean saveProduct(ProductDTO productDTO){

        Product product = new Product();
        product.setName(productDTO.getReg_product_name());
        product.setPrice(Double.parseDouble(productDTO.getReg_product_price()));
        product.setQty(Integer.parseInt(productDTO.getReg_product_qty()));
        product.setDescription(productDTO.getReg_product_description());
        product.setCreated_at(new Date());

       Category category = categoryRepo.findByName(productDTO.getReg_product_category());
       product.setCategory(category);

       Status status = statusRepo.findById(1).get();
       product.setStatus(status);

       User user = userRepo.findById(Integer.parseInt(productDTO.getReg_product_User_id())).get();
       product.setUser(user);

       Product returnproduct = productRepo.save(product);

        Product_image productImage = new Product_image();
        productImage.setProduct(returnproduct);
        productImage.setImage_path1(productDTO.getReg_product_image_path1());
        productImage.setImage_path2(productDTO.getReg_product_image_path2());
        productImage.setImage_path3(productDTO.getReg_product_image_path3());

        productImageRepo.save(productImage);

        return true;

    }
}
