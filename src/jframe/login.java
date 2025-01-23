/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import Studentdashboard.Homepage;
import app.bolivia.swing.JCTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/**
 *
 * @author dell
 */
public abstract class login extends javax.swing.JFrame {

    protected JCTextField txt_username;
    protected JCTextField txt_password;
    protected JCTextField txt_studentid;

    protected abstract String getquery();

    public login() {
    }

   public boolean validateLogin() {
    String name = txt_username.getText().trim();
    String password = txt_password.getText().trim();
    String id = txt_studentid.getText().trim();

    // Validate input fields
    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter username");
        return false;
    }
    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter password");
        return false;
    }
    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Student ID");
        return false;
    }
    return  authenticateUser(name,password,id);
}
   
    public void loginSuccessful(String studentId) {
        // After successful login, open the Homepage with the student ID
        new Homepage(studentId).setVisible(true);
        this.dispose(); // Close the login window if necessary
    }
   
public boolean authenticateUser(String username, String password, String studentId) {
        // Validate user against the database
        if (isValidUser(username, password, studentId)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            afterlogin(studentId); // Open the dashboard with the valid student ID
            return true; // Login successful
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
            return false;
        }
    }

    // Validate user against the database
   

    public boolean isValidUser(String username, String password, String studentId) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establish the database connection
            con = DBconnection.getConnection();

            // SQL query to check for the user in the database
            String query = "SELECT * FROM students WHERE name = ? AND password = ? AND id = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, studentId);
            
            // Execute the query
            rs = pst.executeQuery();
            
            return rs.next();
            // If there is a result, it means the credentials are valid

        } catch (Exception e) {
            e.printStackTrace(); // Handle any SQL exceptions
            return false; // Return false in case of any errors
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions during closing
            }
        }
    }

    // This method can be used for additional login checks or actions post-login
    protected void afterlogin(String studentId) {
        // Implementation for actions after login, if any
        Homepage stdashboard= new Homepage(studentId);
        stdashboard.setVisible(true);
        this.dispose();
    }
}
