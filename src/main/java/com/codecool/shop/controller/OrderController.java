package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Person;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class OrderController {

    public static ModelAndView addProductToOrder(Request req, Response res) {
        OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(req.params(":id"));
        Product product = productDaoMem.find(id);
        ArrayList itemList = orderDaoMem.getCurrentOrder().stream()
                .filter(n -> n.getName()
                        .equals(product.getName()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (itemList.size() != 0) {
            for (LineItem items : orderDaoMem.getCurrentOrder()) {
                if (items.getName().equals(product.getName())) items.setQuantity(items.getQuantity() + 1);
            }
        } else {
            LineItem lineItem = new LineItem(product, 1, product.getDefaultPrice(), product.getDefaultCurrency());
            orderDaoMem.add(lineItem);
        }

        return ProductController.renderProducts(req, res);

    }

    public static ModelAndView addPerson(Request request, Response response) {
        OrderDaoMem orders = OrderDaoMem.getInstance();
        Order currOrder = orders.getOrder();

        Person person = new Person(request.queryParams("first_name"), request.queryParams("last_name"),
                request.queryParams("phone_number"), request.queryParams("email_address"),
                request.queryParams("shipping-country"), request.queryParams("shipping-city"),
                request.queryParams("shipping-zip-code"), request.queryParams("shipping-address"),
                request.queryParams("billing-country"), request.queryParams("billing-city"),
                request.queryParams("billing-zip_code"), request.queryParams("billing-address"));
        currOrder.setOwner(person);
        currOrder.setStatus("checked");
        response.redirect("/order/payment");
        return new ModelAndView(person, "product/payment");
    }

    public static ModelAndView setPaidStatus(Request request, Response response) {
        OrderDaoMem orders = OrderDaoMem.getInstance();
        Order currOrder = orders.getOrder();

        currOrder.setStatus("paid");
        System.out.println(currOrder.getStatus());
        response.redirect("/index");

        if(currOrder.getStatus().equals("checked")){
            return new ModelAndView(response, "product/payment");
        }
        else return ProductController.renderProducts(request,response);

    }
}
