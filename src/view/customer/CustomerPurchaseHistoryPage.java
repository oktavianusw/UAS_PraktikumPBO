package view.customer;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

import controller.customer.CustomerPurchaseHistoryController;

public class CustomerPurchaseHistoryPage extends JFrame {

    public CustomerPurchaseHistoryPage(String currentUsername, JFrame mainMenu) {
        mainMenu.dispose();

        setTitle("Purchase History");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        add(panel);
        setVisible(true);
        initComponents(panel, currentUsername);

    }

    private void initComponents(JPanel panel, String currentUsername) {
        panel.setLayout(null);

        ArrayList<String[]> purchaseHistory = new ArrayList<>();
        CustomerPurchaseHistoryController con = new CustomerPurchaseHistoryController();
        purchaseHistory = con.getPurchaseHistory(currentUsername);

        JPanel contentPanel = new JPanel(); 
        contentPanel.setLayout(null);

        if (purchaseHistory != null) {
            int counterY = 0;
            for (String[] arrPurchaseHistory : purchaseHistory) {

                String transactionDate = arrPurchaseHistory[0];
                String quantity = arrPurchaseHistory[1];
                String productName = arrPurchaseHistory[2];
                String total = arrPurchaseHistory[3];

                JLabel dateLabel = new JLabel("Tanggal: ");
                dateLabel.setBounds(8, 1 + counterY, 100, 25);
                contentPanel.add(dateLabel);

                JTextField dateField = new JTextField(transactionDate);
                dateField.setBounds(60, 3 + counterY, 100, 20);
                dateField.setEditable(false);
                dateField.setBorder(null);
                dateField.setBackground(null);
                contentPanel.add(dateField);

                JLabel quantityLabel = new JLabel("Qty: ");
                quantityLabel.setBounds(308, 1 + counterY, 100, 20);
                contentPanel.add(quantityLabel);

                JTextField quantityField = new JTextField(quantity);
                quantityField.setBounds(333, 2 + counterY, 25, 20);
                quantityField.setEditable(false);
                quantityField.setBorder(null);
                contentPanel.add(quantityField);

                JTextField productNameField = new JTextField(productName);
                productNameField.setBounds(8, 25 + counterY, 393, 25);
                productNameField.setEditable(false);
                contentPanel.add(productNameField);

                JLabel totalHargaLabel = new JLabel("Total Harga: ");
                totalHargaLabel.setBounds(8, 45 + counterY, 100, 25);
                contentPanel.add(totalHargaLabel);

                JTextField totalHargaField = new JTextField(total);
                totalHargaField.setBounds(80, 49 + counterY, 245, 18);
                totalHargaField.setEditable(false);
                totalHargaField.setBorder(null);
                contentPanel.add(totalHargaField);

                counterY += 75;
            }
            contentPanel.setPreferredSize(new Dimension(358, counterY));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(10, 10, 380, 130);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        int buttonWidth = 80;
        int buttonHeight = 25;
        int buttonX = (getWidth() - buttonWidth) / 2 + 45;
        int buttonY = 110;

        JButton backButton = new JButton("Back");
        backButton.setBounds(buttonX + 105, buttonY + 43, buttonWidth, buttonHeight);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            new CustomerMenuPage(currentUsername);
            dispose();
        });

    }

}
