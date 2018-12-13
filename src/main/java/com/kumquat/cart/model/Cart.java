package com.kumquat.cart.model;

public class Cart {

    private String catgory;
    private String brand;
    private String color;
    private double price;

    public Cart(String catgory, String brand, String color, double price) {
        this.catgory = catgory;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public Cart() {
    }

    public String getCatgory() {
        return catgory;
    }

    public void setCatgory(String catgory) {
        this.catgory = catgory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
