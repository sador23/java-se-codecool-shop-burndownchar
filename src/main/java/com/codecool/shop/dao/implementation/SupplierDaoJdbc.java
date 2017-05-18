package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 2017.05.17..
 */
public class SupplierDaoJdbc implements SupplierDao {

    private static SupplierDaoJdbc instance = null;
    private static PropertiesReader propertiesReader = new PropertiesReader("connection.properties");


    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        propertiesReader.readData();
        return instance;
    }

    public static void setPropertiesReader(String fileName) {
        SupplierDaoJdbc.propertiesReader = new PropertiesReader(fileName);
    }

    @Override
    public void add(Supplier supplier) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO Supplier (name, description) VALUES (?,?);");
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
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

    public Supplier find(int id) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Supplier WHERE id = ?;",
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY,
                            ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                Supplier supplier = new Supplier(
                        result.getString("name"),
                        result.getString("description"));
                supplier.setId(result.getInt("id"));
                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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


    public List<Supplier> getAll() {
        Connection connection = null;
        List<Supplier> supplierList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Supplier supplier = new Supplier(
                        result.getString("name"),
                        result.getString("description"));
                supplier.setId(result.getInt("id"));
                supplierList.add(supplier);
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
        return supplierList;
    }

    public void remove(int id) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM supplier WHERE id = ?;");
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

    public void removeAll() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + propertiesReader.db_url + "/"
                            + propertiesReader.database,
                    propertiesReader.user,
                    propertiesReader.psw);
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE supplier RESTART IDENTITY CASCADE;");
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
