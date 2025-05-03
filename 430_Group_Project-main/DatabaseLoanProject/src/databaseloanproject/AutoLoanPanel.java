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

class AutoLoanPanel extends JPanel {
    public AutoLoanPanel(Connection conn) {
        this.setLayout(new GridLayout(0, 2, 10, 5));
        JTextField ssnField = new JTextField();
        JTextField loanIdField = new JTextField();
        JTextField makeField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField vinField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField interestRateField = new JTextField();
        JTextField balanceField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField termField = new JTextField();
        JTextField dueDateField = new JTextField();
        this.add(new JLabel("SSN:"));
        this.add(ssnField);
        this.add(new JLabel("Loan ID:"));
        this.add(loanIdField);
        this.add(new JLabel("Make:"));
        this.add(makeField);
        this.add(new JLabel("Model:"));
        this.add(modelField);
        this.add(new JLabel("Year:"));
        this.add(yearField);
        this.add(new JLabel("VIN:"));
        this.add(vinField);
        this.add(new JLabel("Amount:"));
        this.add(amountField);
        this.add(new JLabel("Interest Rate:"));
        this.add(interestRateField);
        this.add(new JLabel("Balance:"));
        this.add(balanceField);
        this.add(new JLabel("Start Date (YYYY-MM-DD):"));
        this.add(startDateField);
        this.add(new JLabel("Term (months):"));
        this.add(termField);
        this.add(new JLabel("Due Date (YYYY-MM-DD):"));
        this.add(dueDateField);
        JButton addButton = new JButton("Add Auto Loan");
        this.add(addButton);
        this.add(new JLabel());
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement("    INSERT INTO AutoLoan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
                stmt1.setInt(1, Integer.parseInt(loanIdField.getText().trim()));
                stmt1.setString(2, makeField.getText().trim());
                stmt1.setString(3, modelField.getText().trim());
                stmt1.setInt(4, Integer.parseInt(yearField.getText().trim()));
                stmt1.setString(5, vinField.getText().trim());
                stmt1.setDouble(6, Double.parseDouble(amountField.getText().trim()));
                stmt1.setDouble(7, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(8, Double.parseDouble(balanceField.getText().trim()));
                stmt1.setString(9, startDateField.getText().trim());
                stmt1.setInt(10, Integer.parseInt(termField.getText().trim()));
                stmt1.setString(11, dueDateField.getText().trim());
                stmt1.executeUpdate();
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO TakesAutoLoan VALUES (?, ?)");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(loanIdField.getText().trim()));
                stmt2.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Auto loan added and linked.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }

        });
    }
}