package com.example.colors.repo;

import com.example.colors.entity.Product_image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<Product_image,Integer> {

    public Product_image findProduct_imageByProduct_id(Integer product_id);
}
