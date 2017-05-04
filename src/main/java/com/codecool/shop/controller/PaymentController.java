package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomi on 2017.05.04..
 */
public class PaymentController {
        public static ModelAndView renderPayment(Request req, Response res) {
            Map params = new HashMap<>();
            return new ModelAndView(params, "product/payment");
        }
    }

