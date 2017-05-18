package com.codecool.shop.model;

import javax.persistence.*;

@Entity
public class UserContact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public UserContact(){

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
