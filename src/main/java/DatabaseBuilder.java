import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tomi on 2017.05.17..
 */
public class DatabaseBuilder {
    public static String user;
    public static String psw;
    public static String database;
    public static String db_url;

    public static void readData(){
        try {
            Properties properties = new Properties();
            InputStream inputStream = Main.class.getResourceAsStream("sql/connection.properties");
            properties.load(inputStream);
            database=properties.getProperty("database");
            user=properties.getProperty("user");
            db_url=properties.getProperty("url");
            psw=properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readData();
}
