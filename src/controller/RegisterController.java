package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    public void signUp(String username, String password) throws SQLException {
        Connector databaseConnector = new Connector();
        Connection connection = databaseConnector.getConnection();

        String query = "INSERT INTO user (userName, userPassword, roleType) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, "Customer");

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        if (rowsAffected <= 0) {
            throw new SQLException("Customer registration failed.");
        }
    }
}
