/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class Mortgage {
    private int mortgageID;
    private String address;
    private double area;
    private int bedrooms;
    private double price;
    private double amount;
    private double interest;
    private double paid;
    private String start;
    private int payments;
    private String end;

    public Mortgage(int mortgageID, String address, double area, int bedrooms, double price, double amount, double interest, double paid, String start, int payments, String end) {
        this.mortgageID = mortgageID;
        this.address = address;
        this.area = area;
        this.bedrooms = bedrooms;
        this.price = price;
        this.amount = amount;
        this.interest = interest;
        this.paid = paid;
        this.start = start;
        this.payments = payments;
        this.end = end;
    }

    public int getMortgageID() {
        return mortgageID;
    }

    public void setMortgageID(int mortgageID) {
        this.mortgageID = mortgageID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
