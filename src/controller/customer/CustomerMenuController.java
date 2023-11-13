package controller.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.Connector;

public class CustomerMenuController {
    private Connection connection;

    public CustomerMenuController() {
        // Get the database connection from the Connector singleton
        this.connection = Connector.getInstance().getConnection();
        if (this.connection == null) {
            System.err.println("No database connection");
        }
    }

    public double getBalance(String username) {
        String query = "SELECT walletAmount FROM wallet INNER JOIN user ON wallet.userID = user.userID WHERE user.userName = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("walletAmount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
