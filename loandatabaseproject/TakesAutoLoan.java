/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class TakesAutoLoan {
    private int SSN;
    private int autoID;

    public TakesAutoLoan(int SSN, int autoID) {
        this.SSN = SSN;
        this.autoID = autoID;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getAutoID() {
        return autoID;
    }

    public void setAutoID(int autoID) {
        this.autoID = autoID;
    }
    
    
    
}
