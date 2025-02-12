package com.example.colors.repo;

import com.example.colors.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    public List<Product> findProductByStatus_NameAndNameStartingWith(String status, String name);
}
