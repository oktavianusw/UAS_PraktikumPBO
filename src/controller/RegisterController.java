package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {
    public void signUp(String username, String password) throws SQLException {
        // Get the database connection from the Connector singleton
        Connection connection = Connector.getInstance().getConnection();
        if (connection == null) {
            throw new SQLException("No database connection");
        }

        // Prepare a SQL query to check if the username already exists
        String checkQuery = "SELECT * FROM user WHERE userName = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        ResultSet checkRs = checkStmt.executeQuery();

        // If the username already exists, throw an exception
        if (checkRs.next()) {
            throw new SQLException("Username already exists.");
        }

        // Prepare a SQL query to insert the new user
        String query = "INSERT INTO user (userName, userPassword, roleType) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, "Customer");

        // Execute the insert query and get the number of affected rows
        int rowsAffected = preparedStatement.executeUpdate();

        // Get the generated keys (user ID) from the insert query
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int userID = rs.getInt(1);

            // Prepare a SQL query to insert a new wallet for the user
            query = "INSERT INTO wallet (userID, walletAmount) VALUES (?, 0)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        }

        // Close the prepared statement and the connection
        preparedStatement.close();
        connection.close();

        // If no rows were affected by the insert query, throw an exception
        if (rowsAffected <= 0) {
            throw new SQLException("Customer registration failed.");
        }
    }
}