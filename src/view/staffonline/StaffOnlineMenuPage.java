package view.staffonline;

import javax.swing.*;
import view.LoginPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffOnlineMenuPage extends JFrame {
    private String username; // The username of the logged-in staff

    public StaffOnlineMenuPage(String username) {
        this.username = username;

        setTitle("Staff Online Menu");
        setSize(365, 280);
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
        welcomeLabel.setBounds(10, 10, getWidth() - 20, 35);
        panel.add(welcomeLabel);

        JButton productEntryManagementButton = new JButton("Product Entry Management");
        productEntryManagementButton.setBounds(50, 60, 250, 35);
        panel.add(productEntryManagementButton);

        JButton memberManagementButton = new JButton("Member Management");
        memberManagementButton.setBounds(50, 110, 250, 35);
        panel.add(memberManagementButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 160, 250, 35);
        panel.add(logoutButton);

        productEntryManagementButton.addActionListener(e -> {
            // Add the action for Product Entry Management
            // You can open a new frame or perform other actions
            // Example: new ProductEntryManagementPage(username, this);
            showMessage("Product Entry Management functionality not implemented yet.");
        });

        memberManagementButton.addActionListener(e -> {
            // Add the action for Member Management
            // You can open a new frame or perform other actions
            // Example: new MemberManagementPage(username, this);
            showMessage("Member Management functionality not implemented yet.");
        });

        logoutButton.addActionListener(e -> {
            Object[] options = { "No", "Yes" };
            int response = JOptionPane.showOptionDialog(StaffOnlineMenuPage.this,
                    "Are you sure you want to logout?", "Logout",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            if (response == 1) {
                new LoginPage();
                dispose();
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
