package com.wynntech.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Product {

    private String id;
    private String category;
    private String name;
    private String description;
    private double price;

    public Product() {

    }

    public Product(String category, String name, String description, double price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String id, String category, String name, String description, double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

