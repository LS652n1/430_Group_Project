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
public class TakesAutoLoanDatabase {
    private static PreparedStatement addAuto;
    private static PreparedStatement allTakesAutoLoan;
    private static PreparedStatement allTakesAutoLoanSSN;
    private static PreparedStatement allTakesAutoLoanID;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddTakesAuto(TakesAutoLoan auto){
        int SSN = auto.getSSN();
        int autoID = auto.getAutoID();

        db = DBConnection.connect();
        try{
            addAuto = db.prepareStatement("INSERT INTO TakesAutoLoan (SSN, Auto_Loan_ID) values (?,?)");
            addAuto.setInt(1, SSN);
            addAuto.setInt(2, autoID);
            addAuto.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static ArrayList<TakesAutoLoan> AllTakesAutoLoan(){
        TakesAutoLoan takesAutoLoan;
        ArrayList<TakesAutoLoan> takesAutoLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesAutoLoan = db.prepareStatement("SELECT * FROM TakesAutoLoan");
            
            resultSet = allTakesAutoLoan.executeQuery();
            while(resultSet.next()){
                takesAutoLoan = new TakesAutoLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Auto_Loan_ID"));
                takesAutoLoanList.add(takesAutoLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesAutoLoanList;
    }
    
    public static ArrayList<TakesAutoLoan> AllTakesAutoLoanSSN(int SSN){
        TakesAutoLoan takesAutoLoan;
        ArrayList<TakesAutoLoan> takesAutoLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesAutoLoanSSN = db.prepareStatement("SELECT * FROM TakesAutoLoan WHERE SSN = ?");
            allTakesAutoLoanSSN.setInt(1, SSN);
            resultSet = allTakesAutoLoanSSN.executeQuery();
            while(resultSet.next()){
                takesAutoLoan = new TakesAutoLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Auto_Loan_ID"));
                takesAutoLoanList.add(takesAutoLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesAutoLoanList;
    }
    public static ArrayList<TakesAutoLoan> AllTakesAutoLoanID(int autoID){
        TakesAutoLoan takesAutoLoan;
        ArrayList<TakesAutoLoan> takesAutoLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesAutoLoanID = db.prepareStatement("SELECT * FROM TakesAutoLoan WHERE Auto_Loan_ID = ?");
            allTakesAutoLoanID.setInt(1, autoID);
            resultSet = allTakesAutoLoanID.executeQuery();
            while(resultSet.next()){
                takesAutoLoan = new TakesAutoLoan(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Auto_Loan_ID"));
                takesAutoLoanList.add(takesAutoLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesAutoLoanList;
    }
}
