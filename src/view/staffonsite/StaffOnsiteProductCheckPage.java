package view.staffonsite;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StaffOnsiteProductCheckPage extends JFrame {
    private JTextField productNameOrIdField;
    private JTable warehouseTable;

    public StaffOnsiteProductCheckPage() {
        setTitle("Onsite Product Check");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Product Name or ID:"));
        productNameOrIdField = new JTextField(20);
        inputPanel.add(productNameOrIdField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWarehouses();
            }
        });
        inputPanel.add(submitButton);
        panel.add(inputPanel, BorderLayout.NORTH);

        warehouseTable = new JTable();
        panel.add(new JScrollPane(warehouseTable), BorderLayout.CENTER);
    }

    private void showWarehouses() {
        String productNameOrId = productNameOrIdField.getText();

        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/product_warehouse_management", "root", "");
            String query = "SELECT w.warehouseName, quantity, isActive FROM productlist JOIN warehouse w ON w.warehouseID = productlist.warehouseID JOIN product p ON p.productID = productlist.productID WHERE p.productID = ? OR p.productName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productNameOrId);
            preparedStatement.setString(2, productNameOrId);
            ResultSet resultSet = preparedStatement.executeQuery();

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                row.add(resultSet.getInt("isActive") == 1 ? "On Sale" : "Not On Sale");
                data.add(row);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        warehouseTable.setModel(new DefaultTableModel(data, columnNames));
    }

}