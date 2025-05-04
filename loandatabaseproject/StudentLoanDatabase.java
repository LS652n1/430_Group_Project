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
public class StudentLoanDatabase {
    private static PreparedStatement addStudent;
    private static PreparedStatement editStudentPurpose;
    private static PreparedStatement editStudentAmount;
    private static PreparedStatement editStudentInterest;
    private static PreparedStatement editStudentPaid;
    private static PreparedStatement editStudentStart;
    private static PreparedStatement editStudentPayments;
    private static PreparedStatement editStudentEnd;
    private static PreparedStatement findStudent;
    private static PreparedStatement deleteStudent;
    private static PreparedStatement allStudentLoan;
    private static PreparedStatement allStudentLoanSSN;
    private static PreparedStatement allStudentLoanID;
    private static ResultSet resultSet;
    private static Connection db;
    
    public static void AddStudent(StudentLoan student){
        int ssn = student.getSSN();
        int studentID = student.getStudentLoanID();
        String term = student.getTerm();
        String disbursementDate = student.getDisbursementDate();
        String start = student.getStart();
        String end = student.getEnd();
        double payment = student.getPayment();
        String gracePeriod = student.getGracePeriod();
        String loanType = student.getLoanType();
        db = DBConnection.connect();
        
        try{
            addStudent = db.prepareStatement("INSERT INTO StudentLoan (SSN, Student_Loan_ID, Loan_Term, Disbursement_Date, Repayment_Start_Date, Repayment_End_Date, Monthly_Payment, Grace_Period, Loan_Type) values (?,?,?,?,?,?,?,?,?)");
            addStudent.setInt(1, ssn);
            addStudent.setInt(2, studentID);
            addStudent.setString(3, term);
            addStudent.setString(4, disbursementDate);
            addStudent.setString(5, start);
            addStudent.setString(6, end);
            addStudent.setDouble(7, payment);
            addStudent.setString(8, gracePeriod);
            addStudent.setString(9, loanType);
            addStudent.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static StudentLoan FindStudent(int studentID, int ssn){
        db = DBConnection.connect();
        StudentLoan student = null;
        try{
            findStudent = db.prepareStatement("SELECT * FROM StudentLoan WHERE Student_Loan_ID = ? AND SSN = ?");
            findStudent.setInt(1, studentID);
            findStudent.setInt(2, ssn);
            resultSet = findStudent.executeQuery();

            while(resultSet.next()){
                student = new StudentLoan(resultSet.getInt("SSN"), resultSet.getInt("Student_Loan_ID"), resultSet.getString("Loan_Term"), resultSet.getString("Disbursement_Date"), resultSet.getString("Repayment_Start_Date"), resultSet.getString("Repayment_End_Date"), resultSet.getDouble("Monthly_Payment"), resultSet.getString("Grace_Period"), resultSet.getString("Loan_Type"));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return student;
    }
    
    public static void EditStudentTerm(int studentID, String term, int ssn){
        db = DBConnection.connect();
        try{
            editStudentPurpose = db.prepareStatement("UPDATE StudentLoan SET Loan_Term = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentPurpose.setString(1, term);
            editStudentPurpose.setInt(2, studentID);
            editStudentPurpose.setInt(3, ssn);
            editStudentPurpose.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void EditStudentDisbursementDate(int studentID, String disbursementDate, int ssn){
        db = DBConnection.connect();
        try{
            editStudentAmount = db.prepareStatement("UPDATE StudentLoan SET Disbursement_Date = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentAmount.setString(1, disbursementDate);
            editStudentAmount.setInt(2, studentID);
            editStudentAmount.setInt(3, ssn);
            editStudentAmount.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditStudentStart(int studentID, String start, int ssn){
        db = DBConnection.connect();
        try{
            editStudentInterest = db.prepareStatement("UPDATE StudentLoan SET Repayment_Start_Date = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentInterest.setString(1, start);
            editStudentInterest.setInt(2, studentID);
            editStudentInterest.setInt(3, ssn);
            editStudentInterest.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditStudentEnd(int studentID, String end, int ssn){
        db = DBConnection.connect();
        try{
            editStudentPaid = db.prepareStatement("UPDATE StudentLoan SET Repayment_End_Date = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentPaid.setString(1, end);
            editStudentPaid.setInt(2, studentID);
            editStudentPaid.setInt(3, ssn);
            editStudentPaid.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditStudentPayment(int studentID, double payment, int ssn){
        db = DBConnection.connect();
        try{
            editStudentStart = db.prepareStatement("UPDATE StudentLoan SET Monthly_Payment = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentStart.setDouble(1, payment);
            editStudentStart.setInt(2, studentID);
            editStudentStart.setInt(3, ssn);
            editStudentStart.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditStudentGracePeriod(int studentID, String gracePeriod, int ssn){
        db = DBConnection.connect();
        try{
            editStudentPayments = db.prepareStatement("UPDATE StudentLoan SET Grace_Period = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentPayments.setString(1, gracePeriod);
            editStudentPayments.setInt(2, studentID);
            editStudentPayments.setInt(3, ssn);
            editStudentPayments.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static void EditStudentLoanType(int studentID, String loanType, int ssn){
        db = DBConnection.connect();
        try{
            editStudentEnd = db.prepareStatement("UPDATE StudentLoan SET Loan_Type = ? WHERE Student_Loan_ID = ? AND SSN = ?");
            editStudentEnd.setString(1, loanType);
            editStudentEnd.setInt(2, studentID);
            editStudentEnd.setInt(3, ssn);
            editStudentEnd.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public static void DeleteStudent(int studentID, int ssn){
        db = DBConnection.connect();

        try{
            deleteStudent = db.prepareStatement("DELETE FROM StudentLoan WHERE Student_Loan_ID = ? AND SSN = ?");
            deleteStudent.setInt(1, studentID);
            deleteStudent.setInt(2, ssn);
            deleteStudent.executeUpdate();

            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static ArrayList<StudentLoan> AllStudentLoan(){
        StudentLoan studentLoan;
        ArrayList<StudentLoan> studentLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allStudentLoan = db.prepareStatement("SELECT * FROM StudentLoan");
            
            resultSet = allStudentLoan.executeQuery();
            while(resultSet.next()){
                studentLoan = new StudentLoan(resultSet.getInt("SSN"), resultSet.getInt("Student_Loan_ID"), resultSet.getString("Loan_Term"), resultSet.getString("Disbursement_Date"), resultSet.getString("Repayment_Start_Date"), resultSet.getString("Repayment_End_Date"), resultSet.getDouble("Monthly_Payment"), resultSet.getString("Grace_Period"), resultSet.getString("Loan_Type"));
                studentLoanList.add(studentLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return studentLoanList;
    }
    public static ArrayList<StudentLoan> AllStudentLoanSSN(int SSN){
        StudentLoan studentLoan;
        ArrayList<StudentLoan> studentLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allStudentLoanSSN = db.prepareStatement("SELECT * FROM StudentLoan WHERE SSN = ?");
            allStudentLoanSSN.setInt(1, SSN);
            resultSet = allStudentLoanSSN.executeQuery();
            while(resultSet.next()){
                studentLoan = new StudentLoan(resultSet.getInt("SSN"), resultSet.getInt("Student_Loan_ID"), resultSet.getString("Loan_Term"), resultSet.getString("Disbursement_Date"), resultSet.getString("Repayment_Start_Date"), resultSet.getString("Repayment_End_Date"), resultSet.getDouble("Monthly_Payment"), resultSet.getString("Grace_Period"), resultSet.getString("Loan_Type"));
                studentLoanList.add(studentLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return studentLoanList;
    }
    public static ArrayList<StudentLoan> AllStudentLoanID(int studentID){
        StudentLoan studentLoan;
        ArrayList<StudentLoan> studentLoanList = new ArrayList<>();
        db = DBConnection.connect();

        try{
            
            allStudentLoanID = db.prepareStatement("SELECT * FROM StudentLoan WHERE Student_Loan_ID = ?");
            allStudentLoanID.setInt(1, studentID);
            resultSet = allStudentLoanID.executeQuery();
            while(resultSet.next()){
                studentLoan = new StudentLoan(resultSet.getInt("SSN"), resultSet.getInt("Student_Loan_ID"), resultSet.getString("Loan_Term"), resultSet.getString("Disbursement_Date"), resultSet.getString("Repayment_Start_Date"), resultSet.getString("Repayment_End_Date"), resultSet.getDouble("Monthly_Payment"), resultSet.getString("Grace_Period"), resultSet.getString("Loan_Type"));
                studentLoanList.add(studentLoan);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return studentLoanList;
    }
}
