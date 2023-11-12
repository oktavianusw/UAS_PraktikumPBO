package view.customer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.customer.CustomerAddToCartController;

public class CustomerViewProductPage extends JFrame {
    private JTable productTable;
    private String username;

    public CustomerViewProductPage(String username, JFrame menu) {
        menu.dispose();
        this.username = username;
        setTitle("Product List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/product_warehouse_management", "root", "");
            String query = "SELECT * FROM Product";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Get column names
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productTable = new JTable(data, columnNames);

        TableColumn buttonColumn = new TableColumn();
        buttonColumn.setHeaderValue("Add to Cart");
        buttonColumn.setCellRenderer(new ButtonRenderer());
        buttonColumn.setCellEditor(new ButtonEditor(new JTextField(), this));

        productTable.addColumn(buttonColumn);

        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private JFrame parentFrame;

        public ButtonEditor(JTextField textField, JFrame parentFrame) {
            super(textField);
            this.button = new JButton("Add to Cart");
            this.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = productTable.getSelectedRow();
                    if (row != -1) {
                        int productID = (int) productTable.getValueAt(row, 0);
                        new CustomerAddToCartPage(username, productID);
                        dispose();
                    }
                }
            });
            this.parentFrame = parentFrame;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

}
