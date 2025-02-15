package com.example.colors.controller;

import com.example.colors.dto.ResponseDTO;
import com.example.colors.dto.ReturnProductDTO;
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

        List<ReturnProductDTO> productList = homeService.SearchAllProducts(searchText);
        responseDTO.setContent(productList);
        System.out.println(responseDTO);
        System.out.println(searchText);

        return responseDTO;

    }

    @GetMapping(value = "/advanceSearch")
    public ResponseDTO advanceSearchProduct(@RequestParam("searchText") String searchText,@RequestParam("category") String category,@RequestParam("startPrice") String startPrice,@RequestParam("endPrice") String endPrice,@RequestParam("sort") String sort) {
        System.out.println(searchText);
        System.out.println(category);
        System.out.println(startPrice);
        System.out.println(endPrice);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        List<ReturnProductDTO> productList = homeService.advanceSearchProducts(searchText,category,Double.parseDouble(startPrice),Double.parseDouble(endPrice),sort);
        responseDTO.setContent(productList);
//        System.out.println(productList);

        return responseDTO;

    }
}
