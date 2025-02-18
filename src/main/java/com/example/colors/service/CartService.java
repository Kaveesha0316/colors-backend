package com.example.colors.service;

import com.example.colors.dto.Cart_DTO;
import com.example.colors.entity.*;
import com.example.colors.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductImageRepo productImageRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    OrderStatusRepo orderStatusRepo;



    public String addToCart(String qty, String product_id,String user_id) {

        Cart existCart = cartRepo.findCartByUser_IdAndProduct_Id(Integer.parseInt(user_id), Integer.parseInt(product_id));
        if (existCart == null) {
            Product product = productRepo.findById(Integer.parseInt(product_id)).get();
            User user = userRepo.findById(Integer.parseInt(user_id)).get();

            Cart cart = new Cart();
            cart.setQty(qty);
            cart.setProduct(product);
            cart.setUser(user);
            cart.setDate(new Date());

            cartRepo.save(cart);
            return "add";
        }else {
            existCart.setQty(String.valueOf(Integer.parseInt(existCart.getQty()) + Integer.parseInt(qty)));
            cartRepo.save(existCart);
            return "Updated";
        }

    }

    public List<Cart_DTO> findCartByUser_Id(Integer user_id) {
       List<Cart> cartList = cartRepo.findCartByUser_IdOrderByDateDesc(user_id);
       List<Cart_DTO> cartDTOList = new ArrayList<>();
       for (Cart cart : cartList) {
           Cart_DTO cartDTO = new Cart_DTO();
           cartDTO.setId(cart.getId());
           cartDTO.setProduct_name(cart.getProduct().getName());
           cartDTO.setProduct_price(cart.getProduct().getPrice());
           cartDTO.setProduct_Id(cart.getProduct().getId());

           Product product = productRepo.findById(cart.getProduct().getId()).get();

          Product_image productImage = productImageRepo.findProduct_imageByProduct_id(product.getId());
          cartDTO.setProduct_imagepath(productImage.getImage_path1());

           cartDTO.setQty(String.valueOf(cart.getQty()));
           cartDTOList.add(cartDTO);

       }
        return cartDTOList;
    }
    public boolean deleteCart(String cartItemId) {
        cartRepo.deleteById(Integer.parseInt(cartItemId));
        return true;
    }
    public String updateToCart(String qty, String product_id,String user_id,String status) {

        Cart existCart = cartRepo.findCartByUser_IdAndProduct_Id(Integer.parseInt(user_id), Integer.parseInt(product_id));

        if (status.equals("up")) {
            Product product = productRepo.findById(Integer.parseInt(product_id)).get();
            if (product.getQty() >= Integer.parseInt(existCart.getQty()) + 1) {
                existCart.setQty(String.valueOf(Integer.parseInt(existCart.getQty()) + Integer.parseInt(qty)));
                cartRepo.save(existCart);
                return "Updated";
            } else {
                return "not enough qty";
            }
        }else {
                existCart.setQty(String.valueOf(Integer.parseInt(existCart.getQty()) - Integer.parseInt(qty)));
                cartRepo.save(existCart);
                return "Updated";
        }



    }

    public Boolean removeperchesproduct(Integer userId,String total){
        List<Cart> cartList = cartRepo.findCartByUser_IdOrderByDateDesc(userId);

        Orders orders = new Orders();
        orders.setUser(userRepo.findById(userId).get());
        orders.setTotal_price(Double.parseDouble(total));
        orders.setDate_created(new Date());
        Orders savedorder = ordersRepo.save(orders);

        for (Cart cart: cartList){
            Product product = cart.getProduct();
            product.setQty(product.getQty()-Integer.parseInt(cart.getQty()));

            Order_item orderItem = new Order_item();
            orderItem.setQty(cart.getQty());
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice() * Integer.parseInt(cart.getQty()));
            orderItem.setOrders(savedorder);
            orderItem.setOrder_status(orderStatusRepo.findById(1).get());

            orderItemRepo.save(orderItem);
            productRepo.save(product);
            cartRepo.deleteById(cart.getId());
        }

        return true;
    }
}
