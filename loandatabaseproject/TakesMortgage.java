/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class TakesMortgage {
    private int SSN;
    private int mortgageID;

    public TakesMortgage(int SSN, int mortgageID) {
        this.SSN = SSN;
        this.mortgageID = mortgageID;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getMortgageID() {
        return mortgageID;
    }

    public void setMortgageID(int mortgageID) {
        this.mortgageID = mortgageID;
    }
    
    
}
