import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // SQLite creates the file if it doesn't exist
            String url = "jdbc:sqlite:loans.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite.");

            // Create table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL" +
                    ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table ensured.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return conn;
    }
}
