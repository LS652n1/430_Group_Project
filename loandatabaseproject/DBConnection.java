/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loandatabaseproject;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author rdenn
 */
public class DBConnection {
    
    public static Connection connect(){
        
        String url = "jdbc:sqlite:C:\\Users\\rdenn\\OneDrive\\Documents\\NetBeansProjects\\LoanDatabaseProject\\src\\loandatabaseproject\\loanDB.db";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            return connection;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
