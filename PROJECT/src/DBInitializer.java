import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            // Drop tables if needed (optional, for development)
            // stmt.execute("DROP TABLE IF EXISTS TakesAutoLoan;");
            // stmt.execute("DROP TABLE IF EXISTS AutoLoan;");
            // ... (etc.)

            // CLIENT
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Client (
                    SSN NUMERIC(9,0) CHECK (001010001 <= SSN),
                    Name CHAR(50),
                    Income BINARY_DOUBLE CHECK (Income >= 0),
                    PRIMARY KEY (SSN)
                );
            """);

            // AUTO LOAN
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS AutoLoan (
                    Auto_Loan_ID INT CHECK (Auto_Loan_ID > 0),
                    Make CHAR(20),
                    Model CHAR(20),
                    Year INT CHECK (1900 <= Year AND Year <= 2030),
                    VIN CHAR(17),
                    Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount > 0),
                    Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),
                    Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),
                    Start_Date CHAR(10),
                    Number_of_payments INT CHECK (Number_of_payments >= 0),
                    End_Date CHAR(10),
                    PRIMARY KEY (Auto_Loan_ID)
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS TakesAutoLoan (
                    SSN NUMERIC(9,0),
                    Auto_Loan_ID INT,
                    PRIMARY KEY (SSN, Auto_Loan_ID),
                    FOREIGN KEY (SSN) REFERENCES Client,
                    FOREIGN KEY (Auto_Loan_ID) REFERENCES AutoLoan
                );
            """);

            // PERSONAL LOAN
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS PersonalLoan (
                    Personal_Loan_ID INT CHECK (Personal_Loan_ID > 0),
                    Loan_purpose CHAR(20),
                    Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount > 0),
                    Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),
                    Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),
                    Start_Date CHAR(10),
                    Number_of_Payments INT DEFAULT 0,
                    End_Date CHAR(10),
                    PRIMARY KEY (Personal_Loan_ID)
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS TakesPersonalLoan (
                    SSN NUMERIC(9,0),
                    Personal_Loan_ID INT,
                    PRIMARY KEY (SSN, Personal_Loan_ID),
                    FOREIGN KEY (SSN) REFERENCES Client,
                    FOREIGN KEY (Personal_Loan_ID) REFERENCES PersonalLoan
                );
            """);

            // STUDENT LOAN
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS StudentLoan (
                    SSN NUMERIC(9,0),
                    Student_Loan_ID INT,
                    Loan_Term CHAR(10),
                    Disbursement_Date CHAR(10),
                    Repayment_Start_Date CHAR(10),
                    Repayment_End_Date CHAR(10),
                    Monthly_Payment NUMERIC(10,2),
                    Grace_Period CHAR(10),
                    Loan_Type CHAR(35),
                    PRIMARY KEY (SSN, Student_Loan_ID),
                    FOREIGN KEY (SSN) REFERENCES Client
                );
            """);

            // MORTGAGE
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Mortgage (
                    Mortgage_ID INT CHECK (Mortgage_ID >= 0),
                    House_Address CHAR(35),
                    House_Area BINARY_DOUBLE CHECK (House_Area >= 0),
                    Number_of_bedrooms NUMERIC(3,0) CHECK (Number_of_bedrooms >= 0),
                    House_Price BINARY_DOUBLE CHECK (House_Price >= 0),
                    Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount >= 0),
                    Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),
                    Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),
                    Start_Date CHAR(10),
                    Number_of_Payments INT DEFAULT 0 CHECK (Number_of_Payments >= 0),
                    End_Date CHAR(10),
                    PRIMARY KEY (Mortgage_ID)
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS TakesMortgage (
                    SSN NUMERIC(9,0),
                    Mortgage_ID INT,
                    PRIMARY KEY (SSN, Mortgage_ID),
                    FOREIGN KEY (SSN) REFERENCES Client,
                    FOREIGN KEY (Mortgage_ID) REFERENCES Mortgage
                );
            """);

            // -------------------------
            // TEST DATA
            // -------------------------
            stmt.execute("INSERT OR IGNORE INTO Client VALUES (555555555,'Logan',100000);");

            stmt.execute("""
                INSERT OR IGNORE INTO Mortgage VALUES (
                    1, '123 Main Street, Apt 4B', 2500.0, 3, 350000.0, 300000.0, 3.5, 15000.0,
                    '2023-01-01', 360, '2053-01-01'
                );
            """);
            stmt.execute("INSERT OR IGNORE INTO TakesMortgage VALUES (555555555, 1);");

            stmt.execute("""
                INSERT OR IGNORE INTO StudentLoan VALUES (
                    555555555, 123456789, '10 years', '2022-01-15', '2022-07-01', '2032-07-01',
                    250.00, '6 months', 'Federal Subsidized Loan'
                );
            """);

            stmt.execute("""
                INSERT OR IGNORE INTO AutoLoan VALUES (
                    22, 'Toyota', 'Camry', 2020, '1HGCM82633A004352', 25000.00, 0.05,
                    5000.00, '2022-01-01', 60, '2027-01-01'
                );
            """);
            stmt.execute("INSERT OR IGNORE INTO TakesAutoLoan VALUES (555555555, 22);");

            stmt.execute("""
                INSERT OR IGNORE INTO PersonalLoan VALUES (
                    33, 'Debt Consolidation', 5000.0, 5.5, 0.0, '2023-04-01', 0, '2024-04-01'
                );
            """);
            stmt.execute("INSERT OR IGNORE INTO TakesPersonalLoan VALUES (555555555, 33);");

            System.out.println("Database initialized successfully.");

        } catch (Exception e) {
            System.out.println("Error initializing DB: " + e.getMessage());
        }
    }
}
