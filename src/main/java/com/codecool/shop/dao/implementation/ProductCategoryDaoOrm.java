package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryOrmDao;
import com.codecool.shop.entity.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sador on 17.05.17.
 */
public class ProductCategoryDaoOrm implements ProductCategoryOrmDao{
    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoOrm instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoOrm() {
    }

    public static ProductCategoryDaoOrm getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoOrm();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        return DATA.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}
