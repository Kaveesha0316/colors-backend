package com.example.colors.controller;

import com.example.colors.dto.ProfitResponseDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.dto.UserDTO;
import com.example.colors.entity.Admin;
import com.example.colors.entity.Order_item;
import com.example.colors.entity.Product;
import com.example.colors.entity.User;
import com.example.colors.service.AdminService;
import com.example.colors.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "colors/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/loadusers")
    public  List<User> LoadUsers(){
        List<User> userList = adminService.loadusers();
        return  userList;
    }

    @RequestMapping(value = "/chngsts")
    public void changests(@RequestParam("id") String id){
        adminService.chngsts(id);
    }

    @GetMapping(value = "/loadproducts")
    public  List<Product> Loadproducts(){
        List<Product> productList = adminService.loadproducts();
        return  productList;
    }

    @RequestMapping(value = "/chngproductsts")
    public void changeproductsts(@RequestParam("id") String id){
        adminService.chngproductsts(id);
    }

    @GetMapping(value = "/loadorders")
    public  List<Order_item> Loadorders(){
        List<Order_item> orderItems = adminService.loadorders();
        return  orderItems;
    }

    @GetMapping(value = "/totalerning")
    public  String totalErning(){
       String total = adminService.totalErning();
        return  total;
    }

    @GetMapping(value = "/totalsales")
    public  String totalSales(){
        String total = adminService.totalsales();
        return  total;
    }
    @GetMapping(value = "/totalusers")
    public  String totalUsers(){
        String total = adminService.totaluser();
        return  total;
    }
    @GetMapping(value = "/totalcart")
    public  String totalCarts(){
        String total = adminService.totalCart();
        return  total;
    }

    @GetMapping(value = "/barchart")
    public ResponseDTO LoadBarChary() {

        ResponseDTO responseDTO = new ResponseDTO();
        List<ProfitResponseDTO> profitResponseDTOS = adminService.LoadBarChart();
        for (ProfitResponseDTO profitResponseDTO : profitResponseDTOS) {
            System.out.println(profitResponseDTO.getProfit());
            System.out.println(profitResponseDTO.getDate());
        }
        responseDTO.setSuccess(true);
        responseDTO.setContent(profitResponseDTOS);
        return responseDTO;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<ResponseDTO> signIn(@RequestBody UserDTO userDTO, HttpSession session){

        ResponseDTO responseDTO = new ResponseDTO();
        String admin = adminService.signin(userDTO.getEmail(),userDTO.getPassword());

        if (admin == null) {
            responseDTO.setMessage("Invalid details");
        }else {
            if (admin.equals("enter email")) {
                responseDTO.setMessage("Please enter email");
            } else if (admin.equals("enter password")) {
                responseDTO.setMessage("Please enter password");
            } else {
                responseDTO.setSuccess(true);
                responseDTO.setMessage("Login successful");

                // Assuming 'admin' contains the username or user ID.
                session.setAttribute("username", admin);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/get-username")
    public ResponseEntity<String> getUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
        return ResponseEntity.ok(username);
    }

    @PostMapping("/signout")
    public ResponseEntity<ResponseDTO> signOut(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);
        responseDTO.setMessage("Signed out successfully");

        return ResponseEntity.ok(responseDTO);
    }

}
