package controller.customer;

import controller.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CustomerPurchaseHistoryController {
    private Connector connector;

    public CustomerPurchaseHistoryController(){
        this.connector = Connector.getInstance();
    }

    public ArrayList<String[]> getPurchaseHistory(String username) {
        
        ArrayList<String[]> purchaseHistory = new ArrayList<>();
    
        String query = "SELECT a.transaction_date, b.quantity, (b.quantity * c.productPrice) as 'Total Harga', c.productName " +
                       "FROM transaction a " +
                       "INNER JOIN detailtransaction b ON a.transactionID = b.transactionID " +
                       "INNER JOIN product c ON b.productID = c.productID " +
                       "INNER JOIN user d ON a.userID = d.userID " +
                       "WHERE d.userName = ?;";
    
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String [] arrString = new String[4];
                    arrString[0] = rs.getString("transaction_date");
                    arrString[1] = rs.getString("quantity"); 
                    arrString[2] = rs.getString("productName");
                    arrString[3] = rs.getString("Total Harga");
                    
                    purchaseHistory.add(arrString);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return purchaseHistory;
    }
    
}