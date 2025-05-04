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
public class TakesMortgageDatabase {
    private static PreparedStatement addMortgage;
    private static PreparedStatement allTakesMortgage;
    private static PreparedStatement allTakesMortgageSSN;
    private static PreparedStatement allTakesMortgageID;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddTakesMortgage(TakesMortgage mortgage){
        int SSN = mortgage.getSSN();
        int mortgageID = mortgage.getMortgageID();

        db = DBConnection.connect();
        try{
            addMortgage = db.prepareStatement("INSERT INTO TakesMortgage (SSN, Mortgage_ID) values (?,?)");
            addMortgage.setInt(1, SSN);
            addMortgage.setInt(2, mortgageID);
            addMortgage.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<TakesMortgage> AllTakesMortgage(){
        TakesMortgage takesMortgage;
        ArrayList<TakesMortgage> takesMortgageList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesMortgage = db.prepareStatement("SELECT * FROM TakesMortgage");
            
            resultSet = allTakesMortgage.executeQuery();
            while(resultSet.next()){
                takesMortgage = new TakesMortgage(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Mortgage_ID"));
                takesMortgageList.add(takesMortgage);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesMortgageList;
    }
    
    public static ArrayList<TakesMortgage> AllTakesMortgageSSN(int SSN){
        TakesMortgage takesMortgage;
        ArrayList<TakesMortgage> takesMortgageList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesMortgageSSN = db.prepareStatement("SELECT * FROM TakesMortgage WHERE SSN = ?");
            allTakesMortgageSSN.setInt(1, SSN);
            resultSet = allTakesMortgageSSN.executeQuery();
            while(resultSet.next()){
                takesMortgage = new TakesMortgage(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Mortgage_ID"));
                takesMortgageList.add(takesMortgage);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesMortgageList;
    }
    public static ArrayList<TakesMortgage> AllTakesMortgageID(int mortgageID){
        TakesMortgage takesMortgage;
        ArrayList<TakesMortgage> takesMortgageList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allTakesMortgageID = db.prepareStatement("SELECT * FROM TakesMortgage WHERE Mortgage_ID = ?");
            allTakesMortgageID.setInt(1, mortgageID);
            resultSet = allTakesMortgageID.executeQuery();
            while(resultSet.next()){
                takesMortgage = new TakesMortgage(Integer.valueOf(resultSet.getString("SSN")),resultSet.getInt("Mortgage_ID"));
                takesMortgageList.add(takesMortgage);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return takesMortgageList;
    }
}
