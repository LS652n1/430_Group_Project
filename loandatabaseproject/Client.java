/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class Client {
    private int SSN;
    private String name;
    private double income;

    public Client(int SSN, String name, double income) {
        this.SSN = SSN;
        this.name = name;
        this.income = income;
    }

    public int getSSN() {
        return SSN;
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncome(double income) {
        this.income = income;
    }
    
    
    
    
}
