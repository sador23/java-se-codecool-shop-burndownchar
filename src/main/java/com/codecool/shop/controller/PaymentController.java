package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
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
            OrderDaoMem orders = OrderDaoMem.getInstance();
            Order currOrder = orders.getOrder();
            Map params = new HashMap<>();

            if(currOrder.getStatus().equals("checked")){
                return new ModelAndView(res, "product/payment");
            }
            else return ProductController.renderProducts(req,res);

        }
    }

