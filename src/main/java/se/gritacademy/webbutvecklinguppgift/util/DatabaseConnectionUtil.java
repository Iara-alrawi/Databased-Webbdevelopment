package se.gritacademy.webbutvecklinguppgift.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {
    // JDBC URL med angiven databasnamn och serverTimezone (anpassa vid behov)
    private static final String URL = "jdbc:mysql://localhost:3306/fulkoping_bibliotek?serverTimezone=UTC";
    // Ändra användarnamn och lösenord så att de stämmer överens med din lokala MySQL-installation
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    // Laddar JDBC-drivrutinen
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver saknas.");
            e.printStackTrace();
        }
    }

    // Returnerar en anslutning till databasen
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}