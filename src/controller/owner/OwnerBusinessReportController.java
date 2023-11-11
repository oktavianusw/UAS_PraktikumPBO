package controller.owner;

import controller.Connector;
import java.sql.*;

public class OwnerBusinessReportController {
    private Connection conn;

    public OwnerBusinessReportController() {
        this.conn = Connector.getInstance().getConnection();
    }

    public double getTotalMoneyEarned() {
        double totalMoneyEarned = 0;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");

            while (rs.next()) {
                int productID = rs.getInt("productID");
                double productPrice = rs.getDouble("productPrice");

                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(
                        "SELECT SUM(quantity) as totalQuantity FROM detailtransaction WHERE productID = " + productID);

                int quantitySold = 0;
                if (rs2.next()) {
                    quantitySold = rs2.getInt("totalQuantity");
                }

                double moneyEarned = quantitySold * productPrice;
                totalMoneyEarned += moneyEarned;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalMoneyEarned;
    }
}