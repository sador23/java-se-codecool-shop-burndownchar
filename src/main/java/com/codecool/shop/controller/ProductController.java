package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    public static ModelAndView renderProducts(Request req, Response res) {
        if(!req.session().attributes().contains("order")) req.session().attribute("order",OrderDaoMem.getInstance());
        logger.debug("Products rendered! Nice!");

        params.put("suppliers",supplierDataStore.getAll());
        params.put("categories",productCategoryDataStore.getAll());
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getAll());
        params.put("quantity", orderDaoMem.getOrder().getTotalQuantity());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCart(Request req, Response res){
        Map params= new HashMap<>();
        req.session().attribute("order");

        OrderDaoMem orders=req.session().attribute("order");

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
        OrderDaoMem orders=req.session().attribute("order");
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

        OrderDaoMem orderDaoMem = req.session().attribute("order");
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(req.params(":id"));
        Product product = productDaoMem.find(id);
        List < LineItem> itemList =orderDaoMem.getCurrentOrder();

            for(LineItem items: itemList){
                if(req.queryParams("id")
                        .equals
                   (Integer.toString(items.getId()))) {
                    if(Integer.parseInt(req.queryParams("quantity"))<=0) orderDaoMem.deleteItem(items);
                    else items.setQuantity(Integer.parseInt(req.queryParams("quantity")));
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
