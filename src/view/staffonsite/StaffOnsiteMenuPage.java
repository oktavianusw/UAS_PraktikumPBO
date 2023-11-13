package view.staffonsite;

import javax.swing.*;
import view.LoginPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffOnsiteMenuPage extends JFrame {
    private String username; // The username of the logged-in staff

    public StaffOnsiteMenuPage(String username) {
        this.username = username;

        setTitle("Staff Onsite Menu");
        setSize(365, 300);
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

        JButton productStorageButton = new JButton("Product Storage");
        productStorageButton.setBounds(50, 60, 250, 35);
        panel.add(productStorageButton);

        JButton productCheckButton = new JButton("Product Check");
        productCheckButton.setBounds(50, 110, 250, 35);
        panel.add(productCheckButton);

        JButton memberManagementButton = new JButton("Member Management");
        memberManagementButton.setBounds(50, 160, 250, 35);
        panel.add(memberManagementButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 210, 250, 35);
        panel.add(logoutButton);

        productStorageButton.addActionListener(e -> {
            // Add the action for Product Storage
            // You can open a new frame or perform other actions
            // Example: new ProductStoragePage(username, this);
            showMessage("Product Storage functionality not implemented yet.");
        });

        productCheckButton.addActionListener(e -> {
            // Add the action for Product Check
            // You can open a new frame or perform other actions
            // Example: new ProductCheckPage(username, this);
            showMessage("Product Check functionality not implemented yet.");
        });

        memberManagementButton.addActionListener(e -> {
            // Add the action for Member Management
            // You can open a new frame or perform other actions
            // Example: new MemberManagementPage(username, this);
            showMessage("Member Management functionality not implemented yet.");
        });

        logoutButton.addActionListener(e -> {
            Object[] options = { "No", "Yes" };
            int response = JOptionPane.showOptionDialog(StaffOnsiteMenuPage.this,
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
