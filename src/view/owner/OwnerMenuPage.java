package view.owner;

import javax.swing.*;

import view.LoginPage;

public class OwnerMenuPage extends JFrame {
    public OwnerMenuPage(String username) {
        setTitle("Main Menu");
        setSize(365, 260);
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
        welcomeLabel.setBounds(-5, 10, getWidth(), 35);
        panel.add(welcomeLabel);

        JButton businessReportButton = new JButton("Business Report");
        businessReportButton.setBounds(50, 60, 250, 35);
        panel.add(businessReportButton);

        JButton staffManagementButton = new JButton("Staff Management");
        staffManagementButton.setBounds(50, 110, 250, 35);
        panel.add(staffManagementButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 160, 250, 35);
        panel.add(logoutButton);

        businessReportButton.addActionListener(e -> {
            new OwnerBusinessReportPage();
            this.dispose();
        });

        staffManagementButton.addActionListener(e -> {
            new OwnerStaffManagementPage();
            this.dispose();
        });

        logoutButton.addActionListener(e -> {
            Object[] options = { "No", "Yes" };
            int response = JOptionPane.showOptionDialog(OwnerMenuPage.this,
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