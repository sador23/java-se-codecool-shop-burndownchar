package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

/**
 * Created by dynuchu on 2017.05.02..
 */
public interface Orderable {
    void add(LineItem lineItem);
    Order find(int id);
    void remove(int id);


    List<Order> getAll();

    List<LineItem> getCurrentOrder();
}
