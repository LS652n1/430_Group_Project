/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rdenn
 */
public class TakesPersonalLoanDatabase {
    private static PreparedStatement addPersonal;
    private static PreparedStatement allTakesPersonalLoan;
    private static PreparedStatement allTakesPersonalLoanSSN;
    private static PreparedStatement allTakesPersonalLoanID;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddTakesPersonal(TakesPersonalLoan personal){
        int SSN = personal.getSSN();
        int personalID = personal.getPersonalID();

        db = DBConnection.connect();
        try{
            addPersonal = db.prepareStatement("INSERT INTO TakesPersonalLoan (SSN, Personal_Loan_ID) values (?,?)");
            addPersonal.setInt(1, SSN);
            addPersonal.setInt(2, personalID);
            addPersonal.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static ArrayList<TakesPersonalLoan> AllTakesPersonalLoan(){
        TakesPersonalLoan takesPersonalLoan;
        ArrayList<TakesPersonalLoan> takesPersonalLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesPersonalLoan = db.prepareStatement("SELECT * FROM TakesPersonalLoan");
            
            resultSet = allTakesPersonalLoan.executeQuery();
            while(resultSet.next()){
                takesPersonalLoan = new TakesPersonalLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Personal_Loan_ID"));
                takesPersonalLoanList.add(takesPersonalLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesPersonalLoanList;
    }
    public static ArrayList<TakesPersonalLoan> AllTakesPersonalLoanSSN(int SSN){
        TakesPersonalLoan takesPersonalLoan;
        ArrayList<TakesPersonalLoan> takesPersonalLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesPersonalLoanSSN = db.prepareStatement("SELECT * FROM TakesPersonalLoan WHERE SSN = ?");
            allTakesPersonalLoanSSN.setInt(1, SSN);
            resultSet = allTakesPersonalLoanSSN.executeQuery();
            while(resultSet.next()){
                takesPersonalLoan = new TakesPersonalLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Personal_Loan_ID"));
                takesPersonalLoanList.add(takesPersonalLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesPersonalLoanList;
    }
    public static ArrayList<TakesPersonalLoan> AllTakesPersonalLoanID(int personalID){
        TakesPersonalLoan takesPersonalLoan;
        ArrayList<TakesPersonalLoan> takesPersonalLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesPersonalLoanID = db.prepareStatement("SELECT * FROM TakesPersonalLoan WHERE Peronal_Loan_ID = ?");
            allTakesPersonalLoanID.setInt(1, personalID);
            resultSet = allTakesPersonalLoanID.executeQuery();
            while(resultSet.next()){
                takesPersonalLoan = new TakesPersonalLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Personal_Loan_ID"));
                takesPersonalLoanList.add(takesPersonalLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesPersonalLoanList;
    }
}
