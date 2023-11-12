package view.customer;

import view.LoginPage;
import javax.swing.*;
import java.text.DecimalFormat;
import controller.customer.CustomerMenuController;

// This class represents the customer menu page
public class CustomerMenuPage extends JFrame {
    private String username; // The username of the logged-in user

    // Constructor takes the username as a parameter
    public CustomerMenuPage(String username) {
        this.username = username; // Set the username

        setTitle("Main Menu"); // Set the title of the window
        setSize(365, 480); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation

        JPanel panel = new JPanel(); // Create a new panel
        add(panel); // Add the panel to the frame
        placeComponents(panel, username); // Place components on the panel

        setLocationRelativeTo(null); // Center the window

        setVisible(true); // Make the window visible
    }

    // This method places components on the panel
    private void placeComponents(JPanel panel, String username) {
        panel.setLayout(null); // Set the layout manager to null (absolute positioning)

        // Create and add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(-5, 10, getWidth(), 35);
        panel.add(welcomeLabel);

        // Create a controller and get the balance of the user
        CustomerMenuController controller = new CustomerMenuController();
        double balance = controller.getBalance(username);

        // Format the balance to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedBalance = df.format(balance);

        // Create and add a label showing the current balance
        JLabel currentBalance = new JLabel("Your current balance is: " + formattedBalance);
        currentBalance.setHorizontalAlignment(JLabel.CENTER);
        currentBalance.setBounds(-5, 30, getWidth(), 35);
        panel.add(currentBalance);

        // Create and add various buttons
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

        // Add action listeners to the buttons
        topUpButton.addActionListener(e -> {
            new CustomerTopUpPage(this.username, this);
            this.dispose();
        });

        viewProductsButton.addActionListener(e -> {
            new CustomerViewProductPage(username, this);
        });

        viewCartButton.addActionListener(e -> {
            new CustomerViewCartPage(username, this);
        });

        editProfileButton.addActionListener(e -> {
            new CustomerEditProfilePage(username, this);
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
    }
}