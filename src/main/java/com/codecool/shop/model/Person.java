package com.codecool.shop.model;


import jdk.nashorn.internal.runtime.logging.Logger;

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



    public Person() {

        this.id = personIdCounter + 1;
        personIdCounter += 1;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public void setShippingZipCode(String shippingZipCode) {
        this.shippingZipCode = shippingZipCode;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
