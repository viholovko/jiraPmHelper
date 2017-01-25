package com.boostsolutions.jiraservice.models;

public class FlorsModel {

    private Long id;
    private boolean firstMarket;
    private int flourNuber;
    private float price;

    public FlorsModel() {
    }

    public FlorsModel(boolean firstMarket, int flourNuber, float price) {
        this.firstMarket = firstMarket;
        this.flourNuber = flourNuber;
        this.price = price;
    }

    public FlorsModel(Long id, boolean firstMarket, int flourNuber, float price) {
        this.id = id;
        this.firstMarket = firstMarket;
        this.flourNuber = flourNuber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFirstMarket() {
        return firstMarket;
    }

    public void setFirstMarket(boolean firstMarket) {
        this.firstMarket = firstMarket;
    }

    public int getFlourNuber() {
        return flourNuber;
    }

    public void setFlourNuber(int flourNuber) {
        this.flourNuber = flourNuber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
