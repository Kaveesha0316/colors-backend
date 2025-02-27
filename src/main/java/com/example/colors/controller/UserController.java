package com.example.colors.controller;

import com.example.colors.dto.ResponseDTO;
import com.example.colors.dto.UserDTO;
import com.example.colors.entity.User;
import com.example.colors.model.Validation;
import com.example.colors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "colors/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/signup")
    public  ResponseDTO  signIup(@RequestBody UserDTO userDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        if (!Validation.isEmailValid(userDTO.getEmail())) {
            responseDTO.setContent("Invalid email address");
            System.out.println("Invalid email address");
        }
        else if (!Validation.isPasswordValid(userDTO.getPassword())) {
            responseDTO.setContent("Invalid password");
            System.out.println("Invalid password password");
        }
        else if (!Validation.isMobileNumberValid(userDTO.getMobile())) {
            responseDTO.setContent("Invalid mobile number");
            System.out.println("Invalid mobile number");
        }
        else{

          if (userService.userSignUp(userDTO)){
              System.out.println("successfully");
              System.out.println(userDTO);
              responseDTO.setSuccess(true);
              responseDTO.setContent("User has been registered successfully");
          }else {
              System.out.println("Email already in use");
              responseDTO.setContent("Email already in use");
          }


        }


        return responseDTO;
    }

    @PostMapping(value = "/signin")
    public ResponseDTO  userSignIn(@RequestBody UserDTO userDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
       User user = userService.userLogin(userDTO);
        System.out.println(user);
       if (user != null) {
           responseDTO.setSuccess(true);
           responseDTO.setContent(user);
           System.out.println("Successfully logged in");
       }else {

           responseDTO.setMessage("Invalid username or password or blocked");
       }
        System.out.println(responseDTO.getMessage());

        return responseDTO;
    }

    @PostMapping(value = "/profileUpdate")
    public ResponseDTO  userProfileUpdate(@RequestBody UserDTO userDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        User user = userService.profileUpdateUser(userDTO);
        System.out.println(user);
        if (user != null) {
            responseDTO.setSuccess(true);
            responseDTO.setContent(user);
            System.out.println("Successfully updated");
        }else {

            responseDTO.setMessage("something went wrong");
        }
        return responseDTO;
    }




}
