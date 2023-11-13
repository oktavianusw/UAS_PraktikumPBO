package controller.owner;

import controller.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerBusinessReportController {
    private Connection conn;

    public OwnerBusinessReportController() {
        this.conn = Connector.getInstance().getConnection();
    }

    public Object[][] getBusinessReportData() {
        List<Object[]> reportData = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM warehouse");
            while (rs.next()) {
                String warehouseName = rs.getString("warehouseName");
                int warehouseID = rs.getInt("warehouseID");

                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(
                        "SELECT product.productName, productlist.quantity AS 'Product Quantity Left', " +
                                "SUM(detailtransaction.quantity) AS 'Products Sold', " +
                                "SUM(detailtransaction.quantity * product.productPrice) AS 'Total Income' " +
                                "FROM productlist " +
                                "JOIN product ON productlist.productID = product.productID " +
                                "LEFT JOIN detailtransaction ON product.productID = detailtransaction.productID " +
                                "WHERE productlist.warehouseID = " + warehouseID +
                                " GROUP BY product.productName");

                while (rs2.next()) {
                    String productName = rs2.getString("productName");
                    int productQuantityLeft = rs2.getInt("Product Quantity Left");
                    int productsSold = rs2.getInt("Products Sold");
                    double totalIncome = rs2.getDouble("Total Income");

                    reportData.add(new Object[] { warehouseName, productName, productQuantityLeft, productsSold,
                            totalIncome });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reportData.toArray(new Object[0][]);
    }
}