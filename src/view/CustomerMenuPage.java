package view;

import javax.swing.*;

public class CustomerMenuPage extends JFrame {
    public CustomerMenuPage(String username) {
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
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 10, getWidth(), 35);
        panel.add(welcomeLabel);

        JButton viewProductsButton = new JButton("Catalogue / View Products");
        viewProductsButton.setBounds(50, 50, 250, 35);
        panel.add(viewProductsButton);

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(50, 100, 250, 35);
        panel.add(viewCartButton);

        JButton membershipMenuButton = new JButton("Membership Menu");
        membershipMenuButton.setBounds(50, 150, 250, 35);
        panel.add(membershipMenuButton);

        JButton topUpButton = new JButton("Top Up");
        topUpButton.setBounds(50, 200, 250, 35);
        panel.add(topUpButton);

        JButton purchaseHistoryButton = new JButton("Purchase History");
        purchaseHistoryButton.setBounds(50, 250, 250, 35);
        panel.add(purchaseHistoryButton);

        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setBounds(50, 300, 250, 35);
        panel.add(editProfileButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 350, 250, 35);
        panel.add(logoutButton);

        logoutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(CustomerMenuPage.this,
                    "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                new LoginPage();
                dispose();
            }
        });
    }
}