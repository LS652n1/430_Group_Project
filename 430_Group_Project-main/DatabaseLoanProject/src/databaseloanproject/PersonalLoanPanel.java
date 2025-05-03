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

class PersonalLoanPanel extends JPanel {
    public PersonalLoanPanel(Connection conn) {
        this.setLayout(new GridLayout(0, 2, 10, 5));
        JTextField ssnField = new JTextField();
        JTextField loanIdField = new JTextField();
        JTextField purposeField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField interestRateField = new JTextField();
        JTextField balanceField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField missedPaymentsField = new JTextField();
        JTextField dueDateField = new JTextField();
        this.add(new JLabel("SSN:"));
        this.add(ssnField);
        this.add(new JLabel("Loan ID:"));
        this.add(loanIdField);
        this.add(new JLabel("Purpose:"));
        this.add(purposeField);
        this.add(new JLabel("Amount:"));
        this.add(amountField);
        this.add(new JLabel("Interest Rate:"));
        this.add(interestRateField);
        this.add(new JLabel("Balance:"));
        this.add(balanceField);
        this.add(new JLabel("Start Date (YYYY-MM-DD):"));
        this.add(startDateField);
        this.add(new JLabel("Missed Payments:"));
        this.add(missedPaymentsField);
        this.add(new JLabel("Due Date (YYYY-MM-DD):"));
        this.add(dueDateField);
        JButton addButton = new JButton("Add Personal Loan");
        this.add(addButton);
        this.add(new JLabel());
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement("    INSERT INTO PersonalLoan VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n");
                stmt1.setInt(1, Integer.parseInt(loanIdField.getText().trim()));
                stmt1.setString(2, purposeField.getText().trim());
                stmt1.setDouble(3, Double.parseDouble(amountField.getText().trim()));
                stmt1.setDouble(4, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(5, Double.parseDouble(balanceField.getText().trim()));
                stmt1.setString(6, startDateField.getText().trim());
                stmt1.setInt(7, Integer.parseInt(missedPaymentsField.getText().trim()));
                stmt1.setString(8, dueDateField.getText().trim());
                stmt1.executeUpdate();
                PreparedStatement stmt2 = conn.prepareStatement("    INSERT INTO TakesPersonalLoan VALUES (?, ?)\n");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(loanIdField.getText().trim()));
                stmt2.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Personal loan added and linked.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }

        });
    }
}
