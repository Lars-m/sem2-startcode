package business.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    //TODO --> Is this the right way
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }

    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null){
            // Production: Get variable from setenv.sh in Tomcats bin folder
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Development on localhost
            //URL = "jdbc:mysql://localhost:3306/useradmin?serverTimezone=CET&useSSL=false";
            URL = "jdbc:mysql://localhost:3306/useradmin";
            USERNAME = "dev";
            PASSWORD = "ax2";
        }
    }

}
