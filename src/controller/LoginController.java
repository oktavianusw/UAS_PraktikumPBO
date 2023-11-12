package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static String authenticateUser(String username, String password) {
        // Get the database connection from the Connector singleton
        Connection connection = Connector.getInstance().getConnection();
        if (connection == null) {
            System.err.println("No database connection");
            return null;
        }

        // SQL query to select the user with the given username and password
        String query = "SELECT * FROM user WHERE userName = ? AND userPassword = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the username and password parameters in the SQL statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // Execute the SQL query and get the result
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If a user is found, return the user's role type
                if (resultSet.next()) {
                    return resultSet.getString("roleType");
                }
            }
        } catch (SQLException e) {
            // If an SQL exception occurs, print the exception message
            System.err.println("Error executing query: " + e.getMessage());
        }
        // If no user is found or an exception occurs, return null
        return null;
    }
}