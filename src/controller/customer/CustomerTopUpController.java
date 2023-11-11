package controller.customer;

import controller.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CustomerTopUpController {
    public void topUpWallet(String username, double amount) {
        System.out.println("Top Up Wallet method called with username: " + username + " and amount: " + amount);
        Connector databaseConnector = Connector.getInstance();
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "SELECT userID FROM user WHERE userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");

                sql = "UPDATE wallet SET walletAmount = walletAmount + ? WHERE userID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, userID);
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
                if (rowsAffected == 0) {
                    System.out.println("No wallet found for username: " + username);
                }
            } else {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
