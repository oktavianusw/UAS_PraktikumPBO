package view.owner;

import controller.owner.OwnerBusinessReportController;
import javax.swing.*;

public class OwnerBusinessReportPage extends JFrame {
    private OwnerBusinessReportController controller;

    public OwnerBusinessReportPage() {
        this.controller = new OwnerBusinessReportController();

        setTitle("Business Report");
        setSize(365, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Business Report");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(-5, 10, getWidth(), 35);
        panel.add(titleLabel);

        JLabel totalMoneyLabel = new JLabel("Total Money Earned: " + controller.getTotalMoneyEarned());
        totalMoneyLabel.setHorizontalAlignment(JLabel.CENTER);
        totalMoneyLabel.setBounds(-5, 60, getWidth(), 35);
        panel.add(totalMoneyLabel);
    }
}