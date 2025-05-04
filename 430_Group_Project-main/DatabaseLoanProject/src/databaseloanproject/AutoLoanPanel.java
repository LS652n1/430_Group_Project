package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class AutoLoanPanel extends JPanel {
    private JTable autoLoanTable;
    private DefaultTableModel tableModel;

    private JTextField ssnField;
    private JTextField loanIdField;
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField vinField;
    private JTextField amountField;
    private JTextField interestRateField;
    private JTextField amountPaidField;
    private JTextField startDateField;
    private JTextField numberOfPaymentsField;
    private JTextField endDateField;

    public AutoLoanPanel(Connection conn) {
        this.setLayout((LayoutManager)null);

        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(20, 20, 120, 25);
        ssnField = new JTextField();
        ssnField.setBounds(150, 20, 200, 25);

        JLabel loanIdLabel = new JLabel("Auto Loan ID:");
        loanIdLabel.setBounds(20, 50, 120, 25);
        loanIdField = new JTextField();
        loanIdField.setBounds(150, 50, 200, 25);

        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(20, 80, 120, 25);
        makeField = new JTextField();
        makeField.setBounds(150, 80, 200, 25);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(20, 110, 120, 25);
        modelField = new JTextField();
        modelField.setBounds(150, 110, 200, 25);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 140, 120, 25);
        yearField = new JTextField();
        yearField.setBounds(150, 140, 200, 25);

        JLabel vinLabel = new JLabel("VIN:");
        vinLabel.setBounds(20, 170, 120, 25);
        vinField = new JTextField();
        vinField.setBounds(150, 170, 200, 25);

        JLabel amountLabel = new JLabel("Loan Amount:");
        amountLabel.setBounds(400, 20, 120, 25);
        amountField = new JTextField();
        amountField.setBounds(530, 20, 200, 25);

        JLabel interestRateLabel = new JLabel("Interest Rate:");
        interestRateLabel.setBounds(400, 50, 120, 25);
        interestRateField = new JTextField();
        interestRateField.setBounds(530, 50, 200, 25);

        JLabel amountPaidLabel = new JLabel("Amount Paid:");
        amountPaidLabel.setBounds(400, 80, 120, 25);
        amountPaidField = new JTextField();
        amountPaidField.setBounds(530, 80, 200, 25);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateLabel.setBounds(400, 110, 180, 25);
        startDateField = new JTextField();
        startDateField.setBounds(580, 110, 150, 25);

        JLabel numberOfPaymentsLabel = new JLabel("Number of Payments:");
        numberOfPaymentsLabel.setBounds(400, 140, 180, 25);
        numberOfPaymentsField = new JTextField();
        numberOfPaymentsField.setBounds(580, 140, 150, 25);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateLabel.setBounds(400, 170, 180, 25);
        endDateField = new JTextField();
        endDateField.setBounds(580, 170, 150, 25);

        JButton addButton = new JButton("Add Auto Loan");
        addButton.setBounds(150, 210, 150, 30);
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement(
                        "INSERT INTO AutoLoan (Auto_Loan_ID, Make, Model, Year, VIN, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                stmt1.setInt(1, Integer.parseInt(loanIdField.getText().trim()));
                stmt1.setString(2, makeField.getText().trim());
                stmt1.setString(3, modelField.getText().trim());
                stmt1.setInt(4, Integer.parseInt(yearField.getText().trim()));
                stmt1.setString(5, vinField.getText().trim());
                stmt1.setDouble(6, Double.parseDouble(amountField.getText().trim()));
                stmt1.setDouble(7, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(8, Double.parseDouble(amountPaidField.getText().trim()));
                stmt1.setString(9, startDateField.getText().trim());
                stmt1.setInt(10, Integer.parseInt(numberOfPaymentsField.getText().trim()));
                stmt1.setString(11, endDateField.getText().trim());
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO TakesAutoLoan (SSN, Auto_Loan_ID) VALUES (?, ?)");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(loanIdField.getText().trim()));
                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Auto loan added and linked successfully.");
                clearFields();
                loadAutoLoans(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        JButton editButton = new JButton("Edit Auto Loan");
        editButton.setBounds(310, 210, 150, 30);
        editButton.addActionListener((e) -> {
            try {
                int loanId = Integer.parseInt(loanIdField.getText().trim());
                String make = makeField.getText().trim();
                String model = modelField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());
                String vin = vinField.getText().trim();
                double amount = Double.parseDouble(amountField.getText().trim());
                double interestRate = Double.parseDouble(interestRateField.getText().trim());
                double amountPaid = Double.parseDouble(amountPaidField.getText().trim());
                String startDate = startDateField.getText().trim();
                int numberOfPayments = Integer.parseInt(numberOfPaymentsField.getText().trim());
                String endDate = endDateField.getText().trim();

                String sql = "UPDATE AutoLoan SET Make = ?, Model = ?, Year = ?, VIN = ?, Loan_Amount = ?, Interest_Rate = ?, Amount_Paid = ?, Start_Date = ?, Number_of_Payments = ?, End_Date = ? WHERE Auto_Loan_ID = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, make);
                stmt.setString(2, model);
                stmt.setInt(3, year);
                stmt.setString(4, vin);
                stmt.setDouble(5, amount);
                stmt.setDouble(6, interestRate);
                stmt.setDouble(7, amountPaid);
                stmt.setString(8, startDate);
                stmt.setInt(9, numberOfPayments);
                stmt.setString(10, endDate);
                stmt.setInt(11, loanId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Auto loan updated successfully.");
                    clearFields();
                    loadAutoLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Auto Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        JButton deleteButton = new JButton("Delete Auto Loan");
        deleteButton.setBounds(470, 210, 150, 30);
        deleteButton.addActionListener((e) -> {
            try {
                int loanId = Integer.parseInt(loanIdField.getText().trim());

                String sqlDeleteTakes = "DELETE FROM TakesAutoLoan WHERE Auto_Loan_ID = ?";
                PreparedStatement stmtDeleteTakes = conn.prepareStatement(sqlDeleteTakes);
                stmtDeleteTakes.setInt(1, loanId);
                stmtDeleteTakes.executeUpdate();

                String sqlDeleteLoan = "DELETE FROM AutoLoan WHERE Auto_Loan_ID = ?";
                PreparedStatement stmtDeleteLoan = conn.prepareStatement(sqlDeleteLoan);
                stmtDeleteLoan.setInt(1, loanId);
                int rowsAffected = stmtDeleteLoan.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Auto loan deleted successfully.");
                    clearFields();
                    loadAutoLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Auto Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        String[] columnNames = {"SSN", "Auto Loan ID", "Make", "Model", "Year", "VIN", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        autoLoanTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(autoLoanTable);
        scrollPane.setBounds(20, 260, 760, 300);

        this.add(ssnLabel);
        this.add(ssnField);
        this.add(loanIdLabel);
        this.add(loanIdField);
        this.add(makeLabel);
        this.add(makeField);
        this.add(modelLabel);
        this.add(modelField);
        this.add(yearLabel);
        this.add(yearField);
        this.add(vinLabel);
        this.add(vinField);
        this.add(amountLabel);
        this.add(amountField);
        this.add(interestRateLabel);
        this.add(interestRateField);
        this.add(amountPaidLabel);
        this.add(amountPaidField);
        this.add(startDateLabel);
        this.add(startDateField);
        this.add(numberOfPaymentsLabel);
        this.add(numberOfPaymentsField);
        this.add(endDateLabel);
        this.add(endDateField);

        this.add(addButton);
        this.add(editButton);
        this.add(deleteButton);

        this.add(scrollPane);

        loadAutoLoans(conn);

        autoLoanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && autoLoanTable.getSelectedRow() != -1) {
                int selectedRow = autoLoanTable.getSelectedRow();
                ssnField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                loanIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                makeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                modelField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                yearField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                vinField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                amountField.setText(tableModel.getValueAt(selectedRow, 6).toString());
                interestRateField.setText(tableModel.getValueAt(selectedRow, 7).toString());
                amountPaidField.setText(tableModel.getValueAt(selectedRow, 8).toString());
                startDateField.setText(tableModel.getValueAt(selectedRow, 9).toString());
                numberOfPaymentsField.setText(tableModel.getValueAt(selectedRow, 10).toString());
                endDateField.setText(tableModel.getValueAt(selectedRow, 11).toString());
            }
        });
    }

    private void loadAutoLoans(Connection conn) {
        try {
            this.tableModel.setRowCount(0);

            if (conn != null) {
                String sql = "SELECT ta.SSN, al.Auto_Loan_ID, al.Make, al.Model, al.Year, al.VIN, al.Loan_Amount, al.Interest_Rate, al.Amount_Paid, al.Start_Date, al.Number_of_Payments, al.End_Date " +
                        "FROM AutoLoan al " +
                        "JOIN TakesAutoLoan ta ON al.Auto_Loan_ID = ta.Auto_Loan_ID";
                ResultSet rs = conn.createStatement().executeQuery(sql);

                while(rs.next()) {
                    this.tableModel.addRow(new Object[]{
                            rs.getLong("SSN"),
                            rs.getInt("Auto_Loan_ID"),
                            rs.getString("Make"),
                            rs.getString("Model"),
                            rs.getInt("Year"),
                            rs.getString("VIN"),
                            rs.getDouble("Loan_Amount"),
                            rs.getDouble("Interest_Rate"),
                            rs.getDouble("Amount_Paid"),
                            rs.getString("Start_Date"),
                            rs.getInt("Number_of_Payments"),
                            rs.getString("End_Date")
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading auto loans: " + e.getMessage());
        }
    }

    private void clearFields() {
        ssnField.setText("");
        loanIdField.setText("");
        makeField.setText("");
        modelField.setText("");
        yearField.setText("");
        vinField.setText("");
        amountField.setText("");
        interestRateField.setText("");
        amountPaidField.setText("");
        startDateField.setText("");
        numberOfPaymentsField.setText("");
        endDateField.setText("");
    }
}
