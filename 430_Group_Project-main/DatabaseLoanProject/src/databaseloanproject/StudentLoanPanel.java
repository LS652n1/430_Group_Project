package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class StudentLoanPanel extends JPanel {
    public StudentLoanPanel(Connection conn) {
        this.setLayout(new GridLayout(0, 2, 10, 5));
        JTextField ssnField = new JTextField();
        JTextField loanIdField = new JTextField();
        JTextField loanTermField = new JTextField();
        JTextField disbursementDateField = new JTextField();
        JTextField repaymentStartField = new JTextField();
        JTextField dueDateField = new JTextField();
        JTextField monthlyPaymentField = new JTextField();
        JTextField gracePeriodField = new JTextField();
        JTextField loanTypeField = new JTextField();
        this.add(new JLabel("SSN:"));
        this.add(ssnField);
        this.add(new JLabel("Loan ID:"));
        this.add(loanIdField);
        this.add(new JLabel("Loan Term (e.g. 10 years):"));
        this.add(loanTermField);
        this.add(new JLabel("Disbursement Date (YYYY-MM-DD):"));
        this.add(disbursementDateField);
        this.add(new JLabel("Repayment Start Date (YYYY-MM-DD):"));
        this.add(repaymentStartField);
        this.add(new JLabel("Due Date (YYYY-MM-DD):"));
        this.add(dueDateField);
        this.add(new JLabel("Monthly Payment:"));
        this.add(monthlyPaymentField);
        this.add(new JLabel("Grace Period (e.g. 6 months):"));
        this.add(gracePeriodField);
        this.add(new JLabel("Loan Type:"));
        this.add(loanTypeField);
        JButton addButton = new JButton("Add Student Loan");
        this.add(addButton);
        this.add(new JLabel());
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("    INSERT INTO StudentLoan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
                stmt.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt.setInt(2, Integer.parseInt(loanIdField.getText().trim()));
                stmt.setString(3, loanTermField.getText().trim());
                stmt.setString(4, disbursementDateField.getText().trim());
                stmt.setString(5, repaymentStartField.getText().trim());
                stmt.setString(6, dueDateField.getText().trim());
                stmt.setDouble(7, Double.parseDouble(monthlyPaymentField.getText().trim()));
                stmt.setString(8, gracePeriodField.getText().trim());
                stmt.setString(9, loanTypeField.getText().trim());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Student loan added.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }

        });
    }
}
