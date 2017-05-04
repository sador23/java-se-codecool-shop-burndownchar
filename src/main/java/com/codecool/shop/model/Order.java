package com.codecool.shop.model;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


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

    public List<LineItem>getAll(){
        return items;
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
