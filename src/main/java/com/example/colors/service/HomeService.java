package com.example.colors.service;

import com.example.colors.dto.ReturnProductDTO;
import com.example.colors.entity.Product;
import com.example.colors.entity.Product_image;
import com.example.colors.entity.User;
import com.example.colors.repo.ProductImageRepo;
import com.example.colors.repo.ProductRepo;
import com.example.colors.repo.StatusRepo;
import com.example.colors.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HomeService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductImageRepo productImageRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    public  List<ReturnProductDTO> SearchAllProducts(String name) {
       List<Product> productList = productRepo.findProductByStatus_NameAndNameStartingWithOrderByIdDesc("active", name);

       List<ReturnProductDTO> ReturnProductlist = new ArrayList<ReturnProductDTO>();
       for (Product product : productList) {

           ReturnProductDTO ReturnProductDTO = new ReturnProductDTO();
           ReturnProductDTO.setId(product.getId());
           ReturnProductDTO.setName(product.getName());
           ReturnProductDTO.setPrice(product.getPrice());
           ReturnProductDTO.setCategory(product.getCategory().getName());
           ReturnProductDTO.setDescription(product.getDescription());
           ReturnProductDTO.setQty(product.getQty());
           ReturnProductDTO.setStatus(product.getStatus().getId());

           Product_image productImage = productImageRepo.findProduct_imageByProduct_id(product.getId());
           ReturnProductDTO.setImgpath1(productImage.getImage_path1());
           ReturnProductDTO.setImgpath2(productImage.getImage_path2());
           ReturnProductDTO.setImgpath3(productImage.getImage_path3());
           ReturnProductlist.add(ReturnProductDTO);

       }

       return ReturnProductlist;
    }

    public List<ReturnProductDTO> advanceSearchProducts(String name, String categoryName, Double priceAfter, Double priceBefore,String sort) {
        if (categoryName != null && categoryName.isEmpty()) {
            categoryName = null; // Set to null if empty
        }
          List<Product> productList = productRepo.findProducts("active", name, categoryName, priceAfter, priceBefore,sort);
        List<ReturnProductDTO> ReturnProductlist = new ArrayList<ReturnProductDTO>();
        for (Product product : productList) {

            ReturnProductDTO ReturnProductDTO = new ReturnProductDTO();
            ReturnProductDTO.setId(product.getId());
            ReturnProductDTO.setName(product.getName());
            ReturnProductDTO.setPrice(product.getPrice());
            ReturnProductDTO.setCategory(product.getCategory().getName());
            ReturnProductDTO.setDescription(product.getDescription());
            ReturnProductDTO.setQty(product.getQty());
            ReturnProductDTO.setStatus(product.getStatus().getId());

            Product_image productImage = productImageRepo.findProduct_imageByProduct_id(product.getId());
            ReturnProductDTO.setImgpath1(productImage.getImage_path1());
            ReturnProductDTO.setImgpath2(productImage.getImage_path2());
            ReturnProductDTO.setImgpath3(productImage.getImage_path3());
            ReturnProductlist.add(ReturnProductDTO);

        }

        return ReturnProductlist;
    }



}
