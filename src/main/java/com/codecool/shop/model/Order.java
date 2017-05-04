package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


public class Order {
    private int id;
    private String status;
    private ArrayList<LineItem> items;
    private float totalPrice;
    private int totalQuantity;
    private Currency defaultCurrency;
    private Person owner;

    public Order() {
        this.status = "new";
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.totalQuantity = 0;
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

    public int getTotalQuantity() {
        this.totalQuantity=items.stream().mapToInt(n->n.getQuantity()).sum();
        return totalQuantity;
    }

    public void setOwner(Person owner){
        this.owner=owner;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}

