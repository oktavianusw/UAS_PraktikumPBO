package view;

import java.sql.SQLException;

import javax.swing.*;
import controller.RegisterController;

public class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // * FRAME
    public RegisterPage() {
        // Set the title of the frame
        setTitle("Register");
        // Set the size of the frame
        setSize(360, 250);
        // Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a new JPanel
        JPanel panel = new JPanel();
        // Add the panel to the frame
        add(panel);
        // Place the components on the panel
        placeComponents(panel);

        // Center the frame
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    // * COMPONENTS
    private void placeComponents(JPanel panel) {
        // Set the layout manager to null (absolute positioning)
        panel.setLayout(null);

        // Create and position the user label
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Create and position the username field
        usernameField = new JTextField(20);
        usernameField.setBounds(140, 20, 165, 25);
        panel.add(usernameField);

        // Create and position the password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        // Create and position the password field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 60, 165, 25);
        panel.add(passwordField);

        // Create and position the confirm password label
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(10, 100, 120, 25);
        panel.add(confirmPasswordLabel);

        // Create and position the confirm password field
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBounds(140, 100, 165, 25);
        panel.add(confirmPasswordField);

        // Create and position the register button
        JButton registerButton = new JButton("Sign Up");
        registerButton.setBounds(190, 140, 80, 25);
        panel.add(registerButton);

        // Create and position the cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(80, 140, 80, 25);
        panel.add(cancelButton);

        // * LISTENERS
        // Add an action listener to the register button
        registerButton.addActionListener(e -> {
            boolean success = false;
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Validate the input
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(RegisterPage.this,
                        "Username, password and confirm password cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!username.matches("[a-zA-Z0-9_]+")) {
                JOptionPane.showMessageDialog(RegisterPage.this,
                        "Username can only contain letters, numbers, and underscores.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (username.length() < 3 || username.length() > 15) {
                JOptionPane.showMessageDialog(RegisterPage.this,
                        "Username must be between 3 and 15 characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (password.length() < 3 || password.length() > 15) {
                JOptionPane.showMessageDialog(RegisterPage.this,
                        "Password must be between 3 and 15 characters.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(RegisterPage.this, "Password and confirm password do not match.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                success = true;
            }

            RegisterController registerController = new RegisterController();

            if (success) {
                try {
                    // Try to register the user
                    registerController.signUp(username, password);
                    JOptionPane.showMessageDialog(RegisterPage.this,
                            "Registration successful! Welcome " + username + "!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Dispose the current frame and open the login page
                    this.dispose();
                    new LoginPage().setVisible(true);
                } catch (SQLException ex) {
                    // Show an error message if the registration failed
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Show an error message if the input validation failed
                JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add an action listener to the cancel button
        cancelButton.addActionListener(e -> {
            // Dispose the current frame and open the login page
            dispose();
            new LoginPage();
        });
    }
}