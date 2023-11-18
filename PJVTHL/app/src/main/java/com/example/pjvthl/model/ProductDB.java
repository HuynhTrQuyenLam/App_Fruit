package com.example.pjvthl.model;

import java.io.Serializable;

public class ProductDB implements Serializable {
    Integer id;
    String namepro;
    Double quantity;
    Double price;
    String decs;
    String images;
    String cate;
    String prokind;

    public ProductDB(Integer id, String namepro, Double quantity, Double price, String decs, String images, String cate, String prokind) {
        this.id = id;
        this.namepro = namepro;
        this.quantity = quantity;
        this.price = price;
        this.decs = decs;
        this.images = images;
        this.cate = cate;
        this.prokind = prokind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamepro() {
        return namepro;
    }

    public void setNamepro(String namepro) {
        this.namepro = namepro;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getProkind() {
        return prokind;
    }

    public void setProkind(String prokind) {
        this.prokind = prokind;
    }
}
