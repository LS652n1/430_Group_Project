package databaseloanproject;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    public static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("    CREATE TABLE IF NOT EXISTS Client (\n        SSN NUMERIC(9,0) CHECK (001010001 <= SSN),\n        Name CHAR(50),\n        Income BINARY_DOUBLE CHECK (Income >= 0),\n        PRIMARY KEY (SSN)\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS AutoLoan (\n        Auto_Loan_ID INT CHECK (Auto_Loan_ID > 0),\n        Make CHAR(20),\n        Model CHAR(20),\n        Year INT CHECK (1900 <= Year AND Year <= 2030),\n        VIN CHAR(17),\n        Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount > 0),\n        Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),\n        Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),\n        Start_Date CHAR(10),\n        Number_of_payments INT CHECK (Number_of_payments >= 0),\n        End_Date CHAR(10),\n        PRIMARY KEY (Auto_Loan_ID)\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS TakesAutoLoan (\n        SSN NUMERIC(9,0),\n        Auto_Loan_ID INT,\n        PRIMARY KEY (SSN, Auto_Loan_ID),\n        FOREIGN KEY (SSN) REFERENCES Client,\n        FOREIGN KEY (Auto_Loan_ID) REFERENCES AutoLoan\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS PersonalLoan (\n        Personal_Loan_ID INT CHECK (Personal_Loan_ID > 0),\n        Loan_purpose CHAR(20),\n        Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount > 0),\n        Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),\n        Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),\n        Start_Date CHAR(10),\n        Number_of_Payments INT DEFAULT 0,\n        End_Date CHAR(10),\n        PRIMARY KEY (Personal_Loan_ID)\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS TakesPersonalLoan (\n        SSN NUMERIC(9,0),\n        Personal_Loan_ID INT,\n        PRIMARY KEY (SSN, Personal_Loan_ID),\n        FOREIGN KEY (SSN) REFERENCES Client,\n        FOREIGN KEY (Personal_Loan_ID) REFERENCES PersonalLoan\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS StudentLoan (\n        SSN NUMERIC(9,0),\n        Student_Loan_ID INT,\n        Loan_Term CHAR(10),\n        Disbursement_Date CHAR(10),\n        Repayment_Start_Date CHAR(10),\n        Repayment_End_Date CHAR(10),\n        Monthly_Payment NUMERIC(10,2),\n        Grace_Period CHAR(10),\n        Loan_Type CHAR(35),\n        PRIMARY KEY (SSN, Student_Loan_ID),\n        FOREIGN KEY (SSN) REFERENCES Client\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS Mortgage (\n        Mortgage_ID INT CHECK (Mortgage_ID >= 0),\n        House_Address CHAR(35),\n        House_Area BINARY_DOUBLE CHECK (House_Area >= 0),\n        Number_of_bedrooms NUMERIC(3,0) CHECK (Number_of_bedrooms >= 0),\n        House_Price BINARY_DOUBLE CHECK (House_Price >= 0),\n        Loan_Amount BINARY_DOUBLE CHECK (Loan_Amount >= 0),\n        Interest_Rate BINARY_DOUBLE CHECK (Interest_Rate >= 0),\n        Amount_Paid BINARY_DOUBLE DEFAULT 0 CHECK (Amount_Paid >= 0),\n        Start_Date CHAR(10),\n        Number_of_Payments INT DEFAULT 0 CHECK (Number_of_Payments >= 0),\n        End_Date CHAR(10),\n        PRIMARY KEY (Mortgage_ID)\n    );\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS TakesMortgage (\n        SSN NUMERIC(9,0),\n        Mortgage_ID INT,\n        PRIMARY KEY (SSN, Mortgage_ID),\n        FOREIGN KEY (SSN) REFERENCES Client,\n        FOREIGN KEY (Mortgage_ID) REFERENCES Mortgage\n    );\n");
            stmt.execute("INSERT OR IGNORE INTO Client VALUES (555555555,'Logan',100000);");
            stmt.execute("    INSERT OR IGNORE INTO Mortgage VALUES (\n        1, '123 Main Street, Apt 4B', 2500.0, 3, 350000.0, 300000.0, 3.5, 15000.0,\n        '2023-01-01', 360, '2053-01-01'\n    );\n");
            stmt.execute("INSERT OR IGNORE INTO TakesMortgage VALUES (555555555, 1);");
            stmt.execute("    INSERT OR IGNORE INTO StudentLoan VALUES (\n        555555555, 123456789, '10 years', '2022-01-15', '2022-07-01', '2032-07-01',\n        250.00, '6 months', 'Federal Subsidized Loan'\n    );\n");
            stmt.execute("    INSERT OR IGNORE INTO AutoLoan VALUES (\n        22, 'Toyota', 'Camry', 2020, '1HGCM82633A004352', 25000.00, 0.05,\n        5000.00, '2022-01-01', 60, '2027-01-01'\n    );\n");
            stmt.execute("INSERT OR IGNORE INTO TakesAutoLoan VALUES (555555555, 22);");
            stmt.execute("    INSERT OR IGNORE INTO PersonalLoan VALUES (\n        33, 'Debt Consolidation', 5000.0, 5.5, 0.0, '2023-04-01', 0, '2024-04-01'\n    );\n");
            stmt.execute("INSERT OR IGNORE INTO TakesPersonalLoan VALUES (555555555, 33);");
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            System.out.println("Error initializing DB: " + e.getMessage());
        }

    }
}
