package com.example.colors.controller;

import com.example.colors.dto.ProductDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "colors/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/add")
    public ResponseDTO addProduct(@RequestBody ProductDTO productDTO){

        ResponseDTO responseDTO = new ResponseDTO();

          productService.saveProduct(productDTO);
              responseDTO.setSuccess(true);
              responseDTO.setContent("success");

        System.out.println(responseDTO.isSuccess());
        System.out.println(responseDTO.getContent());
          return  responseDTO;

    }

    @GetMapping(value = "/loadmyproduct")
    public void loadMyproduct(){

    }
}
