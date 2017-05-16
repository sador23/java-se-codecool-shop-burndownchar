package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Person;
import com.codecool.shop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public static ModelAndView addProductToOrder(Request req, Response res) {
        OrderDaoMem orderDaoMem = req.session().attribute("order");
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        try{
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
            logger.debug("Added item to cart with id of {}",req.params(":id"));
        }catch (NumberFormatException e){

        }


        return ProductController.renderProducts(req, res);

    }

    public static ModelAndView addPerson(Request request, Response response) {
        OrderDaoMem orders = request.session().attribute("order");
        Order currOrder = orders.getOrder();

        Person person = new Person();
        person.setFirstName(request.queryParams("first_name"));
        person.setLastName(request.queryParams("last_name"));
        person.setPhoneNumber(request.queryParams("phone_number"));
        person.setEmailAddress(request.queryParams("email_address"));
        person.setShippingCountry(request.queryParams("shipping-country"));
        person.setShippingCity(request.queryParams("shipping-city"));
        person.setShippingZipCode(request.queryParams("shipping-zip-code"));
        person.setShippingAddress(request.queryParams("shipping-address"));
        person.setBillingCountry(request.queryParams("billing-country"));
        person.setBillingCity(request.queryParams("billing-city"));
        person.setBillingZipCode(request.queryParams("billing-zip_code"));
        person.setBillingAddress(request.queryParams("billing-address"));
        
        currOrder.setOwner(person);

        currOrder.setStatus("checked");
        response.redirect("/order/payment");
        logger.debug("User form filled for order, with name of {} {}",request.queryParams("first_name"),request.queryParams("last_name"));
        return new ModelAndView(person, "product/payment");
    }

    public static ModelAndView setPaidStatus(Request request, Response response) {
        OrderDaoMem orders = request.session().attribute("order");
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
