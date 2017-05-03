package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Currency;


public class Order {
    private int id;
    private String status;
    private ArrayList<LineItem> items;
    private float totalPrice;
    private Currency defaultCurrency;

    public Order() {
        this.status = "new";
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.defaultCurrency = Currency.getInstance("USD");
    }



    public void setId(int id) {
        this.id = id;
    }

    public void addLineItem(LineItem lineItem) {
        items.add(lineItem);

    }

    public float getTotalPrice() {
        for (LineItem item: items) {
            this.totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
