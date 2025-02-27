package com.example.colors.model;

public class Validation {

    public static boolean isPasswordValid(String password) {
        return password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }
    public static boolean isMobileNumberValid(String mobile) {
        return mobile.matches("^07[0-9]{8}$");

    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" // local part
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"); // domain part
    }

}
