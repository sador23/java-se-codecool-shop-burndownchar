package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomi on 2017.05.04..
 */
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

        public static ModelAndView renderPayment(Request req, Response res) {
            OrderDaoMem orders = req.session().attribute("order");
            Order currOrder = orders.getOrder();
            Map params = new HashMap<>();

          if(currOrder.getStatus().equals("checked")){
                return new ModelAndView(params, "product/payment");
            }
            else {
              logger.debug("Payment started");
              return ProductController.renderProducts(req,res);
          }

        }
    }

