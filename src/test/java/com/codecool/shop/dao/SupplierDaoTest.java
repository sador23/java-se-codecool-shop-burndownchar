package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by tomi on 2017.05.16..
 */
class SupplierDaoTest {

    static Stream<SupplierDao> objects() {
        return Stream.of(
                SupplierDaoMem.getInstance(),
                SupplierDaoJdbc.getInstance()
        );
    }

    @BeforeEach
    void clearAllSuppliers() {
        objects().forEach(SupplierDao::removeAll);
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testSupplierDaoIsNotNull(SupplierDao argument) {
        assertNotNull(argument);
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testGettingAllSuppliers(SupplierDao argument) {
        argument.add(new Supplier("Amazon", "Digital content and services"));
        argument.add(new Supplier("Lenovo", "Computers"));
        assertEquals(2, argument.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testFindingNotPresentSupplierReturnsNull(SupplierDao argument) {
        assertNull(argument.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingSupplierIsSuccessFull(SupplierDao argument) {
        argument.add(new Supplier("Amazon", "Digital content and services"));
        assertEquals(argument.find(1).getName(),"Amazon");

    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testAddingSecondSupplierIsSuccessFull(SupplierDao argument) {
        argument.add(new Supplier("Amazon", "Digital content and services"));
        argument.add(new Supplier("Lenovo", "Computers"));
        assertEquals(argument.find(2).getName(),"Lenovo");

    }

    @ParameterizedTest
    @MethodSource(names = "objects")
    void testRemovingSupplierIsSuccessFull(SupplierDao argument) {
        argument.add(new Supplier("Amazon", "Digital content and services"));
        argument.add(new Supplier("Lenovo", "Computers"));
        argument.remove(1);
        assertNull(argument.find(1));

    }

}