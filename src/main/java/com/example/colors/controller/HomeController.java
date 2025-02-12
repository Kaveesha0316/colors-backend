package com.example.colors.controller;

import com.example.colors.dto.ResponseDTO;
import com.example.colors.entity.Product;
import com.example.colors.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "colors/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping(value = "/loadproduct")
    public ResponseDTO loadProduct(@RequestParam("searchText") String searchText) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        List<Product> productList = homeService.SearchAllProducts(searchText);
        responseDTO.setContent(productList);
        System.out.println(responseDTO);
        System.out.println(searchText);

        return responseDTO;

    }
}
