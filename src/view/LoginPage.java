package view;

import javax.swing.*;
import controller.*;
import view.customer.CustomerMenuPage;
import view.owner.OwnerMenuPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // * FRAME
    public LoginPage() {
        setTitle("Login");
        setSize(320, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(160, 110, 80, 25);
        panel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(60, 110, 80, 25);
        panel.add(signUpButton);

        // * LISTENERS
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String userType = LoginController.authenticateUser(username, password);
                if (userType != null) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Welcome, " + username + "!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    if ("Owner".equals(userType)) {
                        new OwnerMenuPage(username);
                    } else {
                        new CustomerMenuPage(username);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
                LoginPage.this.setVisible(false);
            }
        });
    }
}