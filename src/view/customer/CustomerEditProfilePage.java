package view.customer;

import javax.swing.*;

import controller.customer.CustomerEditProfileController;

public class CustomerEditProfilePage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;

    public CustomerEditProfilePage(String currentUsername, JFrame mainMenu) {
        mainMenu.dispose();

        setTitle("Edit Profile");
        setSize(420, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel, currentUsername);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel, String currentUsername) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("New Username");
        userLabel.setBounds(10, 20, 100, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(120, 20, 245, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("New Password");
        passwordLabel.setBounds(10, 50, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(120, 50, 245, 25);
        panel.add(passwordField);

        saveButton = new JButton("Save");
        int buttonWidth = 80;
        int buttonHeight = 25;
        int buttonX = (getWidth() - buttonWidth) / 2 + 45;
        int buttonY = 110;
        saveButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        panel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(buttonX - 90, buttonY, buttonWidth, buttonHeight);
        panel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            new CustomerMenuPage(currentUsername);
            dispose();
        });

        saveButton.addActionListener(e -> {
            String newUsername = usernameField.getText();
            String newPassword = new String(passwordField.getPassword());

            if (newUsername.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(CustomerEditProfilePage.this,
                        "Username and password cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!newUsername.matches("[a-zA-Z0-9_]+")) {
                JOptionPane.showMessageDialog(CustomerEditProfilePage.this,
                        "Username can only contain letters, numbers, and underscores.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (newUsername.length() < 3 || newUsername.length() > 15) {
                JOptionPane.showMessageDialog(CustomerEditProfilePage.this,
                        "Username must be between 3 and 15 characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (newPassword.length() < 3 || newPassword.length() > 15) {
                JOptionPane.showMessageDialog(CustomerEditProfilePage.this,
                        "Password must be between 3 and 15 characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                CustomerEditProfileController controller = new CustomerEditProfileController();
                boolean success = controller.updateProfile(currentUsername, newUsername, newPassword);

                if (success) {
                    JOptionPane.showMessageDialog(CustomerEditProfilePage.this, "Profile updated successfully");
                    new CustomerMenuPage(newUsername);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CustomerEditProfilePage.this, "Failed to update profile", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}