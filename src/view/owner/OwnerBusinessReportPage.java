package view.owner;

import controller.owner.OwnerBusinessReportController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OwnerBusinessReportPage extends JFrame {
    private OwnerBusinessReportController controller;

    public OwnerBusinessReportPage() {
        this.controller = new OwnerBusinessReportController();

        setTitle("Business Report");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = { "Warehouse Name", "Product Name", "Product Quantity Left", "Products Sold", "Money Earned" };
        Object[][] data = controller.getBusinessReportData();

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}