/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class AutoLoan {
    private int autoLoanID;
    private String make;
    private String model;
    private int year;
    private String VIN;
    private double amount;
    private double interest;
    private double paid;
    private String start;
    private int payments;
    private String end;

    public AutoLoan(int autoLoanID, String make, String model, int year, String VIN, double amount, double interest, double paid, String start, int payments, String end) {
        this.autoLoanID = autoLoanID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.VIN = VIN;
        this.amount = amount;
        this.interest = interest;
        this.paid = paid;
        this.start = start;
        this.payments = payments;
        this.end = end;
    }

    public int getAutoLoanID() {
        return autoLoanID;
    }

    public void setAutoLoanID(int AutoLoanID) {
        this.autoLoanID = autoLoanID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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
