package com.example.colors.controller;

import com.example.colors.dto.MyProductDTO;
import com.example.colors.dto.ProductDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.dto.ReturnProductDTO;
import com.example.colors.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "colors/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/add")
    public ResponseDTO addProduct(@RequestBody ProductDTO productDTO){
        System.out.println("aava");
        ResponseDTO responseDTO = new ResponseDTO();

          productService.saveProduct(productDTO);
              responseDTO.setSuccess(true);
              responseDTO.setContent("success");

        System.out.println(responseDTO.isSuccess());
        System.out.println(responseDTO.getContent());
          return  responseDTO;

    }

    @GetMapping(value = "/loadmyproduct")
    public ResponseDTO loadMyproduct(@RequestParam("searchText") String searchText,@RequestParam("userId") String userId){

        ResponseDTO responseDTO = new ResponseDTO();
       List<MyProductDTO> myProductDTOS = productService.loadmyproduct(Integer.parseInt(userId),searchText);

       if (myProductDTOS != null) {
           responseDTO.setSuccess(true);
           responseDTO.setContent(myProductDTOS);
       }
       return responseDTO;


    }

    @GetMapping(value = "/changestatus")
    public ResponseDTO loadMyproductStatus(@RequestParam("status") String status,@RequestParam("product_id") String product_id){

        ResponseDTO responseDTO = new ResponseDTO();
        productService.updateProductStatus(Integer.parseInt(product_id),Integer.parseInt(status));

        if (productService.updateProductStatus(Integer.parseInt(product_id),Integer.parseInt(status))) {
            responseDTO.setSuccess(true);
            responseDTO.setContent("updated status");
        }
        return responseDTO;


    }

    @GetMapping(value = "/loadrelatedproduct")
    public ResponseDTO loadRealatedProduct(@RequestParam("category") String category,@RequestParam("product_id") String product_id) {

        ResponseDTO responseDTO = new ResponseDTO();


        List<ReturnProductDTO> productList = productService.loadRealatedProduct(category,Integer.parseInt(product_id));
        if (productList != null) {
            responseDTO.setSuccess(true);
            responseDTO.setContent(productList);
            System.out.println(responseDTO);
        }


        return responseDTO;

    }
}
