package view.customer;

import view.LoginPage;
import javax.swing.*;
import java.text.DecimalFormat;
import controller.customer.CustomerMenuController;

public class CustomerMenuPage extends JFrame {
    private String username;

    public CustomerMenuPage(String username) {
        this.username = username;

        setTitle("Main Menu");
        setSize(365, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel, username);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel, String username) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(-5, 10, getWidth(), 35);
        panel.add(welcomeLabel);

        CustomerMenuController controller = new CustomerMenuController();
        double balance = controller.getBalance(username);

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedBalance = df.format(balance);

        JLabel currentBalance = new JLabel("Your current balance is: " + formattedBalance);
        currentBalance.setHorizontalAlignment(JLabel.CENTER);
        currentBalance.setBounds(-5, 30, getWidth(), 35);
        panel.add(currentBalance);

        JButton viewProductsButton = new JButton("Catalogue / View Products");
        viewProductsButton.setBounds(50, 80, 250, 35);
        panel.add(viewProductsButton);

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(50, 130, 250, 35);
        panel.add(viewCartButton);

        JButton membershipMenuButton = new JButton("Membership Menu");
        membershipMenuButton.setBounds(50, 180, 250, 35);
        panel.add(membershipMenuButton);

        JButton topUpButton = new JButton("Top Up");
        topUpButton.setBounds(50, 230, 250, 35);
        panel.add(topUpButton);

        JButton purchaseHistoryButton = new JButton("Purchase History");
        purchaseHistoryButton.setBounds(50, 280, 250, 35);
        panel.add(purchaseHistoryButton);

        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setBounds(50, 330, 250, 35);
        panel.add(editProfileButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 380, 250, 35);
        panel.add(logoutButton);

        topUpButton.addActionListener(e -> {
            new CustomerTopUpPage(this.username, this);
            this.dispose();
        });

        logoutButton.addActionListener(e -> {
            Object[] options = { "No", "Yes" };
            int response = JOptionPane.showOptionDialog(CustomerMenuPage.this,
                    "Are you sure you want to logout?", "Logout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            if (response == 1) {
                new LoginPage();
                dispose();
            }
        });

        purchaseHistoryButton.addActionListener(e -> new CustomerPurchaseHistoryPage(username, this));

        editProfileButton.addActionListener(e -> new CustomerEditProfilePage(username, this));
    }
}