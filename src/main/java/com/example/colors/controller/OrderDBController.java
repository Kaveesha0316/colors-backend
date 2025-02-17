package com.example.colors.controller;

import com.example.colors.dto.CategoryProfitDTO;
import com.example.colors.dto.ProfitResponseDTO;
import com.example.colors.dto.ResponseDTO;
import com.example.colors.service.OrderDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "colors/orderdb")
public class OrderDBController {
    @Autowired
    OrderDBService LorderDBService;

    @GetMapping(value = "/barchart")
    public ResponseDTO LoadBarChary(@RequestParam("user_id") String user_id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProfitResponseDTO> profitResponseDTOS = LorderDBService.LoadBarChart(Integer.parseInt(user_id));
        for (ProfitResponseDTO profitResponseDTO : profitResponseDTOS) {
            System.out.println(profitResponseDTO.getProfit());
            System.out.println(profitResponseDTO.getDate());
        }
        responseDTO.setSuccess(true);
        responseDTO.setContent(profitResponseDTOS);
        return responseDTO;
    }

    @GetMapping(value = "/piechart")
    public ResponseDTO LoadPieChart(@RequestParam("user_id") String user_id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CategoryProfitDTO> profitResponseDTOS = LorderDBService.loadPieChart(Integer.parseInt(user_id));
        for (CategoryProfitDTO profitResponseDTO : profitResponseDTOS) {
            System.out.println(profitResponseDTO.getProfit());
            System.out.println(profitResponseDTO.getCategory());
        }
        responseDTO.setSuccess(true);
        responseDTO.setContent(profitResponseDTOS);
        return responseDTO;
    }

    @GetMapping(value = "/totalqty")
    public ResponseDTO totalqty(@RequestParam("user_id") String userId) {

        ResponseDTO responseDTO = new ResponseDTO();
        String qty = LorderDBService.totalqty(userId);
        responseDTO.setSuccess(true);
        responseDTO.setContent(qty);

        return responseDTO;
    }

    @GetMapping(value = "/totalprofit")
    public ResponseDTO totalprofit(@RequestParam("user_id") String userId) {

        ResponseDTO responseDTO = new ResponseDTO();
        String profit = LorderDBService.totalprofit(userId);
        responseDTO.setSuccess(true);
        responseDTO.setContent(profit);

        return responseDTO;
    }

    @GetMapping(value = "/bestproduct")
    public ResponseDTO bestproduct(@RequestParam("user_id") String userId) {

        ResponseDTO responseDTO = new ResponseDTO();
        String profit = LorderDBService.bestProduct(userId);
        responseDTO.setSuccess(true);
        responseDTO.setContent(profit);

        return responseDTO;
    }
}
