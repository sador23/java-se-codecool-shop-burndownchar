package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomi on 2017.05.03..
 */
public class CheckoutController {
    public static ModelAndView renderCheckoutForm(Request req, Response res) {
        Map params = new HashMap<>();
        return new ModelAndView(params, "product/checkout");
    }
}
