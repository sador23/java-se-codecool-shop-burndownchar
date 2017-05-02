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
    }

    public void setId(int id) {
        this.id = id;
    }
}
