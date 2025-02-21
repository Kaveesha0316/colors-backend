package com.example.colors.repo;

import com.example.colors.entity.Admin;
import com.example.colors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    public Admin findByEmailAndPassword(String email, String password);
}
