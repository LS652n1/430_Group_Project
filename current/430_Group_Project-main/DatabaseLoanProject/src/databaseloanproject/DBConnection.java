package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;

        try {
            //String url = "jdbc:sqlite:loans.db";
            String url = "jdbc:sqlite:SQL3.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite.");
            String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table ensured.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return conn;
    }
}