package com.example.colors.service;

import com.example.colors.dto.MyProductDTO;
import com.example.colors.dto.ProductDTO;
import com.example.colors.dto.ReturnProductDTO;
import com.example.colors.entity.*;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductImageRepo productImageRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    public boolean saveProduct(ProductDTO productDTO){

        if (productDTO.getReg_product_product_id() == null) {


            Product product = new Product();
            product.setName(productDTO.getReg_product_name());
            product.setPrice(Double.parseDouble(productDTO.getReg_product_price()));
            product.setQty(Integer.parseInt(productDTO.getReg_product_qty()));
            product.setDescription(productDTO.getReg_product_description());
            product.setCreated_at(new Date());

            Category category = categoryRepo.findByName(productDTO.getReg_product_category());
            product.setCategory(category);

            Status status = statusRepo.findById(1).get();
            product.setStatus(status);

            User user = userRepo.findById(Integer.parseInt(productDTO.getReg_product_User_id())).get();
            product.setUser(user);

            Product returnproduct = productRepo.save(product);

            Product_image productImage = new Product_image();
            productImage.setProduct(returnproduct);
            productImage.setImage_path1(productDTO.getReg_product_image_path1());
            productImage.setImage_path2(productDTO.getReg_product_image_path2());
            productImage.setImage_path3(productDTO.getReg_product_image_path3());

            productImageRepo.save(productImage);
        }else {
          Product product =  productRepo.findById(Integer.parseInt(productDTO.getReg_product_product_id())).get();

            product.setName(productDTO.getReg_product_name());
            product.setQty(Integer.parseInt(productDTO.getReg_product_qty()));
            product.setDescription(productDTO.getReg_product_description());

            Status status = statusRepo.findById(1).get();
            product.setStatus(status);

            Product returnproduct = productRepo.save(product);

            Product_image productImage = productImageRepo.findProduct_imageByProduct_id(returnproduct.getId());

            productImage.setProduct(returnproduct);
            productImage.setImage_path1(productDTO.getReg_product_image_path1());
            productImage.setImage_path2(productDTO.getReg_product_image_path2());
            productImage.setImage_path3(productDTO.getReg_product_image_path3());

            productImageRepo.save(productImage);
        }
        return true;

    }

    public List<MyProductDTO> loadmyproduct(Integer id,String searchText){
        List<Product> productList = productRepo.findProductsByUser_IdAndNameStartingWithOrderByIdDesc(id,searchText);
        List<MyProductDTO> myProductDTOS = new ArrayList<>();


        for (Product product : productList) {
            MyProductDTO myProductDTO = new MyProductDTO();
            myProductDTO.setId(product.getId());
            myProductDTO.setName(product.getName());
            myProductDTO.setPrice(product.getPrice());


           List<Order_item> orderItems = orderItemRepo.findOrder_itemsByProductAndProduct_NameStartingWith(product,searchText);

           Integer sell_quantity = 0;
           myProductDTO.setAvqty(product.getQty());
           myProductDTO.setPrice(product.getPrice());
           myProductDTO.setStatus(product.getStatus().getId());
           myProductDTO.setImgpath(productImageRepo.findProduct_imageByProduct_id(product.getId()).getImage_path1());
           myProductDTO.setImgpath2(productImageRepo.findProduct_imageByProduct_id(product.getId()).getImage_path2());
           myProductDTO.setImgpath3(productImageRepo.findProduct_imageByProduct_id(product.getId()).getImage_path3());
           myProductDTO.setDescription(product.getDescription());
           myProductDTO.setCategory(String.valueOf(product.getCategory().getId()));


           for (Order_item orderItem : orderItems) {
             sell_quantity += Integer.parseInt(orderItem.getQty());
           }
            myProductDTO.setSelqty(sell_quantity);

           if((product.getPrice()*sell_quantity)-(product.getPrice()*10/100) < 0.0){
               myProductDTO.setProfit(0.0);
           }else {
               myProductDTO.setProfit(((product.getPrice()- (product.getPrice() * 10 / 100)) * sell_quantity) );
           }
           myProductDTOS.add(myProductDTO);
        }
        return myProductDTOS;

    }

    public boolean updateProductStatus(Integer product_id,Integer status_id){

       Product product = productRepo.findById(product_id).get();

       Status status = statusRepo.findById(status_id).get();
       product.setStatus(status);
       productRepo.save(product);
        return true;
    };

    public List<ReturnProductDTO> loadRealatedProduct(String category,Integer product_id){

        List<Product> returnProductDTOS = productRepo.findProductsByCategoryNameAndExcludeProductId(category,product_id);
        List<ReturnProductDTO> returnProductDTOS1 = new ArrayList<>();
        for (Product product : returnProductDTOS) {
            ReturnProductDTO returnProductDTO = new ReturnProductDTO();
            returnProductDTO.setId(product.getId());
            returnProductDTO.setName(product.getName());
            returnProductDTO.setPrice(product.getPrice());
            returnProductDTO.setQty(product.getQty());
            returnProductDTO.setStatus(product.getStatus().getId());
            returnProductDTO.setDescription(product.getDescription());
            returnProductDTO.setCategory(product.getCategory().getName());

            Product_image productImage = productImageRepo.findProduct_imageByProduct_id(product.getId());

            returnProductDTO.setImgpath1(productImage.getImage_path1());
            returnProductDTO.setImgpath2(productImage.getImage_path2());
            returnProductDTO.setImgpath3(productImage.getImage_path3());
            returnProductDTOS1.add(returnProductDTO);

        }
        return returnProductDTOS1;
    }
}
