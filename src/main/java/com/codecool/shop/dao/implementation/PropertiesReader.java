package com.codecool.shop.dao.implementation;

import java.io.*;
import java.util.Properties;

/**
 * Created by tomi on 2017.05.17..
 */
public class PropertiesReader {
    public static String user;
    public static String psw;
    public static String database;
    public static String db_url;
    public static String JDBC_DRIVER = "jdbc:postgresql";

    public void readData() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("connection.properties");
            properties.load(inputStream);
            database = properties.getProperty("database");
            user = properties.getProperty("user");
            db_url = properties.getProperty("url");
            psw = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hibernateXmlSetup(){


    }
}
