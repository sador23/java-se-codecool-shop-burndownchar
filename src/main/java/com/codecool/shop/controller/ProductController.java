package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.entity.Product;
import com.codecool.shop.entity.User;
import com.codecool.shop.model.*;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import javax.persistence.Query;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ProductController {

    static ProductDao productDataStore = ProductDaoMem.getInstance();
    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    static OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
    static Map params = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    public static ModelAndView renderProducts(Request req, Response res) {
        if(!req.session().attributes().contains("order")) req.session().attribute("order",OrderDaoMem.getInstance());
        params.put("suppliers",supplierDataStore.getAll());
        params.put("categories",productCategoryDataStore.getAll());
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getAll());
        params.put("quantity", orderDaoMem.getOrder().getTotalQuantity());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView login(Request request,Response response){
        return new ModelAndView(params,"product/login");
    }

    public static ModelAndView register(Request request,Response response){
        return new ModelAndView(params,"product/register");
    }

    public static ModelAndView register_user(Request request, Response response,Session session){
        session.beginTransaction();
        User user=new User(request.queryParams("name"),request.queryParams("mail"),BCrypt.hashpw( request.queryParams("psw"), BCrypt.gensalt(10)));
        session.save(user);
        session.getTransaction().commit();
        return new ProductController().renderProducts(request,response);
    }

    public static ModelAndView login_user(Request request, Response response, Session session){
        String input_name= request.queryParams("name");
        Query query = session.createQuery("FROM User WHERE email=:input");
        query.setParameter("input",input_name);
        List<User> users=(List<User>) query.getResultList();
        if (users.get(0).authenticate_user(request.queryParams("psw"))) System.out.println("Yep");
        else System.out.println("Nope");

        return new ProductController().renderProducts(request,response);
    }


    public static ModelAndView renderCart(Request req, Response res){
        Map params= new HashMap<>();
        req.session().attribute("order");

        OrderDaoMem orders = req.session().attribute("order");

        List<LineItem> products = orders.getCurrentOrder();

        int sum = products.stream()
                .mapToInt(n -> (int) n.getPrice() * n.getQuantity())
                .sum();
        params.put("products", products);
        params.put("sum", sum);
        if (sum != 0) {
            return new ModelAndView(params, "product/cart");
        } else {
            res.redirect("/");
            return new ModelAndView(params, "product/cart");
        }
    }


    public static ModelAndView deleteItem(Request req, Response res){
        String product_id=req.params(":id");
        OrderDaoMem orders=req.session().attribute("order");
        List< LineItem> items=orders.getCurrentOrder();
        for(LineItem item : items){
            if(item.getId()==Integer.parseInt(product_id)){
                orders.deleteItem(item);
                break;
            }
        }
        logger.debug("Deleted item from cart with id of {}",product_id);
        return ProductController.renderCart(req,res);
    }

    public static ModelAndView editItem(Request req, Response res){
        OrderDaoMem orderDaoMem = req.session().attribute("order");
        ProductDao productDaoMem = ProductDaoMem.getInstance();
        int id = Integer.parseInt(req.params(":id"));
        Product product = productDaoMem.find(id);
        List < LineItem> itemList =orderDaoMem.getCurrentOrder();
            for(LineItem items: itemList){
                if(req.queryParams("id")
                        .equals
                   (Integer.toString(items.getId()))) {
                    if(Integer.parseInt(req.queryParams("quantity"))<=0){
                        orderDaoMem.deleteItem(items);
                        logger.debug("Deleted item from cart with id of {}", items.getId());
                        logger.info("hali");
                    }
                    else {
                        items.setQuantity(Integer.parseInt(req.queryParams("quantity")));
                        logger.debug("Edited quantity of item from cart with id of {} to new quantity of {}",items.getId(),req.queryParams("quantity"));
                    }
                }
            }


        return ProductController.renderCart(req,res);
    }


    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        int searchedId = Integer.parseInt(req.params(":id"));

        params.put("products", productDataStore.getBy(productCategoryDataStore.find(searchedId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        int searchedId = Integer.parseInt(req.params(":id"));

        params.put("products", productDataStore.getBy(supplierDataStore.find(searchedId)));
        return new ModelAndView(params, "product/index");
    }

}
