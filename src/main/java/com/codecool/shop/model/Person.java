package com.codecool.shop.model;

/**
 * Created by tomi on 2017.05.03..
 */
public class Person {
    static int personIdCounter = 0;

    public int id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String emailAddress;

    public String shippingCountry;
    public String shippingCity;
    public String shippingZipCode;
    public String shippingAddress;

    public String billingCountry;
    public String billingCity;
    public String billingZipCode;
    public String billingAddress;

    public Person(String firstName, String lastName, String phoneNumber, String emailAddress,
                  String shippingCountry, String shippingCity, String shippingZipCode, String shippingAddress,
                  String billingCountry, String billingCity, String billingZipCode, String billingAddress) {

        this.id = personIdCounter + 1;
        personIdCounter += 1;

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.shippingCountry = shippingCountry;
        this.shippingCity = shippingCity;
        this.shippingZipCode = shippingZipCode;
        this.shippingAddress = shippingAddress;
        this.billingCountry = billingCountry;
        this.billingCity = billingCity;
        this.billingZipCode = billingZipCode;
        this.billingAddress = billingAddress;
    }
}
