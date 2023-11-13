package view;

import javax.swing.*;
import controller.*;
import view.customer.CustomerMenuPage;
import view.owner.OwnerMenuPage;
import view.staffonline.StaffOnlineMenuPage;
import view.staffonsite.StaffOnsiteMenuPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // * FRAME
    public LoginPage() {
        // Set the title of the frame
        setTitle("Login");
        // Set the size of the frame
        setSize(320, 200);
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        // Create and position the password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        // Create and position the password field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        // Create and position the login button
        loginButton = new JButton("Login");
        loginButton.setBounds(160, 110, 80, 25);
        panel.add(loginButton);

        // Create and position the sign up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(60, 110, 80, 25);
        panel.add(signUpButton);

        // * LISTENERS
        // Add an action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the username and password from the fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate the user
                String userType = LoginController.authenticateUser(username, password);
                if (userType != null) {
                    // If the user is authenticated, show a welcome message
                    JOptionPane.showMessageDialog(LoginPage.this, "Welcome, " + username + "!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    if ("Owner".equals(userType)) {
                        // If the user is an owner, open the owner menu page
                        new OwnerMenuPage(username);
                    } else if ("Customer".equals(userType)) {
                        // If the user is a customer, open the customer menu page
                        new CustomerMenuPage(username);
                    }
                    else if ("StaffOnline".equals(userType)) {
                    // If the user is an Online Staff, open the Online Staff menu page
                    new StaffOnlineMenuPage(username);
                    } else if ("StaffOnsite".equals(userType)) {
                    // If the user is an Onsite Staff, open the Onsite Staff menu page
                    new StaffOnsiteMenuPage(username);
                    // }
                    // Dispose the current frame
                    dispose();
                }
                } else {
                    // If the user is not authenticated, show an error message
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add an action listener to the sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the register page
                new RegisterPage();
                // Hide the current frame
                LoginPage.this.setVisible(false);
            }
        });
    }
}