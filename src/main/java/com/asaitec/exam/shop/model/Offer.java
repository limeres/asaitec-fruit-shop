package com.asaitec.exam.shop.model;

public class Offer {
    private String type;
    private String productOne;
    private String productTwo;
    private int amountOne;
    private int amountTwo;

    public Offer(String type, String productOne, String productTwo, int amountOne, int amountTwo) {
        this.type = type;
        this.productOne = productOne;
        this.productTwo = productTwo;
        this.amountOne = amountOne;
        this.amountTwo = amountTwo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductOne() {
        return productOne;
    }

    public void setProductOne(String productOne) {
        this.productOne = productOne;
    }

    public String getProductTwo() {
        return productTwo;
    }

    public void setProductTwo(String productTwo) {
        this.productTwo = productTwo;
    }

    public int getAmountOne() {
        return amountOne;
    }

    public void setAmountOne(int amountOne) {
        this.amountOne = amountOne;
    }

    public int getAmountTwo() {
        return amountTwo;
    }

    public void setAmountTwo(int amountTwo) {
        this.amountTwo = amountTwo;
    }
}
