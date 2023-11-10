package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    static Connector databaseConnector = new Connector();
    static Connection connection = databaseConnector.getConnection();

    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM user WHERE userName = ? AND userPassword = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}