package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    public void signUp(String username, String password) {
        Connector databaseConnector = new Connector();
        Connection connection = databaseConnector.getConnection();

        if (connection != null) {
            try {
                String query = "INSERT INTO users (username, password, roleType) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "Customer");

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer registered successfully.");
                } else {
                    System.out.println("Customer registration failed.");
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Customer registration failed. Error: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error closing the database connection: " + e.getMessage());
                }
            }
        } else {
            System.err.println("Unable to establish a database connection.");
        }
    }
}
