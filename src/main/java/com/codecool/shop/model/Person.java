package com.codecool.shop.model;

/**
 * Created by sador on 02.05.17.
 */
public class Person {
    public int price;
    public int id;
    public int priceper;
    public int qual;
    public String name;
    private static int id_p=0;

   public Person(String name, int qual, int price, int priceper){
       this.id=id_p++;
        this.name=name;
        this.qual=qual;
        this.price=price;
        this.priceper=priceper;

    }
}
