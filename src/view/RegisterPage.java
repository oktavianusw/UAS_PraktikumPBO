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
        setTitle("Register");
        setSize(360, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // * COMPONENTS
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(140, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 60, 165, 25);
        panel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(10, 100, 120, 25);
        panel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBounds(140, 100, 165, 25);
        panel.add(confirmPasswordField);

        JButton registerButton = new JButton("Sign Up");
        registerButton.setBounds(190, 140, 80, 25);
        panel.add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(80, 140, 80, 25);
        panel.add(cancelButton);

        // * LISTENERS
        registerButton.addActionListener(e -> {
            boolean success = false;
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

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
                    registerController.signUp(username, password);
                    JOptionPane.showMessageDialog(RegisterPage.this,
                            "Registration successful! Welcome " + username + "!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });
    }
}