package com.codecool.shop.dao;

import com.codecool.shop.entity.ProductCategory;

import java.util.List;

/**
 * Created by sador on 17.05.17.
 */
public interface ProductCategoryOrmDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);

    List<ProductCategory> getAll();
}
