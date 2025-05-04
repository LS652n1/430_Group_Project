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
public class PersonalLoanDatabase {
    private static PreparedStatement addPersonal;
    private static PreparedStatement editPersonalPurpose;
    private static PreparedStatement editPersonalAmount;
    private static PreparedStatement editPersonalInterest;
    private static PreparedStatement editPersonalPaid;
    private static PreparedStatement editPersonalStart;
    private static PreparedStatement editPersonalPayments;
    private static PreparedStatement editPersonalEnd;
    private static PreparedStatement findPersonal;
    private static PreparedStatement deletePersonal;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddPersonal(PersonalLoan personal){
        int personalID = personal.getPersonalLoanID();
        String purpose = personal.getPurpose();
        double amount = personal.getAmount();
        double interest = personal.getInterest();
        double paid = personal.getPaid();
        String start = personal.getStart();
        int payments = personal.getPayments();
        String end = personal.getEnd();
        db = DBConnection.connect();
        
        try{
            addPersonal = db.prepareStatement("INSERT INTO PersonalLoan (Personal_Loan_ID, Loan_Purpose, Loan_Amount, Interest_Rate, Amount_Paid, Start_Date, Number_of_Payments, End_Date) values (?,?,?,?,?,?,?,?)");
            addPersonal.setInt(1, personalID);
            addPersonal.setString(2, purpose);
            addPersonal.setDouble(3, amount);
            addPersonal.setDouble(4, interest);
            addPersonal.setDouble(5, paid);
            addPersonal.setString(6, start);
            addPersonal.setInt(7, payments);
            addPersonal.setString(8, end);
            addPersonal.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static PersonalLoan FindPersonal(int personalID){
        db = DBConnection.connect();
        PersonalLoan personal = null;
        try{
            findPersonal = db.prepareStatement("SELECT * FROM PersonalLoan WHERE Personal_Loan_ID = ?");
            findPersonal.setInt(1, personalID);
            resultSet = findPersonal.executeQuery();

            while(resultSet.next()){
                personal = new PersonalLoan(resultSet.getInt("Personal_Loan_ID"), resultSet.getString("Loan_Purpose"), resultSet.getDouble("Loan_Amount"), resultSet.getDouble("Interest_Rate"), resultSet.getDouble("Amount_Paid"), resultSet.getString("Start_Date"), resultSet.getInt("Number_of_Payments"), resultSet.getString("End_Date"));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return personal;
    }
    
    public static void EditPersonalPurpose(int personalID, String purpose){
        db = DBConnection.connect();
        try{
            editPersonalPurpose = db.prepareStatement("UPDATE PersonalLoan SET Loan_Purpose = ? WHERE Personal_Loan_ID = ?");
            editPersonalPurpose.setString(1, purpose);
            editPersonalPurpose.setInt(2, personalID);
            
            editPersonalPurpose.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void EditPersonalAmount(int personalID, double amount){
        db = DBConnection.connect();
        try{
            editPersonalAmount = db.prepareStatement("UPDATE PersonalLoan SET Loan_Amount = ? WHERE Personal_Loan_ID = ?");
            editPersonalAmount.setDouble(1, amount);
            editPersonalAmount.setInt(2, personalID);
            
            editPersonalAmount.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditPersonalInterest(int personalID, double interest){
        db = DBConnection.connect();
        try{
            editPersonalInterest = db.prepareStatement("UPDATE PersonalLoan SET Interest_Rate = ? WHERE Personal_Loan_ID = ?");
            editPersonalInterest.setDouble(1, interest);
            editPersonalInterest.setInt(2, personalID);
            
            editPersonalInterest.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditPersonalPaid(int personalID, double paid){
        db = DBConnection.connect();
        try{
            editPersonalPaid = db.prepareStatement("UPDATE PersonalLoan SET Amount_Paid = ? WHERE Personal_Loan_ID = ?");
            editPersonalPaid.setDouble(1, paid);
            editPersonalPaid.setInt(2, personalID);
            
            editPersonalPaid.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditPersonalStart(int personalID, String start){
        db = DBConnection.connect();
        try{
            editPersonalStart = db.prepareStatement("UPDATE PersonalLoan SET Start_Date = ? WHERE Personal_Loan_ID = ?");
            editPersonalStart.setString(1, start);
            editPersonalStart.setInt(2, personalID);
            
            editPersonalStart.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditPersonalPayments(int personalID, int payments){
        db = DBConnection.connect();
        try{
            editPersonalPayments = db.prepareStatement("UPDATE PersonalLoan SET Number_of_Payments = ? WHERE Personal_Loan_ID = ?");
            editPersonalPayments.setInt(1, payments);
            editPersonalPayments.setInt(2, personalID);
            
            editPersonalPayments.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditPersonalEnd(int personalID, String end){
        db = DBConnection.connect();
        try{
            editPersonalEnd = db.prepareStatement("UPDATE PersonalLoan SET End_Date = ? WHERE Personal_Loan_ID = ?");
            editPersonalEnd.setString(1, end);
            editPersonalEnd.setInt(2, personalID);
            
            editPersonalEnd.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void DeletePersonal(int personalID){
        db = DBConnection.connect();

        try{
            deletePersonal = db.prepareStatement("DELETE FROM PersonalLoan WHERE Personal_Loan_ID = ?");
            deletePersonal.setInt(1, personalID);
            deletePersonal.executeUpdate();

            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
