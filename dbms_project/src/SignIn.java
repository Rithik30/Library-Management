import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignIn extends javax.swing.JFrame {

    public SignIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        btnlogin = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        // Names and UIDs
        String userDetails = "<html>" +
                "<div style='text-align: right;'>" +
                "Rithik - 23BCS10661<br>" +
                "Harleen - 23BCS10342<br>" +
                "Pushpendra - 23BCS10701<br>" +
                "Himanshi - 23BCS10694<br>" +
                "Ruchi - 23BCS10713" +
                "</div>" +
                "</html>";
        JLabel userListLabel = new JLabel(userDetails);
        userListLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userListLabel.setForeground(Color.WHITE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1316, 0, 50, -1));

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 118, 36, 31));

        txtemail.setFont(new java.awt.Font("Sitka Display", 1, 18));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 264, 41));

        btnlogin.setBackground(new java.awt.Color(255, 51, 51));
        btnlogin.setFont(new java.awt.Font("Sitka Display", 1, 14));
        btnlogin.setForeground(new java.awt.Color(255, 255, 255));
        btnlogin.setText("Login now");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        getContentPane().add(btnlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 122, 30));

        txtpassword.setFont(new java.awt.Font("Tw Cen MT", 1, 24));
        getContentPane().add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 264, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Login now");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 130, 30));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, -1, 30));

        jLabel1.setFont(new java.awt.Font("Sitka Display", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("User ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 80, 30));

        // Display names and UIDs at the bottom right (no scroll box)
        getContentPane().add(userListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 650, 300, 100));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login page.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        int yes = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the application?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (yes == JOptionPane.YES_OPTION) {
            dispose(); // Use dispose instead of System.exit(0)
        }
    }

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {
        // Empty
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
        // Empty
    }

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {
        PreparedStatement pst;
        ResultSet rs;
        Connection c = Connect.ConnectToDB();  // Ensure this method is correct and returns a valid connection
        try {
            pst = c.prepareStatement("SELECT * FROM library.login WHERE userid=? AND password=?");
            pst.setString(1, txtemail.getText());
            pst.setString(2, new String(txtpassword.getPassword())); // Convert password field to string
            rs = pst.executeQuery();
            if (rs.next()) {
                new home().setVisible(true);
                this.dispose(); // Close the sign-in window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid ID or Password. Please try again.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error occurred while connecting to database.");
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnlogin;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration
}