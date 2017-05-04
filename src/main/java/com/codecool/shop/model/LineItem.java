package com.codecool.shop.model;

import java.util.Currency;

public class LineItem {
    private Product product;
    private int quantity;
    private float price;
    private Currency defaultCurrency;

    public LineItem(Product product, int quantity, float price, Currency defaultCurrency) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.defaultCurrency = defaultCurrency;
    }


    public int getId(){
        return this.product.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getName(){
        return this.product.getName();
    }
}
