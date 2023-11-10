package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {
    public void signUp(String username, String password) throws SQLException {
        Connector databaseConnector = new Connector();
        Connection connection = databaseConnector.getConnection();

        String query = "INSERT INTO user (userName, userPassword, roleType) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, "Customer");

        int rowsAffected = preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int userID = rs.getInt(1);

            query = "INSERT INTO wallet (userID, walletAmount) VALUES (?, 0)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();

        if (rowsAffected <= 0) {
            throw new SQLException("Customer registration failed.");
        }
    }
}