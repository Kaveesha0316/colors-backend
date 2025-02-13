package com.example.colors.service;

import com.example.colors.dto.UserDTO;
import com.example.colors.entity.Status;
import com.example.colors.entity.User;
import com.example.colors.repo.StatusRepo;
import com.example.colors.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    public Boolean userSignUp(UserDTO userDTO){

        boolean status = false;

        if(!userRepo.existsByEmail(userDTO.getEmail())){

            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setMobile(userDTO.getMobile());

            Optional<Status> optional = statusRepo.findById(1);
            user.setStatus(optional.get());
            userRepo.save(user);


            status = true;
        }

        return status;
    }

    public User userLogin(UserDTO userDTO){

        User user = userRepo.findByEmailAndPasswordAndStatus_Id(userDTO.getEmail(), userDTO.getPassword(),1);

        return user;
    }



    public User profileUpdateUser(UserDTO userDTO){
        User user = userRepo.findById(userDTO.getId()).get();

        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setImage_path(userDTO.getImage_path());

       User updatedUser = userRepo.save(user);


        return updatedUser;
    }


}
