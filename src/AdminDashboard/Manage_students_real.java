/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdminDashboard;

import Studentdashboard.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import jframe.DBconnection;
/**
 *
 * @author Vignesh Dandu
 */
public class Manage_students_real extends javax.swing.JFrame {

    /**
     * Creates new form Manage_students
     */
     
    DefaultTableModel model;
    
    public Manage_students_real() {
        
    initComponents();
            setStudentDetailsToTable();

     setLocationRelativeTo(null);
    addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentShown(java.awt.event.ComponentEvent evt) {
            mystudentsComponentShown(evt);
        }
    });
    }
        private void setStudentDetailsToTable() {
    DefaultTableModel model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Email", "Contact"}, 0);
mystudents.setModel(model); // Set the model to your table
 // Clear existing rows

    try {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT id, name, email, contact FROM students"; // Include all relevant columns
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String studentId = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String contact = rs.getString("contact");
            model.addRow(new Object[]{studentId, name, email, contact}); // Add all relevant data to the table
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
    }
}

    
    //to add student to student_details table
    public boolean addStudent() {
    boolean isAdded = false;
    String studentName = txt_studentName.getText();
    String studentEmail = stemail.getText();
    String studentContact = stcontact.getText();

    if (studentName.isEmpty() || studentEmail.isEmpty() || studentContact.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.");
        return false;
    }

    try {
        Connection con = DBconnection.getConnection();
        String sql = "INSERT INTO students (name, email, contact) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, studentName);
        pst.setString(2, studentEmail);
        pst.setString(3, studentContact);
        
        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isAdded = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage());
    }
    
    return isAdded;
}

    //to update student details
    
    public boolean updateStudent(){
          boolean isUpdated = false;
    String studentId = txt_studentId.getText();
    String studentName = txt_studentName.getText();
    String studentEmail = stemail.getText();
    String studentContact = stcontact.getText();

    if (studentId.isEmpty() || studentName.isEmpty() || studentEmail.isEmpty() || studentContact.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.");
        return false;
    }

    try {
        Connection con = DBconnection.getConnection();
        String sql = "UPDATE students SET name=?, email=?, contact=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, studentName);
        pst.setString(2, studentEmail);
        pst.setString(3, studentContact);
        pst.setString(4, studentId);

        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isUpdated = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage());
    }

    return isUpdated;
}

    
    //method to delete book detail
   public boolean deleteStudent() {
    boolean isDeleted = false;
    String studentId = txt_studentId.getText();

    if (studentId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Student ID cannot be empty.");
        return false;
    }

    try {
        Connection con = DBconnection.getConnection();
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, studentId);

        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isDeleted = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage());
    }

    return isDeleted;
}

    //method to clear table
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) mystudents.getModel();
        model.setRowCount(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stemail = new app.bolivia.swing.JCTextField();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jScrollPane1 = new javax.swing.JScrollPane();
        mystudents = new rojeru_san.complementos.RSTableMetro();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_studentName = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        stcontact = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 0, 0));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel2.setText("Manage Students");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 410, 110));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("Refresh");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 140, 30));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Enter Student Id");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        stemail.setBackground(new java.awt.Color(0, 0, 0));
        stemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        stemail.setForeground(new java.awt.Color(255, 255, 255));
        stemail.setPhColor(new java.awt.Color(255, 255, 255));
        stemail.setPlaceholder("Enter Student Name");
        stemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stemailActionPerformed(evt);
            }
        });
        jPanel1.add(stemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 260, -1));

        txt_studentId.setBackground(new java.awt.Color(0, 0, 0));
        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txt_studentId.setForeground(new java.awt.Color(255, 255, 255));
        txt_studentId.setPhColor(new java.awt.Color(255, 255, 255));
        txt_studentId.setPlaceholder("Enter Student Id");
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 260, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 40, 70));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Enter Student Name");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 60, 70));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonCircle1.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle1.setText("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 130, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonCircle2.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle2.setText("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonCircle3.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonCircle3.setText("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, 130, 70));

        mystudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Email", "Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mystudents.setColorBackgoundHead(new java.awt.Color(0, 0, 0));
        mystudents.setIntercellSpacing(new java.awt.Dimension(1, 1));
        mystudents.setRowHeight(40);
        mystudents.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                mystudentsComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(mystudents);
        if (mystudents.getColumnModel().getColumnCount() > 0) {
            mystudents.getColumnModel().getColumn(0).setResizable(false);
            mystudents.getColumnModel().getColumn(1).setResizable(false);
            mystudents.getColumnModel().getColumn(2).setResizable(false);
            mystudents.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 770, 250));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("x");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 30, 50));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 460, 10));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Enter Student Email");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

        txt_studentName.setBackground(new java.awt.Color(0, 0, 0));
        txt_studentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txt_studentName.setForeground(new java.awt.Color(255, 255, 255));
        txt_studentName.setPhColor(new java.awt.Color(255, 255, 255));
        txt_studentName.setPlaceholder("Enter Student Name");
        jPanel1.add(txt_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 260, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 60, 70));

        stcontact.setBackground(new java.awt.Color(0, 0, 0));
        stcontact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        stcontact.setForeground(new java.awt.Color(255, 255, 255));
        stcontact.setPhColor(new java.awt.Color(255, 255, 255));
        stcontact.setPlaceholder("Enter Student Name");
        stcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stcontactActionPerformed(evt);
            }
        });
        jPanel1.add(stcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 260, -1));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter Contact No.");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 60, 70));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WhatsApp Image 2024-08-15 at 10.47.11_ce530663.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, -1, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dispose();
        Manage_students_real managest = new Manage_students_real();
        managest.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateStudent()== true) {
            JOptionPane.showMessageDialog(this, "Student Updated");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "student Updation Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        dispose();
        AdminDashboard admindb = new AdminDashboard();
    admindb.setVisible(false);
    admindb.setVisible(true);     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
         if (deleteStudent()== true) {
            JOptionPane.showMessageDialog(this, "student Deleted");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "student Deletion Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
if (addStudent()== true) {
            JOptionPane.showMessageDialog(this, "Student Added");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Addition Failed");
        }       
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void mystudentsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mystudentsComponentShown
// Assuming you have imported the necessary packages for SQL and Swing components
Connection con = null;
Statement st = null;
ResultSet rs = null;

try {
    // Establish connection
    con = ConnectionProvider.getCon(); 
    st = con.createStatement();

    // SQL query to retrieve all student details
    String query = "SELECT name, id, email, contact FROM students";

    // Execute the query and store the result set
    rs = st.executeQuery(query);

    // Set the model for the RSMetroTable to display the result set
    mystudents.setModel(DbUtils.resultSetToTableModel(rs));

} catch (SQLException e) {
    e.printStackTrace();
    // Display an error message in case of failure
    JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
} finally {
    // Close all the resources in the finally block
    try {
        if (rs != null) rs.close();
        if (st != null) st.close();
        if (con != null) con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    }//GEN-LAST:event_mystudentsComponentShown

    private void stemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stemailActionPerformed

    private void stcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stcontactActionPerformed

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage_students_real.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_students_real.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_students_real.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_students_real.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_students_real().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro mystudents;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private app.bolivia.swing.JCTextField stcontact;
    private app.bolivia.swing.JCTextField stemail;
    private app.bolivia.swing.JCTextField txt_studentId;
    private app.bolivia.swing.JCTextField txt_studentName;
    // End of variables declaration//GEN-END:variables
}
