package com.example.colors.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "password", length = 45, nullable = false)
    private String password;

    @Column(name = "mobile", length = 20, nullable = false)
    private String mobile;

    @Column(name = "city",length = 45)
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "image_path")
    private String image_path;

    @Column(name = "location",length = 50)
    private String location;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
