package com.example.colors.repo;

import com.example.colors.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

    public Category findByName(String name);
}
