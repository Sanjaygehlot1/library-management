/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author dell
 */
public class DBconnection {
    static Connection con= null;
   public static Connection getConnection(){
     try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management", "root", "unknown1695");
} catch (ClassNotFoundException e) {
    System.out.println("JDBC Driver not found.");
    e.printStackTrace();
} catch (SQLException e) {
    System.out.println("Connection failed.");
    e.printStackTrace();
}
     return con;
   }
   
}
