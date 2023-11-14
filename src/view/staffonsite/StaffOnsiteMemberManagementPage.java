package view.staffonsite;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.Connector;

public class StaffOnsiteMemberManagementPage extends JFrame {
    private JTable userTable;
    private String username;

    public StaffOnsiteMemberManagementPage(String username) {
        this.username = username;
        setTitle("Member Management");
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

        userTable = new JTable();
        panel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        showUsers();
    }

    private void showUsers() {
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/product_warehouse_management", "root", "");
            String query = "SELECT * FROM user WHERE role = 'Customer'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

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

        userTable.setModel(new DefaultTableModel(data, columnNames));
    }
}