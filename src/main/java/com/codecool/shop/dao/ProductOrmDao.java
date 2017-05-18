package com.codecool.shop.dao;

import com.codecool.shop.entity.Product;
import com.codecool.shop.entity.ProductCategory;
import com.codecool.shop.entity.Supplier;

import java.util.List;

public interface ProductOrmDao {

        void add(Product product);
        Product find(int id);
        void remove(int id);

        List<Product> getAll();
        List<Product> getBy(Supplier supplier);
        List<Product> getBy(ProductCategory productCategory);

    }

