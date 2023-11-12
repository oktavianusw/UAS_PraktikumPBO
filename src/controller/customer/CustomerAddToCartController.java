package controller.customer;

import controller.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerAddToCartController {

    Connector databaseConnector = Connector.getInstance();
    Connection connection = databaseConnector.getConnection();

    public boolean addToCart(String username, int productID, int quantity) {
        String fetchUserID = "SELECT userID FROM user WHERE userName = ?";
        String insertIntoCartSQL = "INSERT INTO cart(userID, productID, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement fetchUserIdStatement = connection.prepareStatement(fetchUserID);
             PreparedStatement insertIntoCartStatement = connection.prepareStatement(insertIntoCartSQL)) {

            fetchUserIdStatement.setString(1, username);
            ResultSet resultSet = fetchUserIdStatement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");

                // Insert into cart
                insertIntoCartStatement.setInt(1, userID);
                insertIntoCartStatement.setInt(2, productID);
                insertIntoCartStatement.setInt(3, quantity);

                int rowsAffected = insertIntoCartStatement.executeUpdate();


                // Check if the insertion was successful
                return rowsAffected > 0;
                
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
