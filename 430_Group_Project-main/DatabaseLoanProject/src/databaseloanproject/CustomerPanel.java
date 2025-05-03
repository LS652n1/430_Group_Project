package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class CustomerPanel extends JPanel {
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerPanel(Connection conn) {
        this.setLayout((LayoutManager)null);
        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(20, 20, 80, 25);
        JTextField ssnField = new JTextField();

        System.out.print("Reached here in CustomerPanel");

        ssnField.setBounds(100, 20, 200, 25);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 60, 200, 25);
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setBounds(20, 100, 80, 25);
        JTextField incomeField = new JTextField();
        incomeField.setBounds(100, 100, 200, 25);
        JButton addButton = new JButton("Add Customer");
        addButton.setBounds(100, 140, 150, 30);
        addButton.addActionListener((e) -> {
            try {
                long ssn = Long.parseLong(ssnField.getText().trim());
                String name = nameField.getText().trim();
                double income = Double.parseDouble(incomeField.getText().trim());
                String sql = "INSERT INTO Client (SSN, Name, Income) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setLong(1, ssn);
                stmt.setString(2, name);
                stmt.setDouble(3, income);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Customer added successfully.");
                ssnField.setText("");
                nameField.setText("");
                incomeField.setText("");
                this.loadCustomers(conn);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }

        });

        JButton editButton = new JButton("Edit Customer");
        editButton.setBounds(300, 140, 150, 30);
        editButton.addActionListener((e) -> {
           try {
               long ssn = Long.parseLong(ssnField.getText().trim());
               String name = nameField.getText().trim();
               double income = Double.parseDouble(incomeField.getText().trim());
               String sql = "UPDATE Client SET Name = ?, Income = ? WHERE SSN = ?";
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1, name);
               stmt.setDouble(2, income);
               stmt.setLong(3, ssn);
               stmt.executeUpdate();
               JOptionPane.showMessageDialog((Component)null, "Customer updated successfully.");
               ssnField.setText("");
               nameField.setText("");
               incomeField.setText("");
               this.loadCustomers(conn);
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
           }
        });

        JButton deleteButton = new JButton("Delete Customer");
        deleteButton.setBounds(500, 140, 150, 30);
        deleteButton.addActionListener((e) -> {
            try {
                long ssn = Long.parseLong(ssnField.getText().trim());
                String sql = "delete from Client where ssn = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setLong(1, ssn);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Customer deleted successfully.");
                ssnField.setText("");
                nameField.setText("");
                incomeField.setText("");
                this.loadCustomers(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }
        });

        this.tableModel = new DefaultTableModel(new String[]{"SSN", "Name", "Income"}, 0);
        this.customerTable = new JTable(this.tableModel);
        JScrollPane scrollPane = new JScrollPane(this.customerTable);
        scrollPane.setBounds(20, 200, 740, 300);
        this.loadCustomers(conn);
        this.add(ssnLabel);
        this.add(ssnField);
        this.add(nameLabel);
        this.add(nameField);
        this.add(incomeLabel);
        this.add(incomeField);
        this.add(addButton);
        this.add(editButton);
        this.add(deleteButton);
        this.add(scrollPane);
    }

    private void loadCustomers(Connection conn) {
        try {
            this.tableModel.setRowCount(0);
            if (conn != null) {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Client");

                while(rs.next()) {
                    this.tableModel.addRow(new Object[]{rs.getLong("SSN"), rs.getString("Name"), rs.getDouble("Income")});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog((Component)null, "Error loading customers: " + e.getMessage());
        }

    }
}
