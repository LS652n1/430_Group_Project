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

class StudentLoanPanel extends JPanel {
    private JTable studentLoanTable;
    private DefaultTableModel tableModel;

    private JTextField ssnField;
    private JTextField studentLoanIdField;
    private JTextField loanTermField;
    private JTextField disbursementDateField;
    private JTextField repaymentStartDateField;
    private JTextField repaymentEndDateField;
    private JTextField monthlyPaymentField;
    private JTextField gracePeriodField;
    private JTextField loanTypeField;
    private JTextField numberOfPaymentsField;

    public StudentLoanPanel(Connection conn) {
        this.setLayout((LayoutManager)null);

        JLabel ssnLabel = new JLabel("SSN:");
        ssnLabel.setBounds(20, 20, 150, 25);
        ssnField = new JTextField();
        ssnField.setBounds(180, 20, 200, 25);

        JLabel studentLoanIdLabel = new JLabel("Student Loan ID:");
        studentLoanIdLabel.setBounds(20, 50, 150, 25);
        studentLoanIdField = new JTextField();
        studentLoanIdField.setBounds(180, 50, 200, 25);

        JLabel loanTermLabel = new JLabel("Loan Term:");
        loanTermLabel.setBounds(20, 80, 150, 25);
        loanTermField = new JTextField();
        loanTermField.setBounds(180, 80, 200, 25);

        JLabel disbursementDateLabel = new JLabel("Disbursement Date (YYYY-MM-DD):");
        disbursementDateLabel.setBounds(20, 110, 200, 25);
        disbursementDateField = new JTextField();
        disbursementDateField.setBounds(230, 110, 150, 25);

        JLabel repaymentStartDateLabel = new JLabel("Repayment Start Date (YYYY-MM-DD):");
        repaymentStartDateLabel.setBounds(20, 140, 220, 25);
        repaymentStartDateField = new JTextField();
        repaymentStartDateField.setBounds(250, 140, 130, 25);

        JLabel repaymentEndDateLabel = new JLabel("Repayment End Date (YYYY-MM-DD):");
        repaymentEndDateLabel.setBounds(400, 20, 220, 25);
        repaymentEndDateField = new JTextField();
        repaymentEndDateField.setBounds(630, 20, 150, 25);

        JLabel monthlyPaymentLabel = new JLabel("Monthly Payment:");
        monthlyPaymentLabel.setBounds(400, 50, 150, 25);
        monthlyPaymentField = new JTextField();
        monthlyPaymentField.setBounds(630, 50, 150, 25);

        JLabel gracePeriodLabel = new JLabel("Grace Period:");
        gracePeriodLabel.setBounds(400, 80, 150, 25);
        gracePeriodField = new JTextField();
        gracePeriodField.setBounds(630, 80, 150, 25);

        JLabel loanTypeLabel = new JLabel("Loan Type:");
        loanTypeLabel.setBounds(400, 110, 150, 25);
        loanTypeField = new JTextField();
        loanTypeField.setBounds(630, 110, 150, 25);

        JLabel numberOfPaymentsLabel = new JLabel("Number of Payments:");
        numberOfPaymentsLabel.setBounds(400, 140, 150, 25);
        numberOfPaymentsField = new JTextField();
        numberOfPaymentsField.setBounds(630, 140, 150, 25);


        JButton addButton = new JButton("Add Student Loan");
        addButton.setBounds(180, 180, 150, 30);
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO StudentLoan (SSN,Student_Loan_ID, Loan_Term, Disbursement_Date, Repayment_Start_Date, Repayment_End_Date, Monthly_Payment, Grace_Period, Loan_Type, Number_of_Payments) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                stmt.setInt(1, Integer.parseInt(ssnField.getText()));
                stmt.setInt(2, Integer.parseInt(studentLoanIdField.getText().trim()));
                stmt.setString(3, loanTermField.getText().trim());
                stmt.setString(4, disbursementDateField.getText().trim());
                stmt.setString(5, repaymentStartDateField.getText().trim());
                stmt.setString(6, repaymentEndDateField.getText().trim());
                stmt.setDouble(7, Double.parseDouble(monthlyPaymentField.getText().trim()));
                stmt.setString(8, gracePeriodField.getText().trim());
                stmt.setString(9, loanTypeField.getText().trim());
                stmt.setInt(10, Integer.parseInt(numberOfPaymentsField.getText().trim()));
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Student loan added and linked successfully.");
                clearFields();
                loadStudentLoans(conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        JButton editButton = new JButton("Edit Student Loan");
        editButton.setBounds(340, 180, 150, 30);
        editButton.addActionListener((e) -> {
            try {
                int ssn = Integer.parseInt(ssnField.getText().trim());
                int studentLoanId = Integer.parseInt(studentLoanIdField.getText().trim());
                String loanTerm = loanTermField.getText().trim();
                String disbursementDate = disbursementDateField.getText().trim();
                String repaymentStartDate = repaymentStartDateField.getText().trim();
                String repaymentEndDate = repaymentEndDateField.getText().trim();
                double monthlyPayment = Double.parseDouble(monthlyPaymentField.getText().trim());
                String gracePeriod = gracePeriodField.getText().trim();
                String loanType = loanTypeField.getText().trim();
                int numberOfPayments = Integer.parseInt(numberOfPaymentsField.getText().trim());

                String sql = "UPDATE StudentLoan SET Loan_Term = ?, Disbursement_Date = ?, Repayment_Start_Date = ?, Repayment_End_Date = ?, Monthly_Payment = ?, Grace_Period = ?, Loan_Type = ?, Number_of_Payments = ? WHERE SSN = ? and Student_Loan_ID = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, loanTerm);
                stmt.setString(2, disbursementDate);
                stmt.setString(3, repaymentStartDate);
                stmt.setString(4, repaymentEndDate);
                stmt.setDouble(5, monthlyPayment);
                stmt.setString(6, gracePeriod);
                stmt.setString(7, loanType);
                stmt.setInt(8, numberOfPayments);
                stmt.setInt(9, ssn);
                stmt.setInt(10, studentLoanId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student loan updated successfully.");
                    clearFields();
                    loadStudentLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Student Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        JButton deleteButton = new JButton("Delete Student Loan");
        deleteButton.setBounds(500, 180, 150, 30);
        deleteButton.addActionListener((e) -> {
            try {
                int ssn = Integer.parseInt(ssnField.getText().trim());
                int studentLoanId = Integer.parseInt(studentLoanIdField.getText().trim());
                String sqlDeleteLoan = "DELETE FROM StudentLoan WHERE ssn = ? and Student_Loan_ID = ?";
                PreparedStatement stmtDeleteLoan = conn.prepareStatement(sqlDeleteLoan);
                stmtDeleteLoan.setInt(1, ssn);
                stmtDeleteLoan.setInt(2, studentLoanId);
                int rowsAffected = stmtDeleteLoan.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student loan deleted successfully.");
                    clearFields();
                    loadStudentLoans(conn);
                } else {
                    JOptionPane.showMessageDialog(this, "Student Loan ID not found.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please ensure numeric fields are entered correctly.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });


        String[] columnNames = {"SSN", "Student Loan ID", "Loan Term", "Disbursement Date", "Repayment Start Date", "Repayment End Date", "Monthly Payment", "Grace Period", "Loan Type", "Number of Payments"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentLoanTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentLoanTable);
        scrollPane.setBounds(20, 220, 760, 300);

        this.add(ssnLabel);
        this.add(ssnField);
        this.add(studentLoanIdLabel);
        this.add(studentLoanIdField);
        this.add(loanTermLabel);
        this.add(loanTermField);
        this.add(disbursementDateLabel);
        this.add(disbursementDateField);
        this.add(repaymentStartDateLabel);
        this.add(repaymentStartDateField);
        this.add(repaymentEndDateLabel);
        this.add(repaymentEndDateField);
        this.add(monthlyPaymentLabel);
        this.add(monthlyPaymentField);
        this.add(gracePeriodLabel);
        this.add(gracePeriodField);
        this.add(loanTypeLabel);
        this.add(loanTypeField);
        this.add(numberOfPaymentsLabel);
        this.add(numberOfPaymentsField);


        this.add(addButton);
        this.add(editButton);
        this.add(deleteButton);

        this.add(scrollPane);

        loadStudentLoans(conn);

        studentLoanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentLoanTable.getSelectedRow() != -1) {
                int selectedRow = studentLoanTable.getSelectedRow();
                ssnField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                studentLoanIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                loanTermField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                disbursementDateField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                repaymentStartDateField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                repaymentEndDateField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                monthlyPaymentField.setText(tableModel.getValueAt(selectedRow, 6).toString());
                gracePeriodField.setText(tableModel.getValueAt(selectedRow, 7).toString());
                loanTypeField.setText(tableModel.getValueAt(selectedRow, 8).toString());
                numberOfPaymentsField.setText(tableModel.getValueAt(selectedRow, 9).toString());
            }
        });
    }

    private void loadStudentLoans(Connection conn) {
        try {
            this.tableModel.setRowCount(0);

            if (conn != null) {
                String sql = "Select * from StudentLoan";
                ResultSet rs = conn.createStatement().executeQuery(sql);

                while(rs.next()) {
                    this.tableModel.addRow(new Object[]{
                            rs.getLong("SSN"),
                            rs.getInt("Student_Loan_ID"),
                            rs.getString("Loan_Term"),
                            rs.getString("Disbursement_Date"),
                            rs.getString("Repayment_Start_Date"),
                            rs.getString("Repayment_End_Date"),
                            rs.getDouble("Monthly_Payment"),
                            rs.getString("Grace_Period"),
                            rs.getString("Loan_Type"),
                            rs.getInt("Number_of_Payments")
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading student loans: " + e.getMessage());
        }
    }

    private void clearFields() {
        ssnField.setText("");
        studentLoanIdField.setText("");
        loanTermField.setText("");
        disbursementDateField.setText("");
        repaymentStartDateField.setText("");
        repaymentEndDateField.setText("");
        monthlyPaymentField.setText("");
        gracePeriodField.setText("");
        loanTypeField.setText("");
        numberOfPaymentsField.setText("");
    }
}
