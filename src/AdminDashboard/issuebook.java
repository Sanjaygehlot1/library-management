/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdminDashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import jframe.DBconnection;

/**
 *
 * @author Vignesh Dandu
 */
public class issuebook extends javax.swing.JFrame {

    /**
     * Create
     */
      Locale l = null;
    public issuebook() {
        initComponents();
        l = new Locale("en", "US");
        txtissuedate.setLocale(l);
    }

    //to fetch the book details from the database and display it to book details panel
    public void getBookDetails() {
    String bookId = issuebookid.getText();

    try {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM books WHERE BookID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, bookId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            bookiddetails.setText(rs.getString("BookID"));
            booknamedetails.setText(rs.getString("Name"));
            authordetails.setText(rs.getString("Publisher"));
            quantitydetails.setText(rs.getString("Quantity"));
        } else {
            // Clear fields if no book is found
            bookiddetails.setText("");
            booknamedetails.setText("");
            authordetails.setText("");
            quantitydetails.setText("");
            JOptionPane.showMessageDialog(this, "No book found with this ID");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //to fetch the student details from the database and display it to student details panel
   public void getStudentDetails() {
    String studentIdText = txtstudentid.getText().trim();
    
    if (studentIdText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Student ID");
        return;
    }

    try {
        int studentId = Integer.parseInt(studentIdText);
        
        try (Connection con = DBconnection.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT id, name FROM students WHERE id = ?")) {
            
            pst.setInt(1, studentId);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    studentiddetails.setText(rs.getString("id"));
                    studentnamedetails.setText(rs.getString("name"));
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with ID: " + studentId);
                    studentiddetails.setText("");
                    studentnamedetails.setText("");
                }
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Student ID. Please enter a valid number.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void updateBookCount() {
    String bookId = issuebookid.getText();
    try {
        Connection con = DBconnection.getConnection();
        String sql = "update books set Quantity = Quantity - 1 where BookID = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, bookId);

        int rowCount = pst.executeUpdate();

        if (rowCount > 0) {
            JOptionPane.showMessageDialog(this, "Book count updated");
            int initialCount = Integer.parseInt(quantitydetails.getText());
            quantitydetails.setText(Integer.toString(initialCount - 1));
        } else {
            JOptionPane.showMessageDialog(this, "Can't update book count");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    //insert issue book details to database
    public boolean issueBook() {
    boolean isIssued = false;
    String bookId = issuebookid.getText();
    String studentId = txtstudentid.getText();
    String bookName = booknamedetails.getText();
    String studentName = studentnamedetails.getText();

    int quantity = Integer.parseInt(quantitydetails.getText());
    if (quantity <= 0) {
        JOptionPane.showMessageDialog(this, "Out of Stock");
        return false;
    }
    

    Date uIssueDate = txtissuedate.getDatoFecha();
    Date uDueDate = txtduedate.getDatoFecha();

    Long l1 = uIssueDate.getTime();
    long l2 = uDueDate.getTime();

    java.sql.Date sIssueDate = new java.sql.Date(l1);
    java.sql.Date sDueDate = new java.sql.Date(l2);

    try {
        Connection con = DBconnection.getConnection();
        String sql = "INSERT INTO issue(BookID, StudentID, issueDate, Duedate, Returned) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, bookId);
        pst.setString(2, studentId);
        pst.setDate(3, sIssueDate);
        pst.setDate(4, sDueDate);
        pst.setString(5, "NO");

        int rowCount = pst.executeUpdate();
        if (rowCount > 0) {
            isIssued = true;
        } else {
            isIssued = false;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return isIssued;
}

    //checking whether book already allocated or not
    public boolean isAlreadyIssued() {

        boolean isAlreadyIssued = false;
        String bookId = bookiddetails.getText();
        String studentId = studentiddetails.getText();

        try {
            Connection con = DBconnection.getConnection();
            String sql = "select * from issue where BookID = ? and StudentID = ? and Returned = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookId);
            pst.setString(2, studentId);
            pst.setString(3, "NO");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlreadyIssued;

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
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        studentnamedetails = new app.bolivia.swing.JCTextField();
        studentiddetails = new app.bolivia.swing.JCTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        booknamedetails = new app.bolivia.swing.JCTextField();
        authordetails = new app.bolivia.swing.JCTextField();
        quantitydetails = new app.bolivia.swing.JCTextField();
        bookiddetails = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        issuebookid = new app.bolivia.swing.JCTextField();
        txtstudentid = new app.bolivia.swing.JCTextField();
        txtduedate = new rojeru_san.componentes.RSDateChooser();
        txtissuedate = new rojeru_san.componentes.RSDateChooser();
        issuebtn = new necesario.RSMaterialButtonCircle();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, 140, 40));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student Id :");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Student Name :");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel23.setText("Student Details");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 320, -1));

        studentnamedetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentnamedetailsActionPerformed(evt);
            }
        });
        jPanel2.add(studentnamedetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, -1));

        studentiddetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentiddetailsActionPerformed(evt);
            }
        });
        jPanel2.add(studentiddetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 390, 580));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel2.setText(" Book Details");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 32, 283, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity :");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Book Id :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Name :");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Author :");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Book Id :");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        booknamedetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booknamedetailsActionPerformed(evt);
            }
        });
        jPanel4.add(booknamedetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        authordetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authordetailsActionPerformed(evt);
            }
        });
        jPanel4.add(authordetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        quantitydetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantitydetailsActionPerformed(evt);
            }
        });
        jPanel4.add(quantitydetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));
        jPanel4.add(bookiddetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 390, 580));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel4.setText("   Issue Book");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, 320, -1));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 51));
        jLabel24.setText("x");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 40, 40));

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Book Id :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, -1, -1));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Student Id :");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, -1, -1));

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Issue Date :");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 300, -1, -1));

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Due Date :");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 360, -1, -1));

        issuebookid.setBackground(new java.awt.Color(204, 204, 204));
        issuebookid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        issuebookid.setPlaceholder("Enter Book ID..");
        issuebookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                issuebookidFocusLost(evt);
            }
        });
        issuebookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebookidActionPerformed(evt);
            }
        });
        jPanel1.add(issuebookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 190, -1, -1));

        txtstudentid.setBackground(new java.awt.Color(204, 204, 204));
        txtstudentid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtstudentid.setPlaceholder("Enter Student ID");
        txtstudentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtstudentidFocusLost(evt);
            }
        });
        txtstudentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstudentidActionPerformed(evt);
            }
        });
        jPanel1.add(txtstudentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 250, 200, 30));

        txtduedate.setPlaceholder("Enter Due Date");
        jPanel1.add(txtduedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 360, -1, -1));

        txtissuedate.setPlaceholder("Enter Issue Date");
        jPanel1.add(txtissuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 300, -1, -1));

        issuebtn.setText("Issue");
        issuebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                issuebtnMouseClicked(evt);
            }
        });
        issuebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebtnActionPerformed(evt);
            }
        });
        jPanel1.add(issuebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 410, 140, 70));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Library_Book_532388_1366x768.jpg"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1290, 700));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 790));

        setSize(new java.awt.Dimension(1291, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        AdminDashboard home = new AdminDashboard();
        home.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void booknamedetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booknamedetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booknamedetailsActionPerformed

    private void authordetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authordetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authordetailsActionPerformed

    private void quantitydetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantitydetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantitydetailsActionPerformed

    private void issuebookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issuebookidActionPerformed

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
      dispose();
    }//GEN-LAST:event_jLabel24MouseClicked

    private void studentnamedetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentnamedetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentnamedetailsActionPerformed

    private void studentiddetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentiddetailsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentiddetailsActionPerformed

    private void txtstudentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstudentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstudentidActionPerformed

    private void issuebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_issuebtnMouseClicked
         
    }//GEN-LAST:event_issuebtnMouseClicked

    private void txtstudentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtstudentidFocusLost
      if (!txtstudentid.getText().equals("")) {
            getStudentDetails();
        }
    }//GEN-LAST:event_txtstudentidFocusLost

    private void issuebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebtnActionPerformed
    if (isAlreadyIssued() == false) {
        if (issueBook() == true) {
            JOptionPane.showMessageDialog(this, "Book issued successfully");
            updateBookCount();
        } else {
            JOptionPane.showMessageDialog(this, "Can't issue the book");
        }
    } else {
        JOptionPane.showMessageDialog(this, "This student already has this book");
    }
     
    }//GEN-LAST:event_issuebtnActionPerformed

    private void issuebookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_issuebookidFocusLost
        if (!issuebookid.getText().equals("")) {
            getBookDetails();
        }
    }//GEN-LAST:event_issuebookidFocusLost

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
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issuebook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField authordetails;
    private app.bolivia.swing.JCTextField bookiddetails;
    private app.bolivia.swing.JCTextField booknamedetails;
    private app.bolivia.swing.JCTextField issuebookid;
    private necesario.RSMaterialButtonCircle issuebtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private app.bolivia.swing.JCTextField quantitydetails;
    private app.bolivia.swing.JCTextField studentiddetails;
    private app.bolivia.swing.JCTextField studentnamedetails;
    private rojeru_san.componentes.RSDateChooser txtduedate;
    private rojeru_san.componentes.RSDateChooser txtissuedate;
    private app.bolivia.swing.JCTextField txtstudentid;
    // End of variables declaration//GEN-END:variables
}
