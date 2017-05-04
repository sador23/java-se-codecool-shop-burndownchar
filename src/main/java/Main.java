import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;


import com.codecool.shop.controller.CheckoutController;
import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/cart", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderCart(req, res));
        });

        post("/cart/edit/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.editItem(req, res) );

        });

        get("/cart/delete/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.deleteItem(req, res));
        });


        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        get ("/:id", (req, res) -> {
            res.redirect("/");
            OrderController.addProductToOrder(req, res);
            return null;
        });


        get("/categories/:id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/suppliers/:id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        get("/order/checkout", CheckoutController::renderCheckoutForm, new ThymeleafTemplateEngine());

        get("/order/payment", PaymentController::renderPayment, new ThymeleafTemplateEngine());

        post("/order/checkout/done", (request, response) -> {

            return new ThymeleafTemplateEngine().render( OrderController.addPerson(request, response) );
        });

        post("/order/payment/done", (request, response) -> {

            return new ThymeleafTemplateEngine().render( OrderController.setPaidStatus(request, response) );
        });

    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));


    }


}
