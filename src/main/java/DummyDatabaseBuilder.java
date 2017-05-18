import com.codecool.shop.dao.implementation.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tomi on 2017.05.18..
 */
public class DummyDatabaseBuilder {
    public static PropertiesReader propertiesReader = new PropertiesReader("test_connection.properties");

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        propertiesReader.readData();
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+propertiesReader.db_url+"/"
                        +propertiesReader.database,
                propertiesReader.user,
                propertiesReader.psw);
        System.out.println("Establishing database connection...");
        System.out.println("Connecting to dummy database...");
        System.out.println("Connected database successfully...");
        System.out.println("Creating tables in dummy database...");
        Statement stmt = con.createStatement();

        String sql = "DROP TABLE IF EXISTS ProductCategory, Supplier, Product CASCADE; " +
                "CREATE TABLE ProductCategory " +
                "(id SERIAL PRIMARY KEY, " +
                "name VARCHAR(35), " +
                "description VARCHAR(255)," +
                "department VARCHAR(255)); " +

                "CREATE TABLE Supplier " +
                "(id SERIAL PRIMARY KEY, " +
                "name VARCHAR(35), " +
                "description VARCHAR(255)); " +

                "CREATE TABLE Product " +
                "(id SERIAL PRIMARY KEY, " +
                "supplier_id INT, " +
                "productcategory_id INT, " +
                "name VARCHAR(35), " +
                "description VARCHAR(255), " +
                "currency VARCHAR(10), " +
                "default_price FLOAT, " +
                "FOREIGN KEY (supplier_id) REFERENCES Supplier (id), " +
                "FOREIGN KEY(productcategory_id) REFERENCES ProductCategory (id));";

        stmt.executeUpdate(sql);
        System.out.println("Created ProductCategory, Supplier, Product tables in codecoolshop...");

        sql = "INSERT INTO productcategory (name, description, department) VALUES " +
                "('Tablet','Hardware','A tablet computer, commonly shortened to tablet, " +
                "is a thin, flat mobile computer with a touchscreen display.');";

        stmt.executeUpdate(sql);
        System.out.println("Tablet added to ProductCategory table...");

        sql = "INSERT INTO Supplier (name, description) VALUES ('Amazon','Digital content and services');\n" +
                "INSERT INTO Supplier (name, description) VALUES ('Lenovo','Computers');";

        stmt.executeUpdate(sql);
        System.out.println("Amazon, Lenovo added to ProductCategory table...");

        sql = "INSERT INTO Product (name, description, currency, default_price, " +
                "supplier_id, productcategory_id) " +
                "VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. " +
                "Good parental controls. " +
                "Helpful technical support.', 'USD', 49.9, " +
                "(SELECT id from Supplier WHERE name='Amazon' ), " +
                "(SELECT id from ProductCategory WHERE name='Tablet' )); " +

                "INSERT INTO Product (name, description, currency, default_price, " +
                "supplier_id, productcategory_id) " +
                "VALUES ('Amazon Fire HD 8', 'Amazons latest Fire HD 8 tablet is a great value " +
                "for media consumption." +
                "Helpful technical support.', 'USD', 49.9, " +
                "(SELECT id from Supplier WHERE name='Amazon' ), " +
                "(SELECT id from ProductCategory WHERE name='Tablet' )); " +

                "INSERT INTO Product (name, description, currency, default_price, " +
                "supplier_id, productcategory_id) " +
                "VALUES ('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. " +
                "Fanless Core m5 processor. Full-size USB ports. " +
                "Adjustable kickstand.', 'USD', 479, " +
                "(SELECT id from Supplier WHERE name='Lenovo' ), " +
                "(SELECT id from ProductCategory WHERE name='Tablet' ));";

        stmt.executeUpdate(sql);

        System.out.println("Added products to Products table");
        System.out.println("Database populated");

        con.close();
        System.out.println("Database connection closed.");

    }
}
