package com.example.colors.service;

import com.example.colors.dto.CategoryProfitDTO;
import com.example.colors.dto.CustomerOrderDTO;
import com.example.colors.dto.ProfitResponseDTO;
import com.example.colors.entity.Order_item;
import com.example.colors.entity.Orders;
import com.example.colors.entity.Product;
import com.example.colors.entity.Product_image;
import com.example.colors.repo.OrderItemRepo;
import com.example.colors.repo.OrdersRepo;
import com.example.colors.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDBService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    OrdersRepo ordersRepo;

    public List<ProfitResponseDTO> LoadBarChart(Integer user_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");

        List<Order_item> userOrders = ordersRepo.findOrdersByUser_Id(user_id);

        Map<String, Integer> profitByDate = userOrders.stream()
                .collect(Collectors.groupingBy(
                        order -> sdf.format(order.getOrders().getDate_created()), // Group by order date
                        Collectors.summingInt(order -> (int) ((order.getProduct().getPrice()-(order.getProduct().getPrice()*10/100)) * Integer.parseInt(order.getQty()))) // Calculate profit
                ));

        // Convert map to list, sort by date ASC, and limit to the last 5 entries
        return profitByDate.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)) // Sort by date ASC
                .skip(Math.max(0, profitByDate.size() - 5)) // Keep only the last 5 entries
                .map(entry -> new ProfitResponseDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<CategoryProfitDTO> loadPieChart(Integer userId) {
        List<Order_item> orderItems = ordersRepo.findOrdersByUser_Id(userId);

        // Group profits by category
        Map<String, Integer> profitByCategory = orderItems.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getProduct().getCategory().getName(), // Group by category name
                        Collectors.summingInt(order -> (int) (order.getPrice() * Integer.parseInt(order.getQty()))) // Calculate profit
                ));

        // Convert to List<CategoryProfitDTO>
        return profitByCategory.entrySet().stream()
                .map(entry -> new CategoryProfitDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public String totalqty(String userId) {
        List<Orders> orders = ordersRepo.findByUser_IdNotOrderByIdDesc(Integer.parseInt(userId));
        Integer qty = 0;
        if (orders != null) {

            for (Orders order : orders) {

                List<Order_item> orderItemList = orderItemRepo.findOrder_itemsByOrdersAndProduct_NameStartingWith(order,"");
                for (Order_item orderItem : orderItemList) {

                    if(orderItem.getProduct().getUser().getId() == Integer.parseInt(userId)) {
                      qty +=Integer.parseInt(orderItem.getQty());

                    }


                }
            }

        }
        return String.valueOf(qty);
    }

    public String totalprofit(String userId) {
        List<Orders> orders = ordersRepo.findByUser_IdNotOrderByIdDesc(Integer.parseInt(userId));
        Double profit = 0.0;
        if (orders != null) {

            for (Orders order : orders) {

                List<Order_item> orderItemList = orderItemRepo.findOrder_itemsByOrdersAndProduct_NameStartingWith(order,"");
                for (Order_item orderItem : orderItemList) {

                    if(orderItem.getProduct().getUser().getId() == Integer.parseInt(userId)) {
                        profit += orderItem.getPrice();

                    }


                }
            }

        }

        Double prof = profit - (profit * 10/100);

        return String.valueOf(prof);
    }

    public String bestProduct(String userId) {
        int userIdInt = Integer.parseInt(userId);

        // Fetch all orders (where the user's products were sold)
        List<Orders> orders = ordersRepo.findByUser_IdNotOrderByIdDesc(userIdInt);

        if (orders == null || orders.isEmpty()) {
            return "No orders found.";
        }

        Map<String, Double> productProfitMap = new HashMap<>();

        for (Orders order : orders) {
            // Fetch all order items in the order
            List<Order_item> orderItemList = orderItemRepo.findOrder_itemsByOrdersAndProduct_NameStartingWith(order, "");

            for (Order_item orderItem : orderItemList) {
                if (orderItem.getProduct().getUser().getId() == userIdInt) {
                    String productName = orderItem.getProduct().getName();

                    // Calculate profit (selling price - cost price) * quantity
                    double profit = orderItem.getPrice();

                    // Add profit to the map
                    productProfitMap.put(productName, productProfitMap.getOrDefault(productName, 0.0) + profit);
                }
            }
        }

        // Find the product with the highest profit
        String bestProfitProduct = null;
        double maxProfit = 0.0;

        for (Map.Entry<String, Double> entry : productProfitMap.entrySet()) {
            if (entry.getValue() > maxProfit) {
                maxProfit = entry.getValue();
                bestProfitProduct = entry.getKey();
            }
        }

        return bestProfitProduct;
    }


}
