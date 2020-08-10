package com.example.agilesquadron.model;

public class CartDel {

    public String _id;
    public  String user;
    public String name;
    public String price;
    public String location;

    public CartDel(String _id, String user, String name, String price, String location) {
        this._id = _id;
        this.user = user;
        this.name = name;
        this.price = price;
        this.location = location;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
