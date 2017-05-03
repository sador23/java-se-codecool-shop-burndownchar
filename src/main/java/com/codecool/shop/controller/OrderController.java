package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class OrderController {

    public static ModelAndView addProductToOrder(Request req, Response res) {
        OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(req.params(":id"));
        Product product = productDaoMem.find(id);
        LineItem lineItem = new LineItem(product, 1, product.getDefaultPrice(), product.getDefaultCurrency());
        orderDaoMem.add(lineItem);
        return ProductController.renderProducts(req,res);

    }
}
