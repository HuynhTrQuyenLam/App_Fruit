package com.example.pjvthl.model;

public class SanPhamMoiRa {
    private int resoureId;
    private String title;
    private String price;

    public SanPhamMoiRa(int resoureId, String title, String price) {
        this.resoureId = resoureId;
        this.title = title;
        this.title = price;
    }

    public int getResoureId() {
        return resoureId;
    }

    public void setResoureId(int resoureId) {
        this.resoureId = resoureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
