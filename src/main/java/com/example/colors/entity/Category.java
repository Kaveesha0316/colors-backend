package com.example.colors.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    public Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length =45, nullable = false)
    private String name;

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
}
