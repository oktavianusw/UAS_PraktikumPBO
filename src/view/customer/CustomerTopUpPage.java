package view.customer;

import controller.customer.CustomerTopUpController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CustomerTopUpPage extends JFrame {
    private String username;

    public CustomerTopUpPage(String username, CustomerMenuPage menuPage) {
        this.username = username;

        setTitle("Top Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
        setVisible(true);

        menuPage.dispose();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Top Up Amount: ");
        int userLabelX = 220 / 2;
        userLabel.setBounds(userLabelX - 15, 20, 165, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        int userTextX = 135 / 2;
        userText.setBounds(userTextX - 10, 55, 165, 25);
        panel.add(userText);

        JButton topUpButton = new JButton("Top Up");
        int topUpButtonX = 130 / 2 + 90;
        topUpButton.setBounds(topUpButtonX - 10, 100, 80, 25);
        panel.add(topUpButton);

        JButton cancelButton = new JButton("Cancel");
        int cancelButtonX = topUpButtonX - 90;
        cancelButton.setBounds(cancelButtonX - 10, 100, 80, 25);
        panel.add(cancelButton);

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = userText.getText();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input a number.");
                } else {
                    try {
                        int amount = Integer.parseInt(input);
                        if (amount < 20000) {
                            JOptionPane.showMessageDialog(null, "Minimum top up amount is 20000.");
                        } else {
                            CustomerTopUpController controller = new CustomerTopUpController();
                            controller.topUpWallet(username, amount);
                            DecimalFormat df = new DecimalFormat("#");
                            String formattedAmount = df.format(amount);

                            JOptionPane.showMessageDialog(null,
                                    formattedAmount + " has been added to your account, thank you!");
                            new CustomerMenuPage(username);
                            dispose();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please input a whole number.");
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerMenuPage(username);
                dispose();
            }
        });
    }
}