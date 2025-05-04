/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rdenn
 */
public class AutoLoanDatabase {
    private static PreparedStatement addAuto;
    private static PreparedStatement editAutoAddress;
    private static PreparedStatement editAutoArea;
    private static PreparedStatement editAutoBedrooms;
    private static PreparedStatement editAutoPrice;
    private static PreparedStatement editAutoAmount;
    private static PreparedStatement editAutoInterest;
    private static PreparedStatement editAutoPaid;
    private static PreparedStatement editAutoStart;
    private static PreparedStatement editAutoPayments;
    private static PreparedStatement editAutoEnd;
    private static PreparedStatement findAuto;
    private static PreparedStatement deleteAuto;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddAuto(AutoLoan auto){
        int autoID = auto.getAutoLoanID();
        String make = auto.getMake();
        String model = auto.getModel();
        int year = auto.getYear();
        String vin = auto.getVIN();
        double amount = auto.getAmount();
        double interest = auto.getInterest();
        double paid = auto.getPaid();
        String start = auto.getStart();
        int payments = auto.getPayments();
        String end = auto.getEnd();
        db = DBConnection.connect();
        
        try{
            addAuto = db.prepareStatement("INSERT INTO AutoLoan (Auto_Loan_ID, Make, Model, Year, VIN, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) values (?,?,?,?,?,?,?,?,?,?,?)");
            addAuto.setInt(1, autoID);
            addAuto.setString(2, make);
            addAuto.setString(3, model);
            addAuto.setInt(4, year);
            addAuto.setString(5, vin);
            addAuto.setDouble(6, amount);
            addAuto.setDouble(7, interest);
            addAuto.setDouble(8, paid);
            addAuto.setString(9, start);
            addAuto.setInt(10, payments);
            addAuto.setString(11, end);
            addAuto.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static AutoLoan FindAuto(int autoID){
        db = DBConnection.connect();
        AutoLoan auto = null;
        try{
            findAuto = db.prepareStatement("SELECT * FROM AutoLoan WHERE Auto_Loan_ID = ?");
            findAuto.setInt(1, autoID);
            resultSet = findAuto.executeQuery();

            while(resultSet.next()){
                auto = new AutoLoan(resultSet.getInt("Auto_Loan_ID"), resultSet.getString("Make"), resultSet.getString("Model"), resultSet.getInt("Year"), resultSet.getString("VIN"), resultSet.getDouble("Loan_Amount"), resultSet.getDouble("Interest_Rate"), resultSet.getDouble("Amount_Paid"), resultSet.getString("Start_Date"), resultSet.getInt("Number_of_Payments"), resultSet.getString("End_Date"));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return auto;
    }
    public static void EditAutoMake(int autoID, String make){
        db = DBConnection.connect();
        try{
            editAutoAddress = db.prepareStatement("UPDATE AutoLoan SET make = ? WHERE Auto_Loan_ID = ?");
            editAutoAddress.setString(1, make);
            editAutoAddress.setInt(2, autoID);
            
            editAutoAddress.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoModel(int autoID, String model){
        db = DBConnection.connect();
        try{
            editAutoArea = db.prepareStatement("UPDATE AutoLoan SET Model = ? WHERE Auto_Loan_ID = ?");
            editAutoArea.setString(1, model);
            editAutoArea.setInt(2, autoID);
            
            editAutoArea.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoYear(int autoID, int year){
        db = DBConnection.connect();
        try{
            editAutoBedrooms = db.prepareStatement("UPDATE AutoLoan SET Year = ? WHERE Auto_Loan_ID = ?");
            editAutoBedrooms.setInt(1, year);
            editAutoBedrooms.setInt(2, autoID);
            
            editAutoBedrooms.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoVIN(int autoID, String vin){
        db = DBConnection.connect();
        try{
            editAutoPrice = db.prepareStatement("UPDATE AutoLoan SET VIN = ? WHERE Auto_Loan_ID = ?");
            editAutoPrice.setString(1, vin);
            editAutoPrice.setInt(2, autoID);
            
            editAutoPrice.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoAmount(int autoID, double amount){
        db = DBConnection.connect();
        try{
            editAutoAmount = db.prepareStatement("UPDATE AutoLoan SET Loan_Amount = ? WHERE Auto_Loan_ID = ?");
            editAutoAmount.setDouble(1, amount);
            editAutoAmount.setInt(2, autoID);
            
            editAutoAmount.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoInterest(int autoID, double interest){
        db = DBConnection.connect();
        try{
            editAutoInterest = db.prepareStatement("UPDATE AutoLoan SET Interest_Rate = ? WHERE Auto_Loan_ID = ?");
            editAutoInterest.setDouble(1, interest);
            editAutoInterest.setInt(2, autoID);
            
            editAutoInterest.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoPaid(int autoID, double paid){
        db = DBConnection.connect();
        try{
            editAutoPaid = db.prepareStatement("UPDATE AutoLoan SET Amount_Paid = ? WHERE Auto_Loan_ID = ?");
            editAutoPaid.setDouble(1, paid);
            editAutoPaid.setInt(2, autoID);
            
            editAutoPaid.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoStart(int autoID, String start){
        db = DBConnection.connect();
        try{
            editAutoStart = db.prepareStatement("UPDATE AutoLoan SET Start_Date = ? WHERE Auto_Loan_ID = ?");
            editAutoStart.setString(1, start);
            editAutoStart.setInt(2, autoID);
            
            editAutoStart.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoPayments(int autoID, int payments){
        db = DBConnection.connect();
        try{
            editAutoPayments = db.prepareStatement("UPDATE AutoLoan SET Number_of_Payments = ? WHERE Auto_Loan_ID = ?");
            editAutoPayments.setInt(1, payments);
            editAutoPayments.setInt(2, autoID);
            
            editAutoPayments.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditAutoEnd(int autoID, String end){
        db = DBConnection.connect();
        try{
            editAutoEnd = db.prepareStatement("UPDATE AutoLoan SET End_Date = ? WHERE Auto_Loan_ID = ?");
            editAutoEnd.setString(1, end);
            editAutoEnd.setInt(2, autoID);
            
            editAutoEnd.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void DeleteAuto(int autoID){
        db = DBConnection.connect();

        try{
            deleteAuto = db.prepareStatement("DELETE FROM AutoLoan WHERE Auto_Loan_ID = ?");
            deleteAuto.setInt(1, autoID);
            deleteAuto.executeUpdate();

            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
}
