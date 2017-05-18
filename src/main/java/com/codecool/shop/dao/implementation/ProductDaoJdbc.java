package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 2017.05.17..
 */
public class ProductDaoJdbc implements ProductDao {
    private static ProductDaoJdbc instance = null;
    private SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
    private ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
    private static PropertiesReader propertiesReader = new PropertiesReader("connection.properties");

    private ProductDaoJdbc() {
    }

    public static void setPropertiesReader(String fileName) {
       ProductDaoJdbc.propertiesReader = new PropertiesReader(fileName);
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    public void add(Product product) {
        Connection connection = null;


        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO product " +
                            "(name, description, currency, default_price, supplier_id, " +
                            "productcategory_id) VALUES (?,?,?,?,?,?);");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getDefaultCurrency().toString());
            preparedStatement.setFloat(4, product.getDefaultPrice());
            preparedStatement.setInt(5, product.getSupplier().getId());
            preparedStatement.setInt(6, product.getProductCategory().getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public Product find(int id) {
        Connection connection = null;
        String query = "SELECT * FROM Product WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product WHERE id = ?;",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("supplier_id")),
                        supplierDaoJdbc.find(result.getInt("productcategory_id"))
                );
                product.setId(result.getInt("id"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<Product> getAll() {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("productcategory_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return products;
    }

    public void remove(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public List<Product> getBy(Supplier supplier) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Product WHERE supplier_id = ?;",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, supplier.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("productcategory_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return products;
    }

    public List<Product> getBy(ProductCategory productCategory) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Product WHERE productcategory_id = ?;",
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY,
                            ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, productCategory.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("productcategory_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return products;
    }
    public void removeAll() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE product RESTART IDENTITY CASCADE;");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }





}



