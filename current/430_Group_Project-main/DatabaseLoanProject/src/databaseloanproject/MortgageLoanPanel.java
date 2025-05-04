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

class MortgageLoanPanel extends JPanel {
    private JTable mortgageLoanTable;
    private DefaultTableModel tableModel;

    private JTextField ssnField;
    private JTextField mortgageIdField;
    private JTextField addressField;
    private JTextField houseAreaField;
    private JTextField numberOfBedroomsField;
    private JTextField housePriceField;
    private JTextField loanAmountField;
    private JTextField interestRateField;
    private JTextField amountPaidField;
    private JTextField startDateField;
    private JTextField numberOfPaymentsField;
    private JTextField endDateField;

    public MortgageLoanPanel(Connection conn) {
        this.setLayout((LayoutManager)null);

        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(20, 20, 120, 25);
        ssnField = new JTextField();
        ssnField.setBounds(150, 20, 200, 25);

        JLabel mortgageIdLabel = new JLabel("Mortgage ID:");
        mortgageIdLabel.setBounds(20, 50, 120, 25);
        mortgageIdField = new JTextField();
        mortgageIdField.setBounds(150, 50, 200, 25);

        JLabel addressLabel = new JLabel("House Address:");
        addressLabel.setBounds(20, 80, 120, 25);
        addressField = new JTextField();
        addressField.setBounds(150, 80, 200, 25);

        JLabel houseAreaLabel = new JLabel("House Area:");
        houseAreaLabel.setBounds(20, 110, 120, 25);
        houseAreaField = new JTextField();
        houseAreaField.setBounds(150, 110, 200, 25);

        JLabel numberOfBedroomsLabel = new JLabel("Number of Bedrooms:");
        numberOfBedroomsLabel.setBounds(20, 140, 150, 25);
        numberOfBedroomsField = new JTextField();
        numberOfBedroomsField.setBounds(180, 140, 170, 25);

        JLabel housePriceLabel = new JLabel("House Price:");
        housePriceLabel.setBounds(20, 170, 120, 25);
        housePriceField = new JTextField();
        housePriceField.setBounds(150, 170, 200, 25);

        JLabel loanAmountLabel = new JLabel("Loan Amount:");
        loanAmountLabel.setBounds(400, 20, 120, 25);
        loanAmountField = new JTextField();
        loanAmountField.setBounds(530, 20, 200, 25);

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

        JButton addButton = new JButton("Add Mortgage Loan");
        addButton.setBounds(150, 210, 150, 30);
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement(
                        "INSERT INTO Mortgage (Mortgage_ID, House_Address, House_Area, Number_of_bedrooms, House_Price, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                stmt1.setInt(1, Integer.parseInt(mortgageIdField.getText().trim()));
                stmt1.setString(2, addressField.getText().trim());
                stmt1.setDouble(3, Double.parseDouble(houseAreaField.getText().trim()));
                stmt1.setInt(4, Integer.parseInt(numberOfBedroomsField.getText().trim()));
                stmt1.setDouble(5, Double.parseDouble(housePriceField.getText().trim()));
                stmt1.setDouble(6, Double.parseDouble(loanAmountField.getText().trim()));
                stmt1.setDouble(7, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(8, Double.parseDouble(amountPaidField.getText().trim()));
                stmt1.setString(9, startDateField.getText().trim());
                stmt1.setInt(10, Integer.parseInt(numberOfPaymentsField.getText().trim()));
                stmt1.setString(11, endDateField.getText().trim());
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO TakesMortgage (SSN, Mortgage_ID) VALUES (?, ?)");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(mortgageIdField.getText().trim()));
                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Mortgage loan added and linked successfully.");
                clearFields();
                loadMortgageLoans(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        JButton editButton = new JButton("Edit Mortgage Loan");
        editButton.setBounds(310, 210, 150, 30);
        editButton.addActionListener((e) -> {
            try {
                int mortgageId = Integer.parseInt(mortgageIdField.getText().trim());
                String address = addressField.getText().trim();
                double houseArea = Double.parseDouble(houseAreaField.getText().trim());
                int numberOfBedrooms = Integer.parseInt(numberOfBedroomsField.getText().trim());
                double housePrice = Double.parseDouble(housePriceField.getText().trim());
                double loanAmount = Double.parseDouble(loanAmountField.getText().trim());
                double interestRate = Double.parseDouble(interestRateField.getText().trim());
                double amountPaid = Double.parseDouble(amountPaidField.getText().trim());
                String startDate = startDateField.getText().trim();
                int numberOfPayments = Integer.parseInt(numberOfPaymentsField.getText().trim());
                String endDate = endDateField.getText().trim();

                String sql = "UPDATE Mortgage SET House_Address = ?, House_Area = ?, Number_of_bedrooms = ?, House_Price = ?, Loan_Amount = ?, Interest_Rate = ?, Amount_Paid = ?, Start_Date = ?, Number_of_Payments = ?, End_Date = ? WHERE Mortgage_ID = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, address);
                stmt.setDouble(2, houseArea);
                stmt.setInt(3, numberOfBedrooms);
                stmt.setDouble(4, housePrice);
                stmt.setDouble(5, loanAmount);
                stmt.setDouble(6, interestRate);
                stmt.setDouble(7, amountPaid);
                stmt.setString(8, startDate);
                stmt.setInt(9, numberOfPayments);
                stmt.setString(10, endDate);
                stmt.setInt(11, mortgageId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Mortgage loan updated successfully.");
                    clearFields();
                    loadMortgageLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Mortgage ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        JButton deleteButton = new JButton("Delete Mortgage Loan");
        deleteButton.setBounds(470, 210, 150, 30);
        deleteButton.addActionListener((e) -> {
            try {
                int mortgageId = Integer.parseInt(mortgageIdField.getText().trim());

                String sqlDeleteTakes = "DELETE FROM TakesMortgage WHERE Mortgage_ID = ?";
                PreparedStatement stmtDeleteTakes = conn.prepareStatement(sqlDeleteTakes);
                stmtDeleteTakes.setInt(1, mortgageId);
                stmtDeleteTakes.executeUpdate();

                String sqlDeleteMortgage = "DELETE FROM Mortgage WHERE Mortgage_ID = ?";
                PreparedStatement stmtDeleteMortgage = conn.prepareStatement(sqlDeleteMortgage);
                stmtDeleteMortgage.setInt(1, mortgageId);
                int rowsAffected = stmtDeleteMortgage.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Mortgage loan deleted successfully.");
                    clearFields();
                    loadMortgageLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Mortgage ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        String[] columnNames = {"SSN", "Mortgage ID", "House Address", "House Area", "Number of Bedrooms", "House Price", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        mortgageLoanTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(mortgageLoanTable);
        scrollPane.setBounds(20, 260, 760, 300);

        this.add(ssnLabel);
        this.add(ssnField);
        this.add(mortgageIdLabel);
        this.add(mortgageIdField);
        this.add(addressLabel);
        this.add(addressField);
        this.add(houseAreaLabel);
        this.add(houseAreaField);
        this.add(numberOfBedroomsLabel);
        this.add(numberOfBedroomsField);
        this.add(housePriceLabel);
        this.add(housePriceField);
        this.add(loanAmountLabel);
        this.add(loanAmountField);
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

        loadMortgageLoans(conn);

        mortgageLoanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && mortgageLoanTable.getSelectedRow() != -1) {
                int selectedRow = mortgageLoanTable.getSelectedRow();
                ssnField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                mortgageIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                addressField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                houseAreaField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                numberOfBedroomsField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                housePriceField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                loanAmountField.setText(tableModel.getValueAt(selectedRow, 6).toString());
                interestRateField.setText(tableModel.getValueAt(selectedRow, 7).toString());
                amountPaidField.setText(tableModel.getValueAt(selectedRow, 8).toString());
                startDateField.setText(tableModel.getValueAt(selectedRow, 9).toString());
                numberOfPaymentsField.setText(tableModel.getValueAt(selectedRow, 10).toString());
                endDateField.setText(tableModel.getValueAt(selectedRow, 11).toString());
            }
        });
    }

    private void loadMortgageLoans(Connection conn) {
        try {
            this.tableModel.setRowCount(0);

            if (conn != null) {
                String sql = "SELECT tm.SSN, m.Mortgage_ID, m.House_Address, m.House_Area, m.Number_of_bedrooms, m.House_Price, m.Loan_Amount, m.Interest_Rate, m.Amount_Paid, m.Start_Date, m.Number_of_Payments, m.End_Date " +
                        "FROM Mortgage m " +
                        "JOIN TakesMortgage tm ON m.Mortgage_ID = tm.Mortgage_ID";
                ResultSet rs = conn.createStatement().executeQuery(sql);

                while(rs.next()) {
                    this.tableModel.addRow(new Object[]{
                            rs.getLong("SSN"),
                            rs.getInt("Mortgage_ID"),
                            rs.getString("House_Address"),
                            rs.getDouble("House_Area"),
                            rs.getInt("Number_of_bedrooms"),
                            rs.getDouble("House_Price"),
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
            JOptionPane.showMessageDialog(this, "Error loading mortgage loans: " + e.getMessage());
        }
    }

    private void clearFields() {
        ssnField.setText("");
        mortgageIdField.setText("");
        addressField.setText("");
        houseAreaField.setText("");
        numberOfBedroomsField.setText("");
        housePriceField.setText("");
        loanAmountField.setText("");
        interestRateField.setText("");
        amountPaidField.setText("");
        startDateField.setText("");
        numberOfPaymentsField.setText("");
        endDateField.setText("");
    }
}
