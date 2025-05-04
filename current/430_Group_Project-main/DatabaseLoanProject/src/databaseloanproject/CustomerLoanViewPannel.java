package databaseloanproject;

import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class CustomerLoanViewPanel extends JPanel {
    private JTextField ssnField;
    private JButton viewLoansButton;

    private JTable autoLoanTable;
    private DefaultTableModel autoLoanTableModel;
    private JTable mortgageLoanTable;
    private DefaultTableModel mortgageLoanTableModel;
    private JTable personalLoanTable;
    private DefaultTableModel personalLoanTableModel;
    private JTable studentLoanTable;
    private DefaultTableModel studentLoanTableModel;

    public CustomerLoanViewPanel(Connection conn) {
        this.setLayout((LayoutManager)null);

        // SSN Input
        JLabel ssnLabel = new JLabel("Enter Customer SSN:");
        ssnLabel.setBounds(20, 20, 150, 25);
        ssnField = new JTextField();
        ssnField.setBounds(180, 20, 200, 25);

        viewLoansButton = new JButton("View Loans");
        viewLoansButton.setBounds(400, 20, 120, 25);
        viewLoansButton.addActionListener((e) -> {
            try {
                long ssn = Long.parseLong(ssnField.getText().trim());
                loadCustomerLoans(conn, ssn);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Error: Please enter a valid SSN.");
                clearTables();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                clearTables();
            }
        });

        JLabel autoLoanLabel = new JLabel("Auto Loans:");
        autoLoanLabel.setBounds(20, 60, 150, 25);
        String[] autoLoanColumnNames = {"SSN", "Auto Loan ID", "Make", "Model", "Year", "VIN", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        autoLoanTableModel = new DefaultTableModel(autoLoanColumnNames, 0);
        autoLoanTable = new JTable(autoLoanTableModel);
        JScrollPane autoLoanScrollPane = new JScrollPane(autoLoanTable);
        autoLoanScrollPane.setBounds(20, 90, 760, 120);

        JLabel mortgageLoanLabel = new JLabel("Mortgage Loans:");
        mortgageLoanLabel.setBounds(20, 220, 150, 25);
        String[] mortgageLoanColumnNames = {"SSN", "Mortgage ID", "House Address", "House Area", "Number of Bedrooms", "House Price", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        mortgageLoanTableModel = new DefaultTableModel(mortgageLoanColumnNames, 0);
        mortgageLoanTable = new JTable(mortgageLoanTableModel);
        JScrollPane mortgageLoanScrollPane = new JScrollPane(mortgageLoanTable);
        mortgageLoanScrollPane.setBounds(20, 250, 760, 120);

        JLabel personalLoanLabel = new JLabel("Personal Loans:");
        personalLoanLabel.setBounds(20, 380, 150, 25);
        String[] personalLoanColumnNames = {"SSN", "Personal Loan ID", "Loan Purpose", "Loan Amount", "Interest Rate", "Amount Paid", "Start Date", "Number of Payments", "End Date"};
        personalLoanTableModel = new DefaultTableModel(personalLoanColumnNames, 0);
        personalLoanTable = new JTable(personalLoanTableModel);
        JScrollPane personalLoanScrollPane = new JScrollPane(personalLoanTable);
        personalLoanScrollPane.setBounds(20, 410, 760, 120);

        JLabel studentLoanLabel = new JLabel("Student Loans:");
        studentLoanLabel.setBounds(20, 540, 150, 25);
        String[] studentLoanColumnNames = {"SSN", "Student Loan ID", "Loan Term", "Disbursement Date", "Repayment Start Date", "Repayment End Date", "Monthly Payment", "Grace Period", "Loan Type", "Number of Payments"};
        studentLoanTableModel = new DefaultTableModel(studentLoanColumnNames, 0);
        studentLoanTable = new JTable(studentLoanTableModel);
        JScrollPane studentLoanScrollPane = new JScrollPane(studentLoanTable);
        studentLoanScrollPane.setBounds(20, 570, 760, 120);


        this.add(ssnLabel);
        this.add(ssnField);
        this.add(viewLoansButton);
        this.add(autoLoanLabel);
        this.add(autoLoanScrollPane);
        this.add(mortgageLoanLabel);
        this.add(mortgageLoanScrollPane);
        this.add(personalLoanLabel);
        this.add(personalLoanScrollPane);
        this.add(studentLoanLabel);
        this.add(studentLoanScrollPane);
    }

    private void loadCustomerLoans(Connection conn, long ssn) {
        clearTables();
        try {
            String sql = "SELECT ta.SSN, al.Auto_Loan_ID, al.Make, al.Model, al.Year, al.VIN, al.Loan_Amount, al.Interest_Rate, al.Amount_Paid, al.Start_Date, al.Number_of_Payments, al.End_Date " +
                    "FROM AutoLoan al " +
                    "JOIN TakesAutoLoan ta ON al.Auto_Loan_ID = ta.Auto_Loan_ID " +
                    "WHERE ta.SSN = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ssn);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                autoLoanTableModel.addRow(new Object[]{
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
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Error loading auto loans: " + e.getMessage());
        }

        try {
            String sql = "SELECT tm.SSN, m.Mortgage_ID, m.House_Address, m.House_Area, m.Number_of_bedrooms, m.House_Price, m.Loan_Amount, m.Interest_Rate, m.Amount_Paid, m.Start_Date, m.Number_of_Payments, m.End_Date " +
                    "FROM Mortgage m " +
                    "JOIN TakesMortgage tm ON m.Mortgage_ID = tm.Mortgage_ID " +
                    "WHERE tm.SSN = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ssn);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                mortgageLoanTableModel.addRow(new Object[]{
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
        } catch (SQLException e) {

        }

        try {
            String sql = "SELECT tpl.SSN, pl.Personal_Loan_ID, pl.Loan_Purpose, pl.Loan_Amount, pl.Interest_Rate, pl.Amount_Paid, pl.Start_Date, pl.Number_of_Payments, pl.End_Date " +
                    "FROM PersonalLoan pl " +
                    "JOIN TakesPersonalLoan tpl ON pl.Personal_Loan_ID = tpl.Personal_Loan_ID " +
                    "WHERE tpl.SSN = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ssn);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                personalLoanTableModel.addRow(new Object[]{
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading personal loans: " + e.getMessage());
        }

        try {
            //String sql = "Select * from StudenLoan where SSN = ? ";
            //String sql = "select * from StudentLoan where ssn = 555555555";
            String sql = "Select * from StudentLoan where ssn = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ssn);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                studentLoanTableModel.addRow(new Object[]{
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
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Error loading student loans: " + e.getMessage());
        }
    }

    private void clearTables() {
        autoLoanTableModel.setRowCount(0);
        mortgageLoanTableModel.setRowCount(0);
        personalLoanTableModel.setRowCount(0);
        studentLoanTableModel.setRowCount(0);
    }
}
