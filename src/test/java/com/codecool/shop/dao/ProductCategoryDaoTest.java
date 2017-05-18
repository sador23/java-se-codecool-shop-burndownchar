package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class ProductCategoryDaoTest {

    static Stream<ProductCategoryDao> objects() {
        return Stream.of(
                ProductCategoryDaoMem.getInstance(),
                ProductCategoryDaoJdbc.getInstance()
        );
    }

    @BeforeEach
    void clearAllProductCategories() {
        objects().forEach(ProductCategoryDao::removeAll);
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testProductCategoryDaoIsNotNull(ProductCategoryDao argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testGettingAllProductCategories(ProductCategoryDao argument) {
        argument.add(new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display."));
        argument.add(new ProductCategory("Smart phone", "HardWare", "A phone that is very smart."));
        assertEquals(2, argument.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testFindingNotPresentProductCategoryReturnsNull(ProductCategoryDao argument) {
        assertNull(argument.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingProductCategoryIsSuccessFull(ProductCategoryDao argument) {
        argument.add(new ProductCategory("Smart phone", "HardWare", "A phone that is very smart."));
        assertEquals(argument.find(1).getName(),"Smart phone");

    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingSecondProductCategoryIsSuccessFull(ProductCategoryDao argument) {
        argument.add(new ProductCategory("Smart phone", "HardWare", "A phone that is very smart."));
        argument.add(new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display."));
        assertEquals(argument.find(2).getName(),"Tablet");

    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testRemovingProductCategoryIsSuccessFull(ProductCategoryDao argument) {
        argument.add(new ProductCategory("Smart phone", "HardWare", "A phone that is very smart."));
        argument.add(new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display."));
        argument.remove(1);
        assertNull(argument.find(1));

    }


}