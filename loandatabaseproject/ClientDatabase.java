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
public class ClientDatabase {
    private static PreparedStatement allClients;
    private static PreparedStatement addClient;
    private static PreparedStatement editClientSSN;
    private static PreparedStatement editClientName;
    private static PreparedStatement editClientIncome;
    private static PreparedStatement findClient;
    private static PreparedStatement deleteClient;
    private static ResultSet resultSet;
    private static Connection db;
    
    
    public static ArrayList<Client> AllClients(String sortBy){
        Client client;
        ArrayList<Client> clientList = new ArrayList<>();
        db = DBConnection.connect();

        String sql = "SELECT * FROM Client";
        try{
            if(sortBy.equals("SSN Ascending")){
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY SSN");
            }
            else if(sortBy.equals("SSN Descending")){
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY SSN DESC");
            }
            else if(sortBy.equals("Name Ascending")){
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY Name");
            }
            else if(sortBy.equals("Name Descending")){
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY Name DESC");
            }
            else if(sortBy.equals("Income Ascending")){
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY Income");
            }
            else{
                allClients = db.prepareStatement("SELECT * FROM Client ORDER BY Income DESC");
            }
            resultSet = allClients.executeQuery();
            while(resultSet.next()){
                client = new Client(Integer.valueOf(resultSet.getString("SSN")),resultSet.getString("Name"),Double.valueOf(resultSet.getString("Income")));
                clientList.add(client);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return clientList;
    }
    public static void AddClient(Client client){
        int SSN = client.getSSN();
        String name = client.getName();
        double income = client.getIncome();
        db = DBConnection.connect();
        try{
            addClient = db.prepareStatement("INSERT INTO Client (SSN, name, income) values (?,?,?)");
            addClient.setInt(1, SSN);
            addClient.setString(2, name);
            addClient.setDouble(3, income);
            addClient.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditClientSSN(int SSN, int newSSN){
        db = DBConnection.connect();
        try{
            editClientSSN = db.prepareStatement("UPDATE Client SET SSN = ? WHERE SSN = ?");
            editClientSSN.setInt(1, newSSN);
            editClientSSN.setInt(2, SSN);
            editClientSSN.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditClientName(int SSN, String newName){
        db = DBConnection.connect();
        try{
            editClientName = db.prepareStatement("UPDATE Client SET Name = ? WHERE SSN = ?");
            editClientName.setString(1, newName);
            editClientName.setInt(2, SSN);
            editClientName.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditClientIncome(int SSN, double newIncome){
        db = DBConnection.connect();
        try{
            editClientIncome = db.prepareStatement("UPDATE Client SET Income = ? WHERE SSN = ?");
            editClientIncome.setDouble(1, newIncome);
            editClientIncome.setInt(2, SSN);
            editClientIncome.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static Client FindClient(int SSN){
        db = DBConnection.connect();
        Client client = null;
        try{
            findClient = db.prepareStatement("SELECT * FROM Client WHERE SSN = ?");
            findClient.setInt(1, SSN);
            resultSet = findClient.executeQuery();

            while(resultSet.next()){
                client = new Client(resultSet.getInt("SSN"), resultSet.getString("Name"), resultSet.getDouble("Income"));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return client;
    }
    public static void DeleteClient(int SSN){
        db = DBConnection.connect();

        try{
            deleteClient = db.prepareStatement("DELETE FROM Client WHERE SSN = ?");
            deleteClient.setInt(1, SSN);
            deleteClient.executeUpdate();

            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
