package controller.customer;

import controller.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerEditProfileController {
    Connector databaseConnector = Connector.getInstance();
    Connection connection = databaseConnector.getConnection();

    public boolean updateProfile(String currentUsername, String newUsername, String newPassword) {
        String sql = "UPDATE user SET userName = ?, userPassword = ? WHERE userName = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);
            stmt.setString(3, currentUsername);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}