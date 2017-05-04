package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Orderable;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;


public class OrderDaoMem implements Orderable {
    private static List<Order> orders = new ArrayList<>();
    private static OrderDaoMem instance = null;
    private Order order;

    public OrderDaoMem() {
        this.order = new Order();
        OrderDaoMem.orders.add(this.order);
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void deleteItem(LineItem item){
        this.order.getAll().remove(item);
    }

    @Override
    public void add (LineItem lineItem){
        order.addLineItem(lineItem);

    }


    public Order getOrder() {
        return order;
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
        return OrderDaoMem.orders;

    }

    @Override
    public List<LineItem> getCurrentOrder() {
        return this.order.getAll();
    }
}
