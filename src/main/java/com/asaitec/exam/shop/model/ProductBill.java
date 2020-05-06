package com.asaitec.exam.shop.model;

public class ProductBill {
    private String productName;
    private int numItem;
    private double price;
    private double total;

    public ProductBill(String productName, int numItem, double price, double total) {
        this.productName = productName;
        this.numItem = numItem;
        this.price = price;
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
