package com.example.agilesquadron.model;

public class Cart {
    public  String user;
    public String name;
    public String price;
    public String location;

    public Cart(String user, String name, String price, String location) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
