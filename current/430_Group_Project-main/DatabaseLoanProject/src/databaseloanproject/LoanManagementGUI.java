package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class LoanManagementGUI extends JFrame {
    public LoanManagementGUI() {
        this.setTitle("Loan Management System");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        Connection conn = DBConnection.connect();
        DBInitializer.initializeDatabase(conn);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Add Customer", new CustomerPanel(conn));
        tabs.addTab("Auto Loan", new AutoLoanPanel(conn));
        tabs.addTab("Personal Loan", new PersonalLoanPanel(conn));
        tabs.addTab("Mortgage Loan", new MortgageLoanPanel(conn));
        tabs.addTab("Student Loan", new StudentLoanPanel(conn));
        tabs.addTab("View Customer Loans", new CustomerLoanViewPanel(conn));
        this.add(tabs);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoanManagementGUI());
    }
}
