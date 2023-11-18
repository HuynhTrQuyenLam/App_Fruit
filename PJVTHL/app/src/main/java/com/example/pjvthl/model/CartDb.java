package com.example.pjvthl.model;

public class CartDb {
    Integer id;
    String Name;
    String images;
    Double price;

    Integer quantity;

    public CartDb(Integer id, String name, String images, Double price, Integer quantity) {
        this.id = id;
        Name = name;
        this.images = images;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
