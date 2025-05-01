import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanApp {

    public static void main(String[] args) {
        // Connect to DB and initialize schema/data
        Connection conn = DBConnection.connect();
        DBInitializer.initializeDatabase(conn);

        // Build GUI
        JFrame frame = new JFrame("Loan Management App");

        JLabel nameLabel = new JLabel("Client Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);

        JLabel ssnLabel = new JLabel("SSN (9 digits):");
        ssnLabel.setBounds(20, 60, 100, 25);
        JTextField ssnField = new JTextField();
        ssnField.setBounds(120, 60, 200, 25);

        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setBounds(20, 100, 100, 25);
        JTextField incomeField = new JTextField();
        incomeField.setBounds(120, 100, 200, 25);

        JButton saveButton = new JButton("Add Client");
        saveButton.setBounds(120, 140, 120, 30);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String ssnText = ssnField.getText().trim();
                String incomeText = incomeField.getText().trim();

                if (name.isEmpty() || ssnText.isEmpty() || incomeText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out all fields.");
                    return;
                }

                try {
                    long ssn = Long.parseLong(ssnText);
                    double income = Double.parseDouble(incomeText);

                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO Client (SSN, Name, Income) VALUES (?, ?, ?)");
                    stmt.setLong(1, ssn);
                    stmt.setString(2, name);
                    stmt.setDouble(3, income);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Client added successfully.");
                    nameField.setText("");
                    ssnField.setText("");
                    incomeField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid SSN or income format.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(ssnLabel);
        frame.add(ssnField);
        frame.add(incomeLabel);
        frame.add(incomeField);
        frame.add(saveButton);

        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
