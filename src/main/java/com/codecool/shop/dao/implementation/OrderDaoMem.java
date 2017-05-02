package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Orderable;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderDaoMem implements Orderable {
    private List<Order> DATA = new ArrayList<>();
    private static OrderDaoMem instance = null;

    public OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add (Order order){
        order.setId(DATA.size() + 1);
        DATA.add(order);
    }


    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
