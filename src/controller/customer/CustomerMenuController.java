package controller.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.Connector;

public class CustomerMenuController {
    private Connector connector;

    public CustomerMenuController() {
        this.connector = Connector.getInstance();
    }

    public double getBalance(String username) {
        String query = "SELECT walletAmount FROM wallet INNER JOIN user ON wallet.userID = user.userID WHERE user.userName = ?";
        try (Connection conn = connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
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
