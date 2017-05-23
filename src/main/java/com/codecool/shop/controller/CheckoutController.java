package com.codecool.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;


/**
 * Handles the checkout process
 */
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Renders the checkout page
     * @param req
     * @param res
     * @return
     */
    public static ModelAndView renderCheckoutForm(Request req, Response res) {
        logger.debug("Checkout started");
        Map params = new HashMap<>();
        if(!req.session().attributes().contains("user")){
            return new ModelAndView(params, "product/login");
        }
        return new ModelAndView(params, "product/checkout");
    }
}
