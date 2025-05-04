/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static loandatabaseproject.DBConnection.connect;
import java.sql.PreparedStatement;
/**
 *
 * @author rdenn
 */
public class MortgageDatabase {
    private static PreparedStatement addMortgage;
    private static PreparedStatement editMortgageAddress;
    private static PreparedStatement editMortgageArea;
    private static PreparedStatement editMortgageBedrooms;
    private static PreparedStatement editMortgagePrice;
    private static PreparedStatement editMortgageAmount;
    private static PreparedStatement editMortgageInterest;
    private static PreparedStatement editMortgagePaid;
    private static PreparedStatement editMortgageStart;
    private static PreparedStatement editMortgagePayments;
    private static PreparedStatement editMortgageEnd;
    private static PreparedStatement findMortgage;
    private static PreparedStatement deleteMortgage;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddMortgage(Mortgage mortgage){
        int mortgageID = mortgage.getMortgageID();
        String address = mortgage.getAddress();
        double area = mortgage.getArea();
        int bedrooms = mortgage.getBedrooms();
        double price = mortgage.getPrice();
        double amount = mortgage.getAmount();
        double interest = mortgage.getInterest();
        double paid = mortgage.getPaid();
        String start = mortgage.getStart();
        int payments = mortgage.getPayments();
        String end = mortgage.getEnd();
        db = DBConnection.connect();
        
        try{
            addMortgage = db.prepareStatement("INSERT INTO Mortgage (Mortgage_ID, House_Address, House_Area, Number_of_bedrooms, House_Price, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) values (?,?,?,?,?,?,?,?,?,?,?)");
            addMortgage.setInt(1, mortgageID);
            addMortgage.setString(2, address);
            addMortgage.setDouble(3, area);
            addMortgage.setInt(4, bedrooms);
            addMortgage.setDouble(5, price);
            addMortgage.setDouble(6, amount);
            addMortgage.setDouble(7, interest);
            addMortgage.setDouble(8, paid);
            addMortgage.setString(9, start);
            addMortgage.setInt(10, payments);
            addMortgage.setString(11, end);
            addMortgage.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static Mortgage FindMortgage(int mortgageID){
        db = DBConnection.connect();
        Mortgage mortgage = null;
        try{
            findMortgage = db.prepareStatement("SELECT * FROM Mortgage WHERE Mortgage_ID = ?");
            findMortgage.setInt(1, mortgageID);
            resultSet = findMortgage.executeQuery();

            while(resultSet.next()){
                mortgage = new Mortgage(resultSet.getInt("Mortgage_ID"), resultSet.getString("House_Address"), resultSet.getDouble("House_Area"), resultSet.getInt("Number_of_bedrooms"), resultSet.getDouble("House_Price"), resultSet.getDouble("Loan_Amount"), resultSet.getDouble("Interest_Rate"), resultSet.getDouble("Amount_Paid"), resultSet.getString("Start_Date"), resultSet.getInt("Number_of_Payments"), resultSet.getString("End_Date"));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return mortgage;
    }
    
    public static void EditMortgageAddress(int mortgageID, String address){
        db = DBConnection.connect();
        try{
            editMortgageAddress = db.prepareStatement("UPDATE Mortgage SET House_Address = ? WHERE Mortgage_ID = ?");
            editMortgageAddress.setString(1, address);
            editMortgageAddress.setInt(2, mortgageID);
            
            editMortgageAddress.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageArea(int mortgageID, double area){
        db = DBConnection.connect();
        try{
            editMortgageArea = db.prepareStatement("UPDATE Mortgage SET House_Area = ? WHERE Mortgage_ID = ?");
            editMortgageArea.setDouble(1, area);
            editMortgageArea.setInt(2, mortgageID);
            
            editMortgageArea.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageBedrooms(int mortgageID, int bedrooms){
        db = DBConnection.connect();
        try{
            editMortgageBedrooms = db.prepareStatement("UPDATE Mortgage SET Number_of_Bedrooms = ? WHERE Mortgage_ID = ?");
            editMortgageBedrooms.setInt(1, bedrooms);
            editMortgageBedrooms.setInt(2, mortgageID);
            
            editMortgageBedrooms.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgagePrice(int mortgageID, double price){
        db = DBConnection.connect();
        try{
            editMortgagePrice = db.prepareStatement("UPDATE Mortgage SET House_Price = ? WHERE Mortgage_ID = ?");
            editMortgagePrice.setDouble(1, price);
            editMortgagePrice.setInt(2, mortgageID);
            
            editMortgagePrice.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageAmount(int mortgageID, double amount){
        db = DBConnection.connect();
        try{
            editMortgageAmount = db.prepareStatement("UPDATE Mortgage SET Loan_Amount = ? WHERE Mortgage_ID = ?");
            editMortgageAmount.setDouble(1, amount);
            editMortgageAmount.setInt(2, mortgageID);
            
            editMortgageAmount.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageInterest(int mortgageID, double interest){
        db = DBConnection.connect();
        try{
            editMortgageInterest = db.prepareStatement("UPDATE Mortgage SET Interest_Rate = ? WHERE Mortgage_ID = ?");
            editMortgageInterest.setDouble(1, interest);
            editMortgageInterest.setInt(2, mortgageID);
            
            editMortgageInterest.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgagePaid(int mortgageID, double paid){
        db = DBConnection.connect();
        try{
            editMortgagePaid = db.prepareStatement("UPDATE Mortgage SET Amount_Paid = ? WHERE Mortgage_ID = ?");
            editMortgagePaid.setDouble(1, paid);
            editMortgagePaid.setInt(2, mortgageID);
            
            editMortgagePaid.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageStart(int mortgageID, String start){
        db = DBConnection.connect();
        try{
            editMortgageStart = db.prepareStatement("UPDATE Mortgage SET Start_Date = ? WHERE Mortgage_ID = ?");
            editMortgageStart.setString(1, start);
            editMortgageStart.setInt(2, mortgageID);
            
            editMortgageStart.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgagePayments(int mortgageID, int payments){
        db = DBConnection.connect();
        try{
            editMortgagePayments = db.prepareStatement("UPDATE Mortgage SET Number_of_Payments = ? WHERE Mortgage_ID = ?");
            editMortgagePayments.setInt(1, payments);
            editMortgagePayments.setInt(2, mortgageID);
            
            editMortgagePayments.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditMortgageEnd(int mortgageID, String end){
        db = DBConnection.connect();
        try{
            editMortgageEnd = db.prepareStatement("UPDATE Mortgage SET End_Date = ? WHERE Mortgage_ID = ?");
            editMortgageEnd.setString(1, end);
            editMortgageEnd.setInt(2, mortgageID);
            
            editMortgageEnd.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void DeleteMortgage(int mortgageID){
        db = DBConnection.connect();

        try{
            deleteMortgage = db.prepareStatement("DELETE FROM Mortgage WHERE Mortgage_ID = ?");
            deleteMortgage.setInt(1, mortgageID);
            deleteMortgage.executeUpdate();

            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
}
