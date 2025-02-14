package com.example.colors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    @JsonProperty("Reg_product_User_id")
    private String Reg_product_User_id;

    @JsonProperty("Reg_product_name")
    private String Reg_product_name;

    @JsonProperty("Reg_product_price")
    private String Reg_product_price;

    @JsonProperty("Reg_product_qty")
    private String Reg_product_qty;

    @JsonProperty("Reg_product_description")
    private String Reg_product_description;

    @JsonProperty("Reg_product_category")
    private String Reg_product_category;

    @JsonProperty("Reg_product_image_path1")
    private String Reg_product_image_path1;

    @JsonProperty("Reg_product_image_path2")
    private String Reg_product_image_path2;

    @JsonProperty("Reg_product_image_path3")
    private String Reg_product_image_path3;

    @JsonProperty("Reg_product_product_id")
    private String Reg_product_product_id;



    public String getReg_product_User_id() {
        return Reg_product_User_id;
    }

    public void setReg_product_User_id(String reg_product_User_id) {
        Reg_product_User_id = reg_product_User_id;
    }

    public String getReg_product_name() {
        return Reg_product_name;
    }

    public void setReg_product_name(String reg_product_name) {
        Reg_product_name = reg_product_name;
    }

    public String getReg_product_price() {
        return Reg_product_price;
    }

    public void setReg_product_price(String reg_product_price) {
        Reg_product_price = reg_product_price;
    }

    public String getReg_product_qty() {
        return Reg_product_qty;
    }

    public void setReg_product_qty(String reg_product_qty) {
        Reg_product_qty = reg_product_qty;
    }

    public String getReg_product_description() {
        return Reg_product_description;
    }

    public void setReg_product_description(String reg_product_description) {
        Reg_product_description = reg_product_description;
    }

    public String getReg_product_category() {
        return Reg_product_category;
    }

    public void setReg_product_category(String reg_product_category) {
        Reg_product_category = reg_product_category;
    }

    public String getReg_product_image_path1() {
        return Reg_product_image_path1;
    }

    public void setReg_product_image_path1(String reg_product_image_path1) {
        Reg_product_image_path1 = reg_product_image_path1;
    }

    public String getReg_product_image_path2() {
        return Reg_product_image_path2;
    }

    public void setReg_product_image_path2(String reg_product_image_path2) {
        Reg_product_image_path2 = reg_product_image_path2;
    }

    public String getReg_product_image_path3() {
        return Reg_product_image_path3;
    }

    public void setReg_product_image_path3(String reg_product_image_path3) {
        Reg_product_image_path3 = reg_product_image_path3;
    }

    public String getReg_product_product_id() {
        return Reg_product_product_id;
    }

    public void setReg_product_product_id(String reg_product_product_id) {
        Reg_product_product_id = reg_product_product_id;
    }
}
