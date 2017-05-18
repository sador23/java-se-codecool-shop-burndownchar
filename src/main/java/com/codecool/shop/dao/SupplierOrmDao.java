package com.codecool.shop.dao;

import com.codecool.shop.entity.Supplier;

import java.util.List;



public interface SupplierOrmDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);

    List<Supplier> getAll();
}
