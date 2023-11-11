package view.customer;

import javax.swing.*;

import controller.customer.CustomerCheckOutController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CustomerViewCartPage extends JFrame {
    private JTable productTable;
    private String username;
    private JLabel messageLabel; // Added JLabel for displaying messages

    public CustomerViewCartPage(String username, JFrame menu) {
        menu.dispose();
        this.username = username;
        setTitle("Product List");
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

        // Fetch data from the database
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/product_warehouse_management", "root", "");
            String query = "SELECT product.productname as Produk, SUM(cart.quantity) as Quantity FROM cart " +
                    "JOIN user ON cart.userid = user.userid " +
                    "JOIN product ON cart.productid = product.productid " +
                    "WHERE user.username = ? " +
                    "GROUP BY product.productname";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            ResultSet resultSet = preparedStatement.executeQuery();

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Error fetching data from the database.");
        }

        productTable = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the checkout method from the controller
                CustomerCheckOutController checkoutController = new CustomerCheckOutController(
                        CustomerViewCartPage.this);
                if (checkoutController.checkout(username)) {
                    showMessage("Checkout successful!");
                } else {
                    showMessage("Checkout failed. Please try again.");
                }
            }
        });
        panel.add(checkoutButton, BorderLayout.LINE_START);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerMenuPage(username);
                dispose();
            }
        });
        panel.add(backButton, BorderLayout.PAGE_END);

        // Initialize and add the message label
        messageLabel = new JLabel();
        panel.add(messageLabel, BorderLayout.EAST);
    }

    // Method to update the message label
    public void showMessage(String message) {
        messageLabel.setText(message);
    }
}
