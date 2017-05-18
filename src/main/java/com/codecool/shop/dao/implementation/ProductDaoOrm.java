package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductOrmDao;
import com.codecool.shop.entity.Product;
import com.codecool.shop.entity.ProductCategory;
import com.codecool.shop.entity.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sador on 17.05.17.
 */
public class ProductDaoOrm implements ProductOrmDao{

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoOrm instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoOrm() {
    }

    public static ProductDaoOrm getInstance() {
        if (instance == null) {
            instance = new ProductDaoOrm();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(DATA.size() + 1);
        DATA.add(product);
    }

    @Override
    public Product find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return DATA;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
