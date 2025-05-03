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

class MortgageLoanPanel extends JPanel {
    public MortgageLoanPanel(Connection conn) {
        this.setLayout(new GridLayout(0, 2, 10, 5));
        JTextField ssnField = new JTextField();
        JTextField loanIdField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField propertyTaxField = new JTextField();
        JTextField insuranceField = new JTextField();
        JTextField purchasePriceField = new JTextField();
        JTextField loanAmountField = new JTextField();
        JTextField interestRateField = new JTextField();
        JTextField downPaymentField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField termField = new JTextField();
        JTextField dueDateField = new JTextField();
        this.add(new JLabel("SSN:"));
        this.add(ssnField);
        this.add(new JLabel("Mortgage ID:"));
        this.add(loanIdField);
        this.add(new JLabel("House Address:"));
        this.add(addressField);
        this.add(new JLabel("Property Tax:"));
        this.add(propertyTaxField);
        this.add(new JLabel("Insurance:"));
        this.add(insuranceField);
        this.add(new JLabel("Purchase Price:"));
        this.add(purchasePriceField);
        this.add(new JLabel("Loan Amount:"));
        this.add(loanAmountField);
        this.add(new JLabel("Interest Rate:"));
        this.add(interestRateField);
        this.add(new JLabel("Down Payment:"));
        this.add(downPaymentField);
        this.add(new JLabel("Start Date (YYYY-MM-DD):"));
        this.add(startDateField);
        this.add(new JLabel("Term (months):"));
        this.add(termField);
        this.add(new JLabel("Due Date (YYYY-MM-DD):"));
        this.add(dueDateField);
        JButton addButton = new JButton("Add Mortgage Loan");
        this.add(addButton);
        this.add(new JLabel());
        addButton.addActionListener((e) -> {
            try {
                PreparedStatement stmt1 = conn.prepareStatement("    INSERT INTO Mortgage VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
                stmt1.setInt(1, Integer.parseInt(loanIdField.getText().trim()));
                stmt1.setString(2, addressField.getText().trim());
                stmt1.setDouble(3, Double.parseDouble(propertyTaxField.getText().trim()));
                stmt1.setInt(4, Integer.parseInt(insuranceField.getText().trim()));
                stmt1.setDouble(5, Double.parseDouble(purchasePriceField.getText().trim()));
                stmt1.setDouble(6, Double.parseDouble(loanAmountField.getText().trim()));
                stmt1.setDouble(7, Double.parseDouble(interestRateField.getText().trim()));
                stmt1.setDouble(8, Double.parseDouble(downPaymentField.getText().trim()));
                stmt1.setString(9, startDateField.getText().trim());
                stmt1.setInt(10, Integer.parseInt(termField.getText().trim()));
                stmt1.setString(11, dueDateField.getText().trim());
                stmt1.executeUpdate();
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO TakesMortgage VALUES (?, ?)");
                stmt2.setLong(1, Long.parseLong(ssnField.getText().trim()));
                stmt2.setInt(2, Integer.parseInt(loanIdField.getText().trim()));
                stmt2.executeUpdate();
                JOptionPane.showMessageDialog((Component)null, "Mortgage loan added and linked.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog((Component)null, "Error: " + ex.getMessage());
            }

        });
    }
}
