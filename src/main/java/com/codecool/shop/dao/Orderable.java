package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.util.List;



public interface Orderable {
    void add(LineItem lineItem);
    Order find(int id);
    void remove(int id);


    List<Order> getAll();

    List<LineItem> getCurrentOrder();


}
