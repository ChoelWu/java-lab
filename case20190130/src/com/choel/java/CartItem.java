package com.choel.java;

public class CartItem {
    private String id;
    private String name;
    private int quality;
    private double price;
    private String desc;

    public CartItem(String id, String name, int quality, double price) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getSum() {
        double sum = price * quality;
        return sum;
    }
}
