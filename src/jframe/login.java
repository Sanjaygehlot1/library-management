/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import app.bolivia.swing.JCTextField;
import java.sql.Connection;
import java.sql.DriverManager;
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

    protected abstract String getquery();

    public login() {
    }

    public boolean validateLogin() {
        String name = txt_username.getText();
        String password = txt_password.getText();

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter username");
            return false;
        }
        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter password");
            return false;
        }

        return true;
    }

    //verify creds
    public void login() {
        String name = txt_username.getText();
        String password = txt_password.getText();
        String query = getquery();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management", "root", "unknown1695");
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "login successful");
                afterlogin();

            } else {
                JOptionPane.showMessageDialog(this, "incorrect username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void afterlogin() {

    }
}
