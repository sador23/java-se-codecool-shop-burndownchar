package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductController {
    static ProductDao productDataStore = ProductDaoMem.getInstance();
    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    static OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
    static Map params = new HashMap<>();




    public static ModelAndView renderProducts(Request req, Response res) {

        params.put("suppliers",supplierDataStore.getAll());
        params.put("categories",productCategoryDataStore.getAll());
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getAll());
        params.put("quantity", orderDaoMem.getOrder().getTotalQuantity());
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderCart(Request req, Response res){
        Map params= new HashMap<>();

        OrderDaoMem orders=OrderDaoMem.getInstance();

        List<LineItem> products=orders.getCurrentOrder();

        int sum=products.stream()
                .mapToInt(n->(int)n.getPrice() * n.getQuantity())
                .sum();
        params.put("products",products);
        params.put("sum",sum);
        return new ModelAndView(params,"product/cart");
    }



    public static ModelAndView deleteItem(Request req, Response res){
        String product_id=req.params(":id");
        OrderDaoMem orders=OrderDaoMem.getInstance();
        List< LineItem> items=orders.getCurrentOrder();
        for(LineItem item : items){
            if(item.getId()==Integer.parseInt(product_id)){
                orders.deleteItem(item);
                break;
            }
        }
        return ProductController.renderCart(req,res);
    }

    public static ModelAndView editItem(Request req, Response res){

        OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(req.params(":id"));
        Product product = productDaoMem.find(id);
        List < LineItem> itemList =orderDaoMem.getCurrentOrder();

            for(LineItem items: itemList){
                if(req.queryParams("id")
                        .equals
                   (Integer.toString(items.getId()))) {
                    items.setQuantity(Integer.parseInt(req.queryParams("quantity")));
                }
            }

        return ProductController.renderCart(req,res);
    }


    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        int searchedId = Integer.parseInt(req.params(":id"));

        params.put("products", productDataStore.getBy(productCategoryDataStore.find(searchedId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        int searchedId = Integer.parseInt(req.params(":id"));

        params.put("products", productDataStore.getBy(supplierDataStore.find(searchedId)));
        return new ModelAndView(params, "product/index");
    }

}
