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

class PersonalLoanPanel extends JPanel {
    private JTable personalLoanTable;
    private DefaultTableModel tableModel;

    private JTextField ssnField;
    private JTextField personalLoanIdField;
    private JTextField purposeField;
    private JTextField loanAmountField;
    private JTextField interestRateField;
    private JTextField amountPaidField;
    private JTextField startDateField;
    private JTextField numberOfPaymentsField;
    private JTextField endDateField;

    public PersonalLoanPanel(Connection conn) {
        this.setLayout((LayoutManager)null);

        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(20, 20, 120, 25);
        ssnField = new JTextField();
        ssnField.setBounds(150, 20, 200, 25);

        JLabel personalLoanIdLabel = new JLabel("Personal Loan ID:");
        personalLoanIdLabel.setBounds(20, 50, 120, 25);
        personalLoanIdField = new JTextField();
        personalLoanIdField.setBounds(150, 50, 200, 25);

        JLabel purposeLabel = new JLabel("Loan Purpose:");
        purposeLabel.setBounds(20, 80, 120, 25);
        purposeField = new JTextField();
        purposeField.setBounds(150, 80, 200, 25);

        JLabel loanAmountLabel = new JLabel("Loan Amount:");
        loanAmountLabel.setBounds(20, 110, 120, 25);
        loanAmountField = new JTextField();
        loanAmountField.setBounds(150, 110, 200, 25);

        JLabel interestRateLabel = new JLabel("Interest Rate:");
        interestRateLabel.setBounds(20, 140, 120, 25);
        interestRateField = new JTextField();
        interestRateField.setBounds(150, 140, 200, 25);

        JLabel amountPaidLabel = new JLabel("Amount Paid:");
        amountPaidLabel.setBounds(400, 20, 120, 25);
        amountPaidField = new JTextField();
        amountPaidField.setBounds(530, 20, 200, 25);

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateLabel.setBounds(400, 50, 180, 25);
        startDateField = new JTextField();
        startDateField.setBounds(580, 50, 150, 25);

        JLabel numberOfPaymentsLabel = new JLabel("Number of Payments:");
        numberOfPaymentsLabel.setBounds(400, 80, 180, 25);
        numberOfPaymentsField = new JTextField();
        numberOfPaymentsField.setBounds(580, 80, 150, 25);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateLabel.setBounds(400, 110, 180, 25);
        endDateField = new JTextField();
        endDateField.setBounds(580, 110, 150, 25);


        JButton addButton = new JButton("Add Personal Loan");
        addButton.setBounds(150, 180, 150, 30);
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement(
                        "INSERT INTO PersonalLoan (Personal_Loan_ID, Loan_Purpose, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
                stmt1.setInt(1, Integer.parseInt(personalLoanIdField.getText().trim()));
                stmt1.setString(2, purposeField.getText().trim());
                stmt1.setDouble(3, Double.parseDouble(loanAmountField.getText().trim()));
                stmt1.setDouble(4, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(5, Double.parseDouble(amountPaidField.getText().trim()));
                stmt1.setString(6, startDateField.getText().trim());
                stmt1.setInt(7, Integer.parseInt(numberOfPaymentsField.getText().trim()));
                stmt1.setString(8, endDateField.getText().trim());
                stmt1.executeUpdate();

                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO TakesPersonalLoan (SSN, Personal_Loan_ID) VALUES (?, ?)");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(personalLoanIdField.getText().trim()));
                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Personal loan added and linked successfully.");
                clearFields();
                loadPersonalLoans(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        JButton editButton = new JButton("Edit Personal Loan");
        editButton.setBounds(310, 180, 150, 30);
        editButton.addActionListener((e) -> {
            try {
                int personalLoanId = Integer.parseInt(personalLoanIdField.getText().trim());
                String purpose = purposeField.getText().trim();
                double loanAmount = Double.parseDouble(loanAmountField.getText().trim());
                double interestRate = Double.parseDouble(interestRateField.getText().trim());
                double amountPaid = Double.parseDouble(amountPaidField.getText().trim());
                String startDate = startDateField.getText().trim();
                int numberOfPayments = Integer.parseInt(numberOfPaymentsField.getText().trim());
                String endDate = endDateField.getText().trim();

                String sql = "UPDATE PersonalLoan SET Loan_Purpose = ?, Loan_Amount = ?, Interest_Rate = ?, Amount_Paid = ?, Start_Date = ?, Number_of_Payments = ?, End_Date = ? WHERE Personal_Loan_ID = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, purpose);
                stmt.setDouble(2, loanAmount);
                stmt.setDouble(3, interestRate);
                stmt.setDouble(4, amountPaid);
                stmt.setString(5, startDate);
                stmt.setInt(6, numberOfPayments);
                stmt.setString(7, endDate);
                stmt.setInt(8, personalLoanId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Personal loan updated successfully.");
                    clearFields();
                    loadPersonalLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Personal Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        JButton deleteButton = new JButton("Delete Personal Loan");
        deleteButton.setBounds(470, 180, 150, 30);
        deleteButton.addActionListener((e) -> {
            try {
                int personalLoanId = Integer.parseInt(personalLoanIdField.getText().trim());

                String sqlDeleteTakes = "DELETE FROM TakesPersonalLoan WHERE Personal_Loan_ID = ?";
                PreparedStatement stmtDeleteTakes = conn.prepareStatement(sqlDeleteTakes);
                stmtDeleteTakes.setInt(1, personalLoanId);
                stmtDeleteTakes.executeUpdate();

                String sqlDeleteLoan = "DELETE FROM PersonalLoan WHERE Personal_Loan_ID = ?";
                PreparedStatement stmtDeleteLoan = conn.prepareStatement(sqlDeleteLoan);
                stmtDeleteLoan.setInt(1, personalLoanId);
                int rowsAffected = stmtDeleteLoan.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Personal loan deleted successfully.");
                    clearFields();
                    loadPersonalLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Personal Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        String[] columnNames = {"SSN", "Personal Loan ID", "Loan Purpose", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        personalLoanTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(personalLoanTable);
        scrollPane.setBounds(20, 220, 760, 300);

        this.add(ssnLabel);
        this.add(ssnField);
        this.add(personalLoanIdLabel);
        this.add(personalLoanIdField);
        this.add(purposeLabel);
        this.add(purposeField);
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

        loadPersonalLoans(conn);

        personalLoanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && personalLoanTable.getSelectedRow() != -1) {
                int selectedRow = personalLoanTable.getSelectedRow();
                ssnField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                personalLoanIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                purposeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                loanAmountField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                interestRateField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                amountPaidField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                startDateField.setText(tableModel.getValueAt(selectedRow, 6).toString());
                numberOfPaymentsField.setText(tableModel.getValueAt(selectedRow, 7).toString());
                endDateField.setText(tableModel.getValueAt(selectedRow, 8).toString());
            }
        });
    }

    private void loadPersonalLoans(Connection conn) {
        try {
            this.tableModel.setRowCount(0);

            if (conn != null) {
                String sql = "SELECT tpl.SSN, pl.Personal_Loan_ID, pl.Loan_Purpose, pl.Loan_Amount, pl.Interest_Rate, pl.Amount_Paid, pl.Start_Date, pl.Number_of_Payments, pl.End_Date " +
                        "FROM PersonalLoan pl " +
                        "JOIN TakesPersonalLoan tpl ON pl.Personal_Loan_ID = tpl.Personal_Loan_ID";
                ResultSet rs = conn.createStatement().executeQuery(sql);

                while(rs.next()) {
                    this.tableModel.addRow(new Object[]{
                            rs.getLong("SSN"),
                            rs.getInt("Personal_Loan_ID"),
                            rs.getString("Loan_Purpose"),
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
            JOptionPane.showMessageDialog(this, "Error loading personal loans: " + e.getMessage());
        }
    }

    private void clearFields() {
        ssnField.setText("");
        personalLoanIdField.setText("");
        purposeField.setText("");
        loanAmountField.setText("");
        interestRateField.setText("");
        amountPaidField.setText("");
        startDateField.setText("");
        numberOfPaymentsField.setText("");
        endDateField.setText("");
    }
}
