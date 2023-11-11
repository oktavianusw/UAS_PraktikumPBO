package controller.customer;

import controller.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCheckoutController {
    Connector databaseConnector = Connector.getInstance();
    Connection connection = databaseConnector.getConnection();

    public boolean checkout(String username) {
        String fetchUserID = "SELECT userID FROM user WHERE userName = ?";
        String fetchCartItemsSQL = "SELECT * FROM cart WHERE userID = ?";
        String insertIntoOrderSQL = "INSERT INTO orders(userID, productID, quantity) VALUES (?, ?, ?)";
        String deleteCartItemsSQL = "DELETE FROM cart WHERE userID = ?";

        try (PreparedStatement fetchUserIdStatement = connection.prepareStatement(fetchUserID);
             PreparedStatement fetchCartItemsStatement = connection.prepareStatement(fetchCartItemsSQL);
             PreparedStatement insertIntoOrderStatement = connection.prepareStatement(insertIntoOrderSQL);
             PreparedStatement deleteCartItemsStatement = connection.prepareStatement(deleteCartItemsSQL)) {

            fetchUserIdStatement.setString(1, username);
            ResultSet resultSet = fetchUserIdStatement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");

                // Fetch cart items for the user
                fetchCartItemsStatement.setInt(1, userID);
                ResultSet cartItemsResultSet = fetchCartItemsStatement.executeQuery();

                while (cartItemsResultSet.next()) {
                    int productID = cartItemsResultSet.getInt("productID");
                    int quantity = cartItemsResultSet.getInt("quantity");

                    // Insert into orders
                    insertIntoOrderStatement.setInt(1, userID);
                    insertIntoOrderStatement.setInt(2, productID);
                    insertIntoOrderStatement.setInt(3, quantity);

                    int orderRowsAffected = insertIntoOrderStatement.executeUpdate();

                    if (orderRowsAffected > 0) {
                        // If the order is successful, delete the item from the cart
                        deleteCartItemsStatement.setInt(1, userID);
                        deleteCartItemsStatement.executeUpdate();
                    } else {
                        // Handle the case where the order insertion fails
                        System.out.println("Failed to insert order for productID: " + productID);
                        return false;
                    }
                }

                return true; // Checkout success
            } else {
                // Handle the case where the username does not exist
                System.out.println("User not found: " + username);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}