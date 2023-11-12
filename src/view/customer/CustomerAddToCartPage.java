package view.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.customer.CustomerAddToCartController;

public class CustomerAddToCartPage extends JFrame {
    private String username;
    private int productID;

    public CustomerAddToCartPage(String username, int productID) {
        this.username = username;
        this.productID = productID;

        setTitle("Add to Cart");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 2));

        JLabel productLabel = new JLabel("Product ID: " + productID);
        panel.add(productLabel);

        JLabel quantityLabel = new JLabel("Quantity:");
        panel.add(quantityLabel);

        JTextField quantityField = new JTextField(10);
        panel.add(quantityField);

        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityField.getText());
                CustomerAddToCartController controller = new CustomerAddToCartController();
                boolean success = controller.addToCart(username, productID, quantity);

                if(success){
                    JOptionPane.showMessageDialog(CustomerAddToCartPage.this, "Product added to cart successfully!");
                    new CustomerViewProductPage(username, CustomerAddToCartPage.this);
                    dispose();
                }
            }
        });
        panel.add(addButton);
    }
}