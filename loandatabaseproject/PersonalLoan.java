/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class PersonalLoan {
    private int personalLoanID;
    private String purpose;
    private double amount;
    private double interest;
    private double paid;
    private String start;
    private int payments;
    private String end;

    public PersonalLoan(int personalLoanID, String purpose, double amount, double interest, double paid, String start, int payments, String end) {
        this.personalLoanID = personalLoanID;
        this.purpose = purpose;
        this.amount = amount;
        this.interest = interest;
        this.paid = paid;
        this.start = start;
        this.payments = payments;
        this.end = end;
    }

    public int getPersonalLoanID() {
        return personalLoanID;
    }

    public void setPersonalLoanID(int personalLoanID) {
        this.personalLoanID = personalLoanID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    
}
