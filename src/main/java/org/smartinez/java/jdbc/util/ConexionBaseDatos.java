package org.smartinez.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    //en este caso tmb funciona asi: "jdbc:mysql://localhost:3306/java_curso" o "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=UTC"
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=America/Argentina/Buenos_Aires";
    private static String username = "root";
    private static String password = "sasa";
    private static Connection connection;
    //usando un singleton
    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

}
