package com.example.colors.repo;

import com.example.colors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);
    List<User> findAllByOrderByIdDesc();
    public User findByEmailAndPasswordAndStatus_Id(String email, String password,Integer status);

}
