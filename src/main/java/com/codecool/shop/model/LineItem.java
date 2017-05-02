package com.codecool.shop.model;

import java.util.Currency;

public class LineItem {
    private Product product;
    private int quantity;
    private float defaultPrice;
    private Currency defaultCurrency;

    public LineItem(Product product, int quantity, float defaultPrice, Currency defaultCurrency) {
        this.product = product;
        this.quantity = quantity;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
    }
}
