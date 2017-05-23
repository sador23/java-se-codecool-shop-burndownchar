package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.DaoFactory;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserContact;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Person;
import com.codecool.shop.model.Product;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handles the order process of the website
 */
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     *Adds an item to the cart
     * @param req
     * @param res
     * @return
     */
    public static ModelAndView addProductToOrder(Request req, Response res) {
        OrderDaoMem orderDaoMem = req.session().attribute("order");
        ProductDao productDaoMem = DaoFactory.createProductDao();
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

    /**
     * If the form is filled, creates a new user
     * @param request
     * @param response
     * @param session
     * @return
     */
    public static ModelAndView addPerson(Request request, Response response, Session session) {
        session.beginTransaction();

        OrderDaoMem orders = request.session().attribute("order");
        Order currOrder = orders.getOrder();

        Person person = new Person();

        UserContact contact=new UserContact();

        contact.setFirstName(request.queryParams("first_name"));
        contact.setLastName(request.queryParams("last_name"));
        contact.setPhoneNumber(request.queryParams("phone_number"));
        contact.setEmailAddress(request.queryParams("email_address"));
        contact.setShippingCountry(request.queryParams("shipping-country"));
        contact.setShippingCity(request.queryParams("shipping-city"));
        contact.setShippingZipCode(request.queryParams("shipping-zip-code"));
        contact.setShippingAddress(request.queryParams("shipping-address"));
        contact.setBillingCountry(request.queryParams("billing-country"));
        contact.setBillingCity(request.queryParams("billing-city"));
        contact.setBillingZipCode(request.queryParams("billing-zip_code"));
        contact.setBillingAddress(request.queryParams("billing-address"));

        User user= request.session().attribute("user");
        user.setContact(contact);
        session.save(contact);
        session.save(user);
        session.getTransaction().commit();


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
        Map params=new HashMap();
        currOrder.setStatus("checked");
        response.redirect("/order/payment");
        logger.debug("User form filled for order, with name of {} {}",request.queryParams("first_name"),request.queryParams("last_name"));
        return new ModelAndView(params, "product/payment");
    }

    /**
     * Upon successful payment, sets the status to paid
     * @param request
     * @param response
     * @return
     */
    public static ModelAndView setPaidStatus(Request request, Response response) {
        OrderDaoMem orders = request.session().attribute("order");
        Order currOrder = orders.getOrder();

        currOrder.setStatus("paid");
        System.out.println(currOrder.getStatus());
        response.redirect("/index");

        if(request.session().attributes().contains("user")){
            return new ModelAndView(response, "product/payment");
        }
        else return ProductController.renderProducts(request,response);

    }
}
