package com.example.colors.repo;

import com.example.colors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);
    public User findByEmailAndPasswordAndStatus_Id(String email, String password,Integer status);

}
