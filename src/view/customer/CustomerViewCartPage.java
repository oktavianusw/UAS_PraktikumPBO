package view.customer;

import controller.Connector;
import controller.customer.CustomerViewCartController;
import model.Cart;
import model.Product;
import view.customer.CustomerMenuPage; // Import the CustomerMenuPage class

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerViewCartPage extends JFrame implements ActionListener {
    private static CustomerViewCartPage instance;

    private CustomerViewCartController customerViewCartController;

    private String username;
    private CustomerMenuPage customerMenuPage;

    private JLabel labelTitle;
    private JTable tableCart;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel labelTotal;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonCheckout;
    private JButton buttonClear;
    private JButton buttonBack; // Add a back button
    private JTextArea textAreaMessage;

    private CustomerViewCartPage(String username, CustomerMenuPage customerMenuPage) {
        this.username = username;
        this.customerMenuPage = customerMenuPage;

        customerViewCartController = CustomerViewCartController.getInstance();

        labelTitle = new JLabel("Your Cart");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        tableCart = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Product Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Warehouse ID");
        tableCart.setModel(tableModel);
        tableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(tableCart);

        labelTotal = new JLabel("Total: $0.00");
        labelTotal.setFont(new Font("Arial", Font.PLAIN, 18));
        labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        buttonAdd = new JButton("Add Product");
        buttonAdd.addActionListener(this);

        buttonRemove = new JButton("Remove Product");
        buttonRemove.addActionListener(this);

        buttonCheckout = new JButton("Checkout");
        buttonCheckout.addActionListener(this);

        buttonClear = new JButton("Clear Cart");
        buttonClear.addActionListener(this);

        buttonBack = new JButton("Back"); // Create a back button
        buttonBack.addActionListener(this);

        textAreaMessage = new JTextArea();
        textAreaMessage.setEditable(false);
        textAreaMessage.setLineWrap(true);
        textAreaMessage.setWrapStyleWord(true);

        setLayout(new BorderLayout());
        add(labelTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(labelTotal, BorderLayout.SOUTH);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(3, 2));
        panelButtons.add(buttonAdd);
        panelButtons.add(buttonRemove);
        panelButtons.add(buttonCheckout);
        panelButtons.add(buttonClear);
        panelButtons.add(buttonBack);

        add(panelButtons, BorderLayout.EAST);
        add(textAreaMessage, BorderLayout.WEST);

        setTitle("Customer View Cart Page");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static CustomerViewCartPage getInstance(String username, CustomerMenuPage customerMenuPage) {
        if (instance == null) {
            instance = new CustomerViewCartPage(username, customerMenuPage);
        }
        return instance;
    }

    public void showMessage(String message) {
        textAreaMessage.setText(message);
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        List<Cart> cartItems = customerViewCartController.getCartItems();
        double total = 0;

        for (Cart cartItem : cartItems) {
            int productID = cartItem.getProductID();
            Product product = CustomerViewCartController.getProductById(productID);
            if (product != null) {
                int quantity = product.getProductQuantity();

                Object[] row = new Object[5];
                row[0] = product.getProductID();
                row[1] = product.getProductName();
                row[2] = product.getProductPrice();
                row[3] = quantity;
                row[4] = product.getWarehouseID();
                tableModel.addRow(row);
                total += product.getProductPrice() * quantity;
            }
        }

        labelTotal.setText("Total: $" + String.format("%.2f", total));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            // TODO: Add product to cart
        } else if (e.getSource() == buttonRemove) {
            // TODO: Remove product from cart
        } else if (e.getSource() == buttonCheckout) {
            // Checkout the cart using the controller
            customerViewCartController.checkout(username);
        } else if (e.getSource() == buttonClear) {
            // TODO: Clear the cart
        } else if (e.getSource() == buttonBack) {
            // Return to the CustomerMenuPage when the back button is clicked
            customerMenuPage.setVisible(true);
            this.dispose();
        }
    }
}