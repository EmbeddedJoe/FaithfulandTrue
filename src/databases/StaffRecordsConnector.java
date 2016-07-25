package databases;

import gasstationmanager.SignUp;
import gasstationmanager.StaffMembers;
import gasstationmanager.UserLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;



public class StaffRecordsConnector extends UserLogin {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/staffrecords";
    Connection myConnection = null;
    PreparedStatement myStatement = null;
    PreparedStatement AuthenticateLoginStatement = null;
    //PreparedStatement myCompareStatement = null;
    String dbuserName = "root";
    String dbpwd = "";
    public boolean connectedToDatabase = false;
    //String passwordQuery = "SELECT * FROM `station staff` WHERE Username = '"+userLogin+"' AND Password = '"+passwordLogin+"' ";
    
    public StaffRecordsConnector() {
        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createConnection() {
        try {
             myConnection = (Connection) DriverManager.getConnection(DATABASE_URL, dbuserName,dbpwd);
             connectedToDatabase = true;
        } catch (SQLException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public PreparedStatement createStatement(StaffMembers detailsToInsert ) {
        PreparedStatement  myStatement = null;
        try {
            
            
           
            char[] staffpassword = detailsToInsert.getStaffPassWord();
            String strPassword = Arrays.toString(staffpassword);
            myStatement = myConnection.prepareStatement("INSERT INTO `station staff`(`First Name`, `Last Name`, `Work ID`, `Username`, `Password`, `Position`) VALUES (?,?,?,?,?,?) ");
            
            myStatement.setString(1, detailsToInsert.getFirstName());
            myStatement.setString(2, detailsToInsert.getLastName());
            myStatement.setString(3, detailsToInsert.getWorkID());
            myStatement.setString(4, detailsToInsert.getStaffUserName());
            myStatement.setString(5, strPassword);
            myStatement.setString(6, detailsToInsert.getStaffPosition());
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myStatement;
    }
    
    public PreparedStatement createStatement(UserLogin detailsToAuthenticate) {
        PreparedStatement AuthenticateLoginStatement = null;
        try {
            
            String queryUsernameAndPassword = "SELECT `Username`, `Password` FROM `station staff` WHERE Username = ? AND Password = ?";
            AuthenticateLoginStatement = myConnection.prepareStatement(queryUsernameAndPassword);
            
            AuthenticateLoginStatement.setString(1, detailsToAuthenticate.LoginUsername());
            AuthenticateLoginStatement.setString(2, detailsToAuthenticate.LoginPassword());
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AuthenticateLoginStatement;
    }
    
    public void insertDetails(PreparedStatement myStatement) {
        try {
            myStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean authenticateUser(PreparedStatement AuthenticateLoginStatement) {
        
        boolean isAuthentic = true;
        try {
            
            ResultSet queryResult;
            queryResult = AuthenticateLoginStatement.executeQuery();
            while (queryResult.next()) {
                String firstname = queryResult.getString("First Name");
            }
            if (null == queryResult) {
                isAuthentic = false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAuthentic;
    }
    
    public void closeConnection(PreparedStatement myStatement) {
        if (connectedToDatabase) {
            try {
                myStatement.close();
                myConnection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffRecordsConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}