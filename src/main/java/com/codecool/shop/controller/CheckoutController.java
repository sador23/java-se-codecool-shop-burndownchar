package com.codecool.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomi on 2017.05.03..
 */
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public static ModelAndView renderCheckoutForm(Request req, Response res) {
        logger.debug("Checkout started");
        Map params = new HashMap<>();
        return new ModelAndView(params, "product/checkout");
    }
}
