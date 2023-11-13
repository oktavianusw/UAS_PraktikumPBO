package controller.customer;

import controller.Connector;
import view.customer.CustomerViewCartPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class CustomerCheckOutController {
    Connector databaseConnector = Connector.getInstance();
    Connection connection = databaseConnector.getConnection();

    CustomerViewCartPage viewCartPage;

    public CustomerCheckOutController(CustomerViewCartPage viewCartPage) {
        this.viewCartPage = viewCartPage;
    }

    public boolean adjustBalance(String username, double totalPrice){
        CustomerMenuController controller = new CustomerMenuController();
        double balance = controller.getBalance(username);
        double newBalance = balance - totalPrice;
        String updateBalanceSQL = "UPDATE wallet SET walletAmount = ? WHERE userID = ?";
        String fetchUserID = "SELECT userID FROM user WHERE userName = ?";
        try (PreparedStatement updateBalanceStatement = connection.prepareStatement(updateBalanceSQL);
             PreparedStatement fetchUserIDStatement = connection.prepareStatement(fetchUserID)) {
            fetchUserIDStatement.setString(1, username);
            ResultSet resultSet = fetchUserIDStatement.executeQuery();
            resultSet.next();
            int userID = resultSet.getInt("userID");
            updateBalanceStatement.setDouble(1, newBalance);
            updateBalanceStatement.setInt(2, userID);
            int rowsAffected = updateBalanceStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            viewCartPage.showMessage("An error occurred during checkout.");
            return false;
        }
    }
    public boolean checkWalletAmount(String username){
        CustomerMenuController controller = new CustomerMenuController();
        double balance = controller.getBalance(username);
        String fetchTotalPriceSQL = "SELECT SUM(p.productPrice * c.quantity) as total FROM cart c JOIN product p ON p.productID = c.productID WHERE c.userID = ?";
        String fetchUserID = "SELECT userID FROM user WHERE userName = ?";
        try (PreparedStatement fetchTotalPriceStatement = connection.prepareStatement(fetchTotalPriceSQL);
             PreparedStatement fetchUserIDStatement = connection.prepareStatement(fetchUserID)) {
            fetchUserIDStatement.setString(1, username);
            ResultSet resultSet = fetchUserIDStatement.executeQuery();
            resultSet.next();
            int userID = resultSet.getInt("userID");
            fetchTotalPriceStatement.setInt(1, userID);
            ResultSet totalPriceResultSet = fetchTotalPriceStatement.executeQuery();
            totalPriceResultSet.next();
            double totalPrice = totalPriceResultSet.getDouble("total");
            if(balance<totalPrice){
                JOptionPane.showMessageDialog(null, "Insufficient balance");
                return false;
            }else{
                adjustBalance(username, totalPrice);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            viewCartPage.showMessage("An error occurred during checkout.");
            return false;
        }
    }


    public boolean checkout(String username) {
        String fetchUserID = "SELECT userID FROM user WHERE userName = ?";
        String fetchCartItemsSQL = "SELECT * FROM cart WHERE userID = ?";
        String insertIntoOrderSQL = "INSERT INTO transaction(userID, transaction_date , transactionType) VALUES (?, ?, ?)";
        String deleteCartItemsSQL = "DELETE FROM cart WHERE userID = ?";
        String insertIntoDetailTransaction = "INSERT INTO detailtransaction(detailTransactionID, transactionID, productID, quantity) VALUES (?, ?, ?, ?)";
        String fetchTotalPriceSQL = "SELECT SUM(price * quantity) as total FROM cart WHERE userID = ?";
        try (PreparedStatement fetchUserIdStatement = connection.prepareStatement(fetchUserID);
             PreparedStatement fetchCartItemsStatement = connection.prepareStatement(fetchCartItemsSQL);
             PreparedStatement insertIntoOrderStatement = connection.prepareStatement(insertIntoOrderSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement deleteCartItemsStatement = connection.prepareStatement(deleteCartItemsSQL);
             PreparedStatement insertIntoDetailTransactionStatement = connection.prepareStatement(insertIntoDetailTransaction);
             PreparedStatement fetchTotalPriceStatement = connection.prepareStatement(fetchTotalPriceSQL)) {
    
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
                    // ResultSet totalPriceResultSet = fetchTotalPriceStatement.executeQuery();
                    // double totalPrice = totalPriceResultSet.getDouble("total");
                    if(!checkWalletAmount(username)){
                        viewCartPage.showMessage("Insufficient balance");
                        return false;
                    } else {
                        // Insert into transactions
                        insertIntoOrderStatement.setInt(1, userID);
                        insertIntoOrderStatement.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Assuming current date
                        insertIntoOrderStatement.setString(3, "Purchase"); // Assuming transaction type is "Purchase"
        
                        int orderRowsAffected = insertIntoOrderStatement.executeUpdate();
                        if (orderRowsAffected > 0) {
                        // Get the generated transaction ID
                        try (ResultSet generatedKeys = insertIntoOrderStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int transactionID = generatedKeys.getInt(1);
    
                                // Insert into detailtransaction
                                insertIntoDetailTransactionStatement.setInt(1, transactionID); // Assuming detailTransactionID is same as transactionID
                                insertIntoDetailTransactionStatement.setInt(2, transactionID);
                                insertIntoDetailTransactionStatement.setInt(3, productID);
                                insertIntoDetailTransactionStatement.setInt(4, quantity);
                                insertIntoDetailTransactionStatement.executeUpdate();
                            }
                        }
    
                        // If the order is successful, delete the item from the cart
                        deleteCartItemsStatement.setInt(1, userID);
                        deleteCartItemsStatement.executeUpdate();
                    } else {
                        // Handle the case where the order insertion fails
                        viewCartPage.showMessage("Failed to insert order for productID: " + productID);
                        return false;
                    }
                    }
                    
                }
    
                viewCartPage.showMessage("Checkout successful!");
                viewCartPage.showMessage("Wallet balance updated!");

                return true; // Checkout success
            } else {
                // Handle the case where the username does not exist
                viewCartPage.showMessage("User " + username + " is not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            viewCartPage.showMessage("An error occurred during checkout.");
            return false;
        }
    }
}