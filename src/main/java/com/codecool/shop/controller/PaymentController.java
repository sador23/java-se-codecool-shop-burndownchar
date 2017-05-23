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
 * Handles the payment
 */
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Renders the payment form
     * @param req
     * @param res
     * @return
     */
        public static ModelAndView renderPayment(Request req, Response res) {
            OrderDaoMem orders = req.session().attribute("order");
            Order currOrder = orders.getOrder();
            Map params = new HashMap<>();

          if(!req.session().attributes().contains("user")){
                return new ModelAndView(params, "product/register");
            }
            else {
              logger.debug("Payment started");
              return new ModelAndView(params, "product/payment");
          }

        }
    }

