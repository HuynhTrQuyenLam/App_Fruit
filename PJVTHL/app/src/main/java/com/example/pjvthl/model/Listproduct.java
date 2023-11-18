package com.example.pjvthl.model;

public class Listproduct {
    private int resoureId;
    private String title;

    public Listproduct(int resoureId, String title) {
        this.resoureId = resoureId;
        this.title = title;
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
}
