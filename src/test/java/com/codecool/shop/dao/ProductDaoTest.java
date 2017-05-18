package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by tomi on 2017.05.16..
 */
class ProductDaoTest {

    static Stream<ProductDao> objects() {
        return Stream.of(
                ProductDaoMem.getInstance(),
                ProductDaoJdbc.getInstance()
        );
    }

    static Stream<ProductCategoryDao> productCategoryDaoStream() {
        return Stream.of(
                ProductCategoryDaoMem.getInstance(),
                ProductCategoryDaoJdbc.getInstance()
        );
    }

    static Stream<SupplierDao> supplierStream() {
        return Stream.of(
                SupplierDaoMem.getInstance(),
                SupplierDaoJdbc.getInstance()
        );
    }


    @BeforeEach
    void clearAllProducts() {
        objects().forEach(ProductDao::removeAll);
    }


    @ParameterizedTest
    @MethodSource(names = "objects")
    void testProductDaoIsNotNull(ProductDao argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingProductIsSuccessFull(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierStream().forEach(supplierDao -> supplierDao.add(amazon));
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware",
                "A tablet computer, commonly shortened to tablet, " +
                        "is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDaoStream().forEach(productCategoryDao -> productCategoryDao.add(tablet));
        argument.add(new Product("Amazon Fire HD 8", 49.9f, "USD",
                "Fantastic price. " +
                "Large content ecosystem. " +
                "Good parental controls. Helpful technical support.", tablet, amazon));
        assertNotNull(argument.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingSecondProductIsSuccessFull(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierStream().forEach(supplierDao -> supplierDao.add(amazon));
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDaoStream().forEach(productCategoryDao -> productCategoryDao.add(tablet));
        argument.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        argument.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        assertNotNull(argument.find(2));
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testGettingAllProducts(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierStream().forEach(supplierDao -> supplierDao.add(amazon));
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware",
                "A tablet computer, commonly shortened to tablet, is a thin, " +
                        "flat mobile computer with a touchscreen display.");
        productCategoryDaoStream().forEach(productCategoryDao -> productCategoryDao.add(tablet));
        argument.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        argument.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        assertEquals(2, argument.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testFindingNotPresentProductReturnsNull(ProductDao argument) {
        assertNull(argument.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testFindingProductsBySupplier(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        argument.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        argument.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        List<Product> results = argument.getBy(amazon);
        for (Product product : results) {
            assertEquals("Amazon", product.getSupplier().getName());
        }
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testFindingProductsByProductCategory(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        argument.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        argument.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        List<Product> results = argument.getBy(tablet);
        for (Product product : results) {
            assertEquals("Tablet", product.getProductCategory().getName());
        }
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testDeletingProduct(ProductDao argument) {
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        argument.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        argument.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        argument.remove(1);
        assertNull(argument.find(1));
    }


}