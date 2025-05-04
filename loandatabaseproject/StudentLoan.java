/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

/**
 *
 * @author rdenn
 */
public class StudentLoan {
    private int SSN;
    private int studentLoanID;
    private String term;
    private String disbursementDate;
    private String start;
    private String end;
    private double payment;
    private String gracePeriod;
    private String loanType;

    public StudentLoan(int SSN, int studentLoanID, String term, String disbursementDate, String start, String end, double payment, String gracePeriod, String loanType) {
        this.SSN = SSN;
        this.studentLoanID = studentLoanID;
        this.term = term;
        this.disbursementDate = disbursementDate;
        this.start = start;
        this.end = end;
        this.payment = payment;
        this.gracePeriod = gracePeriod;
        this.loanType = loanType;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getStudentLoanID() {
        return studentLoanID;
    }

    public void setStudentLoanID(int studentLoanID) {
        this.studentLoanID = studentLoanID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(String disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(String gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    
    
}
