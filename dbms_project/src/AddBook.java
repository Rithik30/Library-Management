import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AddBook extends javax.swing.JFrame {

    // Declare the PreparedStatement variable at the class level
    private PreparedStatement pst;

    public AddBook() {
        initComponents();
    }

    // Method to clear text fields after adding the book
    public void clear() {
        txtid.setText("");
        txtname.setText("");
        txtprice.setText("");
        txtpublisher.setText("");
        txtyear.setText("");
    }

    // Method to initialize the form components
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton6 = new javax.swing.JButton();
        txtyear = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtpublisher = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close icon.png")));
        jButton6.addActionListener(evt -> jButton6ActionPerformed(evt));
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 0, 50, 31));

        txtyear.setFont(new java.awt.Font("Segoe UI", 1, 14));
        getContentPane().add(txtyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 320, 36));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel2.setText("Book ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 239, 46));

        txtprice.setFont(new java.awt.Font("Segoe UI", 1, 14));
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 320, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel3.setText("Book Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 239, 46));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.setText("Save");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 670, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel5.setText("Price");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 239, 46));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel6.setText("Publisher");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 239, 46));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel7.setText("Publisher Year");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 239, 46));

        txtpublisher.setFont(new java.awt.Font("Segoe UI", 1, 14));
        getContentPane().add(txtpublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 320, 40));

        txtid.setFont(new java.awt.Font("Segoe UI", 1, 14));
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 320, 40));

        txtname.setFont(new java.awt.Font("Segoe UI", 1, 14));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 320, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/isue.jpg")));
        jLabel1.setText("Add Book Details");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 230, 53));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/All Page Backgraound.jpg")));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 770));

        pack();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose(); // Close the current window
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Check if any required field is empty
        if (txtid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book ID");
            txtid.requestFocus();
            return;
        } else if (txtname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book Name");
            txtname.requestFocus();
            return;
        } else if (txtpublisher.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Publisher");
            txtpublisher.requestFocus();
            return;
        } else if (txtprice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Book Price");
            txtprice.requestFocus();
            return;
        } else if (txtyear.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter Publisher Year");
            txtyear.requestFocus();
            return;
        }

        // Proceed to add the book if all fields are filled
        try (Connection c = Connect.ConnectToDB()) {
            // Initialize the prepared statement
            pst = c.prepareStatement("INSERT INTO library.book(name, publisher, price, year, status) VALUES(?,?,?,?,?)");
            pst.setString(1, txtname.getText());  // Name of the book
            pst.setString(2, txtpublisher.getText());  // Publisher of the book
            pst.setString(3, txtprice.getText());  // Price of the book
            pst.setString(4, txtyear.getText());  // Year of publication
            pst.setString(5, "NotIssued");  // Default status of the book
            pst.executeUpdate();  // Execute the update (insert the record)

            JOptionPane.showMessageDialog(rootPane, "Record Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
            clear(); // Clear the form after saving
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AddBook().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtpublisher;
    private javax.swing.JTextField txtyear;
    // End of variables declaration
}
