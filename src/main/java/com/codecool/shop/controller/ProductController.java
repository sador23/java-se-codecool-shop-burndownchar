package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Person;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCart(Request req, Response res){
        Map params= new HashMap<>();
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("sonka",1,1,1));
        persons.add(new Person("sali",3,2,1));
        persons.add(new Person("hagyma",2,2,1));
        persons.add(new Person("torma",1,1,1));
        persons.add(new Person("alma",1,1,1));
         int sum=persons.stream().mapToInt(n->n.price * n.qual).sum();
        params.put("persons",persons);
        params.put("sum",sum);
        return new ModelAndView(params,"product/cart");
    }

}
