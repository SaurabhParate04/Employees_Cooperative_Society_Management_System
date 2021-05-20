
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh
 */
public class Welcome extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public String acc;
    /**
     * Creates new form Welcome
     */
    public Welcome() {
        super("Login");
        initComponents();
        conn = JavaConnect.Connectdb();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        WelcomeTFAccNum = new javax.swing.JTextField();
        WelcomeTFPin = new javax.swing.JTextField();
        WelcomeLoginButton = new javax.swing.JButton();
        WelcomeCreateButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 250, 255));

        jPanel1.setBackground(new java.awt.Color(245, 250, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "Welcome", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 28), new java.awt.Color(51, 153, 255))); // NOI18N
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jLabel1.setText("Please enter the required information");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Account Number");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Pin");

        WelcomeTFAccNum.setFont(new java.awt.Font("Arial", 0, 19)); // NOI18N

        WelcomeTFPin.setFont(new java.awt.Font("Arial", 0, 19)); // NOI18N

        WelcomeLoginButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        WelcomeLoginButton.setText("Login");
        WelcomeLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WelcomeLoginButtonActionPerformed(evt);
            }
        });

        WelcomeCreateButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        WelcomeCreateButton.setText("Create a New Account");
        WelcomeCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WelcomeCreateButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("--- OR ---");

        jLabel4.setBackground(new java.awt.Color(235, 239, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\Logo.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(194, 194, 194))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(WelcomeTFAccNum)
                            .addComponent(WelcomeTFPin, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(WelcomeCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(WelcomeLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(WelcomeTFAccNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(WelcomeTFPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGap(52, 52, 52)
                .addComponent(WelcomeLoginButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(WelcomeCreateButton)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(778, 706));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void WelcomeCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WelcomeCreateButtonActionPerformed
        // Sign up button
        setVisible(false);
        Account ob = new Account();
        ob.setVisible(true);
    }//GEN-LAST:event_WelcomeCreateButtonActionPerformed

    private void WelcomeLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WelcomeLoginButtonActionPerformed
        // Login button:
        String sql = "select * from account where Acc_No=? and Pin=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, WelcomeTFAccNum.getText());
            pst.setString(2, WelcomeTFPin.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                acc = WelcomeTFAccNum.getText();
                setVisible(false);
                Loading obj = new Loading();
                obj.setUpLoading(acc);
                obj.setVisible(true);
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch(Exception e) {

            }
        }
    }//GEN-LAST:event_WelcomeLoginButtonActionPerformed

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
                /*if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton WelcomeCreateButton;
    private javax.swing.JButton WelcomeLoginButton;
    private javax.swing.JTextField WelcomeTFAccNum;
    private javax.swing.JTextField WelcomeTFPin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
