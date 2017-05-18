package com.codecool.shop.model;


import javax.persistence.*;

@Entity
@Table(name = "prodcat_hiber")
public class ProductCategory {


    private int id;
    private String name;
    private String description;
    private String department;

    public ProductCategory() {
    }
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ProductCategory(String name, String description, String department) {
        this.name = name;
        this.description = description;
        this.department = department;
    }
}
