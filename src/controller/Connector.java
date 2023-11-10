package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/product_warehouse_management?user=root&password=";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
