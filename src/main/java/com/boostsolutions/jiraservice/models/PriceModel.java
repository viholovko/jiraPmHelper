package com.boostsolutions.jiraservice.models;

public class PriceModel {

    private Long id;
    private boolean firstMarket;
    private float square;
    private float positionCenter;
    private float positionMiddle;
    private float positionPeripherals;
    private float internet;
    private boolean internetKey;
    private float furniture;
    private boolean furnitureKey;
    private float repair;
    private boolean repairKey;
    private float avg;
    private boolean avgKey;

    public PriceModel() {
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

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public float getPositionCenter() {
        return positionCenter;
    }

    public void setPositionCenter(float positionCenter) {
        this.positionCenter = positionCenter;
    }

    public float getPositionMiddle() {
        return positionMiddle;
    }

    public void setPositionMiddle(float positionMiddle) {
        this.positionMiddle = positionMiddle;
    }

    public float getPositionPeripherals() {
        return positionPeripherals;
    }

    public void setPositionPeripherals(float positionPeripherals) {
        this.positionPeripherals = positionPeripherals;
    }

    public float getInternet() {
        return internet;
    }

    public void setInternet(float internet) {
        this.internet = internet;
    }

    public boolean isInternetKey() {
        return internetKey;
    }

    public void setInternetKey(boolean internetKey) {
        this.internetKey = internetKey;
    }

    public float getFurniture() {
        return furniture;
    }

    public void setFurniture(float furniture) {
        this.furniture = furniture;
    }

    public boolean isFurnitureKey() {
        return furnitureKey;
    }

    public void setFurnitureKey(boolean furnitureKey) {
        this.furnitureKey = furnitureKey;
    }

    public float getRepair() {
        return repair;
    }

    public void setRepair(float repair) {
        this.repair = repair;
    }

    public boolean isRepairKey() {
        return repairKey;
    }

    public void setRepairKey(boolean repairKey) {
        this.repairKey = repairKey;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public boolean isAvgKey() {
        return avgKey;
    }

    public void setAvgKey(boolean avgKey) {
        this.avgKey = avgKey;
    }
}