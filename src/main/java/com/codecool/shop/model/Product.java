package com.codecool.shop.model;

import javax.persistence.*;
import java.util.Currency;

@Entity
@Table(name = "product_hiber")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float defaultPrice;

    private String defaultCurrency;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;
    private String name;
    private String description;

    public Product() {
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return Currency.getInstance(this.defaultCurrency);
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }


    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = currency;
    }

    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        this.name=name;
        this.defaultCurrency=currencyString;
        this.defaultPrice=defaultPrice;
        this.description=description;
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

}
