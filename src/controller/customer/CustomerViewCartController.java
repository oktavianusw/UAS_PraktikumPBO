package controller.customer;

import controller.Connector;
import model.Cart;
import model.Product;
import view.customer.CustomerViewCartPage;
import view.customer.CustomerMenuPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerViewCartController {
    private List<Cart> cartItems;

    private static CustomerViewCartController instance;
    private CustomerMenuPage customerMenuPage;

    private CustomerViewCartController() {
        cartItems = new ArrayList<>();
    }

    public static CustomerViewCartController getInstance() {
        if (instance == null) {
            instance = new CustomerViewCartController();
        }
        return instance;
    }

    public List<Cart> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void checkout(String username) {
        Connector databaseConnector = Connector.getInstance();
        try (Connection conn = databaseConnector.getConnection()) {
            String getUserIDSQL = "SELECT userID FROM user WHERE userName = ?";
            try (PreparedStatement getUserIDStmt = conn.prepareStatement(getUserIDSQL)) {
                getUserIDStmt.setString(1, username);
                ResultSet userIDResultSet = getUserIDStmt.executeQuery();

                if (userIDResultSet.next()) {
                    int userID = userIDResultSet.getInt("userID");

                    for (Cart cartItem : cartItems) {
                        int productID = cartItem.getProductID();
                        Product product = getProductById(productID);
                        int quantity = product.getProductQuantity();

                        if (product != null) {
                            int warehouseID = product.getWarehouseID();

                            String updateProductListSQL = "UPDATE productlist SET quantity = quantity - ? " +
                                    "WHERE productID = ? AND warehouseID = ?";
                            try (PreparedStatement updateProductListStmt = conn
                                    .prepareStatement(updateProductListSQL)) {
                                updateProductListStmt.setInt(1, quantity);
                                updateProductListStmt.setInt(2, productID);
                                updateProductListStmt.setInt(3, warehouseID);

                                int rowsAffected = updateProductListStmt.executeUpdate();
                                if (rowsAffected == 0) {
                                    CustomerViewCartPage.getInstance(username, customerMenuPage).showMessage(
                                            "Product not found in the product list: " + product.getProductName());
                                }
                            }

                            String updateProductSQL = "UPDATE product SET productSold = productSold + ? " +
                                    "WHERE productID = ?";

                            try (PreparedStatement updateProductStmt = conn.prepareStatement(updateProductSQL)) {
                                updateProductStmt.setInt(1, quantity);
                                updateProductStmt.setInt(2, productID);

                                int rowsAffected = updateProductStmt.executeUpdate();
                                if (rowsAffected == 0) {
                                    CustomerViewCartPage.getInstance(username, customerMenuPage).showMessage(
                                            "Product not found in the product list: " + product.getProductName());
                                }
                            }

                            String updateWalletSQL = "UPDATE wallet SET walletAmount = walletAmount - ? " +
                                    "WHERE userID = ?";
                            try (PreparedStatement updateWalletStmt = conn.prepareStatement(updateWalletSQL)) {
                                updateWalletStmt.setDouble(1, product.getProductPrice() * quantity);
                                updateWalletStmt.setInt(2, userID);

                                int rowsAffected = updateWalletStmt.executeUpdate();
                                if (rowsAffected == 0) {
                                    CustomerViewCartPage.getInstance(username, customerMenuPage)
                                            .showMessage("Wallet not found for user with ID: " + userID);
                                }
                            }
                        }
                    }

                    CustomerViewCartPage.getInstance(username, customerMenuPage)
                            .showMessage("Checkout successful for user with ID: " + userID);
                } else {

                    CustomerViewCartPage.getInstance(username, customerMenuPage)
                            .showMessage("No user found with username: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Product getProductById(int productID) {
        Connector databaseConnector = Connector.getInstance();
        try (Connection conn = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM product WHERE productID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, productID);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Product(rs.getInt("productID"),
                            rs.getString("productName"),
                            rs.getInt("productQuantity"),
                            rs.getDouble("productPrice"),
                            rs.getDouble("productRating"),
                            rs.getInt("productSold"),
                            rs.getString("detailBarang"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}