package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierOrmDao;
import com.codecool.shop.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sador on 17.05.17.
 */
public class SupplierDaoOrm implements SupplierOrmDao{
    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoOrm instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoOrm() {
    }

    public static SupplierDaoOrm getInstance() {
        if (instance == null) {
            instance = new SupplierDaoOrm();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(DATA.size() + 1);
        DATA.add(supplier);
    }

    @Override
    public Supplier find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}
