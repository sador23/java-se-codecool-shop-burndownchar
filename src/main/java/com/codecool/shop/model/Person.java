package com.codecool.shop.model;

/**
 * Created by sador on 02.05.17.
 */
public class Person {
    public int price;
    public int priceper;
    public int qual;
    public String name;

   public Person(String name, int qual, int price, int priceper){
        this.name=name;
        this.qual=qual;
        this.price=price;
        this.priceper=priceper;

    }
}
