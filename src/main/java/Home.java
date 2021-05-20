/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saurabh
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class Home extends javax.swing.JFrame {
    Connection conn;
    ResultSet rs,rs1;
    PreparedStatement pst,pst1;
    static String acc;
    /**
     * Creates new form Home
     * @param accNo
     */
    public Home(String accNo) {
        super("Home");
        initComponents();
        conn = JavaConnect.Connectdb();
        acc = accNo;
        initiate();
    }
    
    private void initiate() {
        setInfo();
        getDate();
        getProfileInfo();
        getDepositInfo();
        getWithdrawInfo();
        getLoanInfo();
        setUpTransfer();
        setUpDeposit();
        setUpWithdraw();
        setUpCustomerList();
        setUpTransactionTable();
        setUpDepositRequestTable();
        setUpLoanRequestTable();
        setUpLoanTable();
        informUser();
        adminControls();
        setUpAdminTable();
        addLoanInterest();
        getPayLoanInfo();
    }
    
    private void setInfo() {
        HomeTFACNo.setText(acc);
        HomeDepositTFAccNo.setText(acc);
        HomeWithdrawTFACNo.setText(acc);
        HomeDepositTFAccNo1.setText(acc);
    }
    
    private void getDate() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        HomeTFDate.setText(+ day + "/" + (month + 1) + "/" + year);
    }

    private void getProfileInfo() {
        String sql = "select * from account where Acc_No = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, HomeTFACNo.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                String add1 = rs.getString("First_Name");
                HomeProfTFFirstName.setText(add1);
                String add2 = rs.getString("Last_Name");
                HomeProfTFLastName.setText(add2);
                String add3 = rs.getString("Religion");
                HomeProfTFReligion.setText(add3);
                String add4 = rs.getString("Mobile_No");
                HomeProfTFMobileNum.setText(add4);
                String add5 = rs.getString("DOB");
                HomeProfTFDOB.setText(add5);
                String add6 = rs.getString("Aadhar_No");
                HomeProfTFAadharNo.setText(add6);
                String add7 = rs.getString("Address");
                HomeProfTFAddress.setText(add7);
                String add8 = rs.getString("Gender");
                HomeProfGenderComboBox.setSelectedItem(add8);
                String add9 = rs.getString("IFSC_Code");
                HomeTFIFSC.setText(add9);
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error Fetching Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Some Error Occured");
            }
        }
    }

    private void getWithdrawInfo() {
        String sql = "select * from balance where Account_No = '"+acc+"'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                String a1 = rs.getString("First_Name");
                HomeWithdrawTFFirstN.setText(a1);
                String a2 = rs.getString("Last_Name");
                HomeWithdrawTFLastN.setText(a2);
                String a3 = rs.getString("Balance");
                HomeWithdrawTFBal.setText(a3);
                String a4 = rs.getString("IFSC_Code");
                HomeWithdrawTFIFSCCode.setText(a4);
                HomeWithdrawTFAmount.setText("");
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error Fetching Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void getDepositInfo() {
        String sql = "select * from balance where Account_No = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, HomeDepositTFAccNo.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                String add1 = rs.getString("First_Name");
                HomeDepositTFFName.setText(add1);
                String add2 = rs.getString("Last_Name");
                HomeDepositTFLName.setText(add2);
                String add3 = rs.getString("Balance");
                HomeDepositTFBal.setText(add3);
                String add4 = rs.getString("IFSC_Code");
                HomeDepositTFIFSCCode.setText(add4);
                HomeDepositTFAmount.setText("");
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error Fetching Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void getLoanInfo() {
        String sql = "select * from account where Acc_No = '"+acc+"'";
        try {
            pst = conn.prepareStatement(sql);;
            rs = pst.executeQuery();
            if(rs.next()) {
                String add1 = rs.getString("First_Name");
                HomeDepositTFFName1.setText(add1);
                String add2 = rs.getString("Last_Name");
                HomeDepositTFLName1.setText(add2);
                String add3 = rs.getString("IFSC_Code");
                HomeDepositTFIFSCCode1.setText(add3);
                HomeDepositTFAmount1.setText("");
                if(rs.getInt("Applied_For_Loan") == 0 && rs.getInt("Is_Loan_Pending") == 0) {
                    HomeDepositButton1.setEnabled(true);
                } else {
                    HomeDepositButton1.setEnabled(false);
                }
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error Fetching Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
     private void getPayLoanInfo() {
        String sql = "select * from account where Acc_No = '"+acc+"'";
        try {
            pst = conn.prepareStatement(sql);;
            rs = pst.executeQuery();
            if(rs.next()) {
                int p = rs.getInt("Is_Loan_Pending");
                if(p == 1) {
                    int am = rs.getInt("Loan_Amount");
                    jTextField7.setText(String.valueOf(am));
                    int d = rs.getInt("Loan_Duration");
                    jTextField6.setText(String.valueOf(d));
                    Timestamp ts = rs.getTimestamp("Loan_Approve_Date");
                    java.util.Date date = new java.util.Date();
                    date.setTime(ts.getTime());
                    String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
                    jTextField8.setText(formattedDate);
                    double pam = am + (am * d * 0.13);
                    jTextField3.setText(String.valueOf(pam));
                }
                rs.close();
                pst.close();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error Fetching Credentials");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void isChangedT() {
        if (HomeTransTFTransAmount.getText().equals("")) {
            HomeTransferButton.setEnabled(false);
        }
        else {
            HomeTransferButton.setEnabled(true);
        }
    }
    
    private void setUpTransfer() {
        HomeTransferButton.setVisible(false);
        HomeTransferButton.setEnabled(false);
        HomeTransTFTransAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isChangedT();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isChangedT();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isChangedT();
            }
        });
    }
    
    private void isChangedD() {
        if (HomeDepositTFAmount.getText().equals("")) {
            HomeDepositButton.setEnabled(false);
        }
        else {
            HomeDepositButton.setEnabled(true);
        }
    }
    
    private void setUpDeposit() {
        HomeDepositButton.setEnabled(false);
        HomeDepositTFAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isChangedD();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isChangedD();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isChangedD();
            }
        });
    }
    
    private void isChangedW() {
        if (HomeWithdrawTFAmount.getText().equals("")) {
            HomeWithdrawButton.setEnabled(false);
        }
        else {
            HomeWithdrawButton.setEnabled(true);
        }
    }
    
    private void setUpWithdraw() {
        HomeWithdrawButton.setEnabled(false);
        HomeWithdrawTFAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isChangedW();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                isChangedW();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                isChangedW();
            }
        });
    }
    
    private void setUpCustomerList() {
        DefaultTableModel model = (DefaultTableModel) (HomeCustomerListTable.getModel());
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Gender, IFSC_Code from account";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                String g = rs.getString(4);
                String i = rs.getString(5);
                model.addRow(new Object[]{a,f,l,g,i});
                HomeCustomerListTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private String setUpTransactionS() {
        Random r = new Random();
        String id = "TRN" + r.nextInt(1000000+1);
        try {
            String q1 = "select First_Name, Last_Name from balance where Account_No = '"+acc+"'";
            pst = conn.prepareStatement(q1);
            rs = pst.executeQuery();
            if(rs.next()) {
                String f = rs.getString("First_Name");
                String l = rs.getString("Last_Name");
                String q2 = "insert into transaction(S_Acc, S_FName, S_LName, Id) values('"+acc+"', '"+f+"', '"+l+"', '"+id+"')";
                try {
                    pst = conn.prepareStatement(q2);
                    pst.execute();
                    return id;
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
    
    private void setUpTransactionR(String credAcc, int tAmount, String id) {
        try {
            String q1 = "select First_Name, Last_Name from balance where Account_No = '"+credAcc+"'";
            pst = conn.prepareStatement(q1);
            rs = pst.executeQuery();
            if(rs.next()) {
                String f = rs.getString("First_Name");
                String l = rs.getString("Last_Name");
                String d = getDateTime();
                String q2 = "update transaction set R_Acc = '"+credAcc+"', R_FName = '"+f+"', R_LName = '"+l+"', Amount = '"+tAmount+"', Date = '"+d+"' where Id = '"+id+"'";
                try {
                    pst = conn.prepareStatement(q2);
                    pst.execute();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpTransactionTable() {
        setUpTransactionTableSent();
        setUpTransactionTableReceived();
    }
    
    private void setUpTransactionTableSent() {
        try {
            String sql = "select R_Acc, R_FName, R_LName, Date, Amount from transaction where S_Acc = '"+acc+"'";
            DefaultTableModel model = (DefaultTableModel) (HomeTransactionsTable.getModel());
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                String d = rs.getString(4);
                int m = -1 * rs.getInt(5);
                model.addRow(new Object[]{a,f,l,d,m});
                HomeTransactionsTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpTransactionTableReceived() {
        try {
            String sql = "select S_Acc, S_FName, S_LName, Date, Amount from transaction where R_Acc = '"+acc+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                String d = rs.getString(4);
                int m = rs.getInt(5);
                DefaultTableModel model = (DefaultTableModel) (HomeTransactionsTable.getModel());
                model.addRow(new Object[]{a,f,l,d,m});
                HomeTransactionsTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpDepositRequestTable() {
        DefaultTableModel model = (DefaultTableModel) (HomeRequestListTable.getModel());
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Deposit_Amount from account where Check_Deposit = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                int d = rs.getInt(4);
                model.addRow(new Object[]{a,f,l,d});
                HomeRequestListTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpLoanRequestTable() {
        DefaultTableModel model = (DefaultTableModel) (HomeLoanRequestTable.getModel());
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Loan_Amount, Loan_Duration from account where Applied_For_Loan = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                int am = rs.getInt(4);
                int yr = rs.getInt(5);
                model.addRow(new Object[]{a,f,l,am,yr});
                HomeLoanRequestTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpLoanTable() {
        DefaultTableModel model = (DefaultTableModel) (HomeLoanTable.getModel());
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Loan_Approve_Date, Loan_Amount, Loan_Duration from account where Is_Loan_Pending = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                String d = rs.getString(4);
                int am = rs.getInt(5);
                int yr = rs.getInt(6);
                model.addRow(new Object[]{a,f,l,d,am,yr});
                HomeLoanTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setUpAdminTable() {
        DefaultTableModel model = (DefaultTableModel) (HomeAdminTable.getModel());
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Gender from account where Is_Admin = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                String g = rs.getString(4);
                model.addRow(new Object[]{a,f,l,g});
                HomeAdminTable.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void informUser() {
        try {
            String sql = "select Info_Deposit, Info_Loan from account where Acc_No = '"+acc+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                int d = rs.getInt("Info_Deposit");
                int l = rs.getInt("Info_Loan");
                if(d == 1) {
                    JOptionPane.showMessageDialog(null, "Your deposit has been processed successfully");
                    String q = "update account set Info_Deposit = '"+0+"'";
                    pst = conn.prepareStatement(q);
                    pst.execute();
                }
                else if(d == 2) {
                    JOptionPane.showMessageDialog(null, "Your deposit request was declined by Bank");
                    String q = "update account set Info_Deposit = '"+0+"'";
                    pst = conn.prepareStatement(q);
                    pst.execute();
                }
                if(l == 1) {
                    JOptionPane.showMessageDialog(null, "Your loan request has been approved!");
                    String q = "update account set Info_Loan = '"+0+"'";
                    pst = conn.prepareStatement(q);
                    pst.execute();
                }
                else if(l == 2) {
                    JOptionPane.showMessageDialog(null, "Your loan was denied by Bank");
                    String q = "update account set Info_Loan = '"+0+"'";
                    pst = conn.prepareStatement(q);
                    pst.execute();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private String getDateTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = new java.util.Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    
    private void adminControls() {
        try {
            String sql = "select Is_Admin from account where Acc_No = '"+acc+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                int a = rs.getInt("Is_Admin");
                if(a == 0) {
                    jTabbedPane1.remove(jPanel5);
                    jTabbedPane1.remove(jPanel9);
                    jTabbedPane1.remove(jPanel11);
                    jTabbedPane1.remove(jPanel12);
                    jTabbedPane1.remove(jPanel13);
                }
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void addLoanInterest() {
        //Todo
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        HomeTFIFSC = new javax.swing.JTextField();
        HomeTFACNo = new javax.swing.JTextField();
        HomeTFDate = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        HomeDepositTFIFSCCode = new javax.swing.JTextField();
        HomeDepositTFLName = new javax.swing.JTextField();
        HomeDepositTFFName = new javax.swing.JTextField();
        HomeDepositTFBal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        HomeDepositTFAmount = new javax.swing.JTextField();
        HomeDepositButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        HomeDepositTFAccNo = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        HomeWithdrawButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        HomeWithdrawTFACNo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        HomeWithdrawTFIFSCCode = new javax.swing.JTextField();
        HomeWithdrawTFLastN = new javax.swing.JTextField();
        HomeWithdrawTFFirstN = new javax.swing.JTextField();
        HomeWithdrawTFBal = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        HomeWithdrawTFAmount = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        HomeTransTFCredAccNo = new javax.swing.JTextField();
        HomeTransferTFLastName = new javax.swing.JTextField();
        HomeTransTFFirstName = new javax.swing.JTextField();
        HomeTransTFTransAmount = new javax.swing.JTextField();
        HomeTransTFPin = new javax.swing.JTextField();
        HomeTransferVerifyButton = new javax.swing.JButton();
        HomeTransferButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HomeTransactionsTable = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        HomeDepositButton1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        HomeDepositTFAccNo1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        HomeDepositTFIFSCCode1 = new javax.swing.JTextField();
        HomeDepositTFLName1 = new javax.swing.JTextField();
        HomeDepositTFFName1 = new javax.swing.JTextField();
        HomeDepositTFBal1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        HomeDepositTFAmount1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        HomeProfTFFirstName = new javax.swing.JTextField();
        HomeProfTFLastName = new javax.swing.JTextField();
        HomeProfTFReligion = new javax.swing.JTextField();
        HomeProfTFMobileNum = new javax.swing.JTextField();
        HomeProfTFDOB = new javax.swing.JTextField();
        HomeProfTFAadharNo = new javax.swing.JTextField();
        HomeProfTFAddress = new javax.swing.JTextField();
        HomeProfEditButton = new javax.swing.JButton();
        HomeProfSaveButton = new javax.swing.JButton();
        HomeProfGenderComboBox = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HomeCustomerListTable = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HomeRequestListTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        HomeLoanRequestTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        HomeLoanTable = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        HomeAdminTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(230, 240, 245));

        jPanel8.setBackground(new java.awt.Color(245, 250, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255)));
        jPanel8.setPreferredSize(new java.awt.Dimension(766, 750));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Downloads\\logo1.png")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Date");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("A/C Number");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("IFSC Code");

        HomeTFIFSC.setEditable(false);
        HomeTFIFSC.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTFACNo.setEditable(false);
        HomeTFACNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTFDate.setEditable(false);
        HomeTFDate.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(250, 250, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTabbedPane1.setName(""); // NOI18N

        jPanel1.setBackground(new java.awt.Color(250, 250, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("IFSC Code");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("First Name");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Available Balance");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Last Name");

        HomeDepositTFIFSCCode.setEditable(false);
        HomeDepositTFIFSCCode.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFLName.setEditable(false);
        HomeDepositTFLName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFFName.setEditable(false);
        HomeDepositTFFName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFBal.setEditable(false);
        HomeDepositTFBal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel17.setText("Deposit Amount");

        HomeDepositTFAmount.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeDepositButton.setText("Deposit");
        HomeDepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeDepositButtonActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Account Number");

        HomeDepositTFAccNo.setEditable(false);
        HomeDepositTFAccNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\Deposit.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HomeDepositTFIFSCCode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFLName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFFName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFBal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(HomeDepositButton)))
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(110, 110, 110))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(HomeDepositTFAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(HomeDepositTFIFSCCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(HomeDepositTFFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(HomeDepositTFLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(HomeDepositTFBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(HomeDepositTFAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(HomeDepositButton)
                .addContainerGap(1858, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Deposit", jPanel1);

        jPanel2.setBackground(new java.awt.Color(250, 250, 255));

        HomeWithdrawButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeWithdrawButton.setText("Withdraw");
        HomeWithdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeWithdrawButtonActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel24.setText("IFSC Code");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel25.setText("Account Number");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel26.setText("First Name");

        HomeWithdrawTFACNo.setEditable(false);
        HomeWithdrawTFACNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel27.setText("Available Balance");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel28.setText("Last Name");

        HomeWithdrawTFIFSCCode.setEditable(false);
        HomeWithdrawTFIFSCCode.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeWithdrawTFLastN.setEditable(false);
        HomeWithdrawTFLastN.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeWithdrawTFFirstN.setEditable(false);
        HomeWithdrawTFFirstN.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeWithdrawTFBal.setEditable(false);
        HomeWithdrawTFBal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel29.setText("Withdraw Amount");

        HomeWithdrawTFAmount.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Downloads\\Withdraw.png")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HomeWithdrawTFIFSCCode, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeWithdrawTFLastN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeWithdrawTFFirstN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeWithdrawTFBal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeWithdrawTFAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeWithdrawTFACNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(HomeWithdrawButton)))
                .addGap(138, 138, 138))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(HomeWithdrawTFACNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(HomeWithdrawTFIFSCCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(HomeWithdrawTFFirstN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(HomeWithdrawTFLastN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(HomeWithdrawTFBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(HomeWithdrawTFAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(HomeWithdrawButton)
                .addContainerGap(1852, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Withdraw", jPanel2);

        jPanel3.setBackground(new java.awt.Color(250, 250, 255));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("Credit A/C Number");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("First Name");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Last Name");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel22.setText("Transfer Amount");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setText("Enter Pin");

        HomeTransTFCredAccNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTransferTFLastName.setEditable(false);
        HomeTransferTFLastName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTransTFFirstName.setEditable(false);
        HomeTransTFFirstName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTransTFTransAmount.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTransTFPin.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeTransferVerifyButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeTransferVerifyButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\search.gif")); // NOI18N
        HomeTransferVerifyButton.setText("Verify");
        HomeTransferVerifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeTransferVerifyButtonActionPerformed(evt);
            }
        });

        HomeTransferButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeTransferButton.setText("Transfer");
        HomeTransferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeTransferButtonActionPerformed(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Downloads\\Transfer.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HomeTransferTFLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeTransTFTransAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeTransTFPin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeTransTFFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(HomeTransTFCredAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(HomeTransferVerifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(HomeTransferButton)))
                .addGap(40, 40, 40))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(HomeTransTFCredAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeTransferVerifyButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(HomeTransTFFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(HomeTransferTFLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22))
                    .addComponent(HomeTransTFTransAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(HomeTransTFPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(HomeTransferButton)
                .addContainerGap(1861, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transfer", jPanel3);

        jPanel4.setBackground(new java.awt.Color(250, 250, 255));

        HomeTransactionsTable.setBackground(new java.awt.Color(240, 245, 255));
        HomeTransactionsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeTransactionsTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeTransactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "First Name", "Last Name", "A/C Number", "Amount"
            }
        ));
        HomeTransactionsTable.setGridColor(new java.awt.Color(0, 153, 204));
        jScrollPane2.setViewportView(HomeTransactionsTable);

        jLabel41.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\Transactions.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1833, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transactions", jPanel4);

        jPanel10.setBackground(new java.awt.Color(250, 250, 255));

        HomeDepositButton1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeDepositButton1.setText("Apply");
        HomeDepositButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeDepositButton1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setText("IFSC Code");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setText("Account Number");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel35.setText("First Name");

        HomeDepositTFAccNo1.setEditable(false);
        HomeDepositTFAccNo1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel36.setText("Amount");

        jLabel37.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel37.setText("Last Name");

        HomeDepositTFIFSCCode1.setEditable(false);
        HomeDepositTFIFSCCode1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFLName1.setEditable(false);
        HomeDepositTFLName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFFName1.setEditable(false);
        HomeDepositTFFName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeDepositTFBal1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel38.setText("Duration (in years)");

        HomeDepositTFAmount1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel48.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\Loan.png")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HomeDepositTFIFSCCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFLName1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFFName1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFBal1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(HomeDepositTFAccNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(HomeDepositButton1)))
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(148, 148, 148))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(HomeDepositTFAccNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(HomeDepositTFIFSCCode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(HomeDepositTFFName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(HomeDepositTFLName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(HomeDepositTFBal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(HomeDepositTFAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(HomeDepositButton1)
                .addContainerGap(1844, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loan", jPanel10);

        jPanel6.setBackground(new java.awt.Color(250, 250, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Last Name");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Gender");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Religion");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Mobile Number");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Date of Birth");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Aadhar Number");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Address");

        HomeProfTFFirstName.setEditable(false);
        HomeProfTFFirstName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFLastName.setEditable(false);
        HomeProfTFLastName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFReligion.setEditable(false);
        HomeProfTFReligion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFMobileNum.setEditable(false);
        HomeProfTFMobileNum.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFDOB.setEditable(false);
        HomeProfTFDOB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFAadharNo.setEditable(false);
        HomeProfTFAadharNo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfTFAddress.setEditable(false);
        HomeProfTFAddress.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        HomeProfEditButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeProfEditButton.setText("Edit");
        HomeProfEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeProfEditButtonActionPerformed(evt);
            }
        });

        HomeProfSaveButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeProfSaveButton.setText("Save");
        HomeProfSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeProfSaveButtonActionPerformed(evt);
            }
        });

        HomeProfGenderComboBox.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HomeProfGenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        HomeProfGenderComboBox.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HomeProfTFFirstName)
                    .addComponent(HomeProfTFLastName)
                    .addComponent(HomeProfTFReligion)
                    .addComponent(HomeProfTFMobileNum)
                    .addComponent(HomeProfTFDOB)
                    .addComponent(HomeProfTFAadharNo)
                    .addComponent(HomeProfTFAddress)
                    .addComponent(HomeProfGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(HomeProfEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HomeProfSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(HomeProfTFFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(HomeProfTFLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(HomeProfGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(HomeProfTFReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(HomeProfTFMobileNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(HomeProfTFDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(HomeProfTFAadharNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(HomeProfTFAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HomeProfEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HomeProfSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(524, 524, 524))
        );

        jTabbedPane1.addTab("Profile", jPanel6);

        jPanel7.setBackground(new java.awt.Color(250, 250, 255));

        jLabel49.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\ECSMS.png")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel49)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2026, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("About", jPanel7);

        jPanel5.setBackground(new java.awt.Color(250, 250, 255));

        HomeCustomerListTable.setBackground(new java.awt.Color(245, 250, 255));
        HomeCustomerListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeCustomerListTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeCustomerListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A/C Number", "First Name", "Last Name", "Gender", "IFSC Code"
            }
        ));
        jScrollPane1.setViewportView(HomeCustomerListTable);
        if (HomeCustomerListTable.getColumnModel().getColumnCount() > 0) {
            HomeCustomerListTable.getColumnModel().getColumn(4).setHeaderValue("Status");
        }

        jLabel50.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\CustomerList.png")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel50)
                .addGap(78, 78, 78))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1832, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer List", jPanel5);

        jPanel9.setBackground(new java.awt.Color(250, 250, 255));

        HomeRequestListTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeRequestListTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeRequestListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A/C Number", "First Name", "Last Name", "Amount"
            }
        ));
        jScrollPane3.setViewportView(HomeRequestListTable);

        jButton1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton1.setText("Take Action");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(1833, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RequestsD", jPanel9);

        jPanel11.setBackground(new java.awt.Color(250, 250, 255));

        HomeLoanRequestTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeLoanRequestTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeLoanRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A/C Number", "First Name", "Last Name", "Amount", "Duration (years)"
            }
        ));
        jScrollPane4.setViewportView(HomeLoanRequestTable);

        jButton3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton3.setText("Take Action");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(1831, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RequestsL", jPanel11);

        jPanel12.setBackground(new java.awt.Color(250, 250, 255));

        HomeLoanTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeLoanTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeLoanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A/C Number", "First Name", "Last Name", "Date", "Amount", "Duration (years)"
            }
        ));
        jScrollPane5.setViewportView(HomeLoanTable);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel51)
                .addGap(93, 93, 93)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1827, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loan List", jPanel12);

        jPanel13.setBackground(new java.awt.Color(250, 250, 255));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel39.setText("Account Number");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel40.setText("Name");

        jTextField1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jButton4.setText("Search");

        HomeAdminTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        HomeAdminTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        HomeAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A/C Number", "First Name", "Last Name", "Gender"
            }
        ));
        jScrollPane6.setViewportView(HomeAdminTable);

        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setText("Make Admin");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton5))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(109, 109, 109))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1826, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admins", jPanel13);

        jPanel14.setBackground(new java.awt.Color(250, 250, 255));

        jLabel42.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel42.setText("Total Amount to be Paid");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jButton6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton6.setText("Pay Amount");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel44.setText("Pin");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel45.setText("Loan Approval Date");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel46.setText("Duration");

        jLabel47.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel47.setText("Loan Amount");

        jLabel52.setIcon(new javax.swing.ImageIcon("C:\\Users\\Win 10 Pc\\Documents\\NetBeansProjects\\OOPCP\\Images\\PayLoan.png")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel47))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jButton6))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel52)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addGap(49, 49, 49)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(1873, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pay Loan", jPanel14);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(131, 131, 131)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(HomeTFDate))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HomeTFACNo)
                                    .addComponent(HomeTFIFSC))))
                        .addGap(57, 57, 57))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(HomeTFDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(HomeTFACNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(HomeTFIFSC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(804, 767));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeProfSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeProfSaveButtonActionPerformed

        try {
            String val1 = HomeProfTFFirstName.getText();
            String val2 = HomeProfTFLastName.getText();
            String val3 = HomeProfTFReligion.getText();
            String val4 = HomeProfTFMobileNum.getText();
            String val5 = HomeProfTFDOB.getText();
            String val6 = HomeProfTFAadharNo.getText();
            String val7 = HomeProfTFAddress.getText();
            String val8 = (String)HomeProfGenderComboBox.getSelectedItem();
            String val9 = HomeTFACNo.getText();
            String sql = "update account set First_Name = '"+val1+"',Last_Name = '"+val2+"',Religion = '"+val3+"',Mobile_No = '"+val4+"',DOB = '"+val5+"',Aadhar_No = '"+val6+"',Address = '"+val7+"',Gender = '"+val8+"' where Acc_No = '"+val9+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Profile Updated");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_HomeProfSaveButtonActionPerformed

    private void HomeProfEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeProfEditButtonActionPerformed
        // Edit Button
        HomeProfTFFirstName.setEditable(true);
        HomeProfTFLastName.setEditable(true);
        HomeProfGenderComboBox.setEnabled(true);
        HomeProfTFReligion.setEditable(true);
        HomeProfTFMobileNum.setEditable(true);
        HomeProfTFDOB.setEditable(true);
        HomeProfTFAadharNo.setEditable(true);
        HomeProfTFAddress.setEditable(true);
    }//GEN-LAST:event_HomeProfEditButtonActionPerformed

    private void HomeTransferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeTransferButtonActionPerformed
        // Transfer Button

        String transferAmount = HomeTransTFTransAmount.getText();
        int transferAmountInt = Integer.parseInt(transferAmount);

        if(transferAmountInt <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount to transfer");
        }
        else {
            String creditAcc = HomeTransTFCredAccNo.getText();
            String pinQuery = "select Pin from account where Acc_No = '"+acc+"'";
            try {
                pst = conn.prepareStatement(pinQuery);
                rs = pst.executeQuery();
                if(rs.next()) {
                    int p = rs.getInt("Pin");
                    System.out.print(p);
                    int enteredPin = Integer.parseInt(HomeTransTFPin.getText());
                    if(enteredPin == p) {
                        String q1 = "select Balance from balance where Account_No = '"+acc+"'";
                        try {
                            pst = conn.prepareStatement(q1);
                            rs = pst.executeQuery();
                            if(rs.next()) {
                                int balance = rs.getInt("Balance");
                                if(transferAmountInt > balance) {
                                    JOptionPane.showMessageDialog(null, "Not enough balance");
                                }
                                else {
                                    int newB = balance - transferAmountInt;
                                    String newBal = String.valueOf(newB);
                                    String q2 = "update balance set Balance = '"+newBal+"' where Account_No = '"+acc+"'";
                                    try {
                                        pst = conn.prepareStatement(q2);
                                        pst.execute();

                                    } catch(Exception e) {
                                        JOptionPane.showMessageDialog(null, "Failed to update balance");
                                    }

                                    String q3 = "select Balance from balance where Account_No = '"+creditAcc+"'";
                                    try {
                                        pst = conn.prepareStatement(q3);
                                        rs = pst.executeQuery();
                                        if(rs.next()) {
                                            int b = rs.getInt("Balance");
                                            int newCreditBal = b + transferAmountInt;
                                            String newCredBal = String.valueOf(newCreditBal);
                                            String q4 = "update balance set Balance = '"+newCredBal+"' where Account_No = '"+creditAcc+"'";
                                            try {
                                                pst = conn.prepareStatement(q4);
                                                pst.execute();
                                                JOptionPane.showMessageDialog(null, "Amount Transferred");
                                                HomeTransTFCredAccNo.setEditable(true);
                                                HomeTransTFCredAccNo.setText("");
                                                HomeTransferTFLastName.setText("");
                                                HomeTransTFFirstName.setText("");
                                                HomeTransTFTransAmount.setText("");
                                                HomeTransTFPin.setText("");
                                                getDepositInfo();
                                                getWithdrawInfo();
                                                String id = setUpTransactionS();
                                                setUpTransactionR(creditAcc, transferAmountInt, id);
                                                setUpTransactionTable();
                                            } catch(Exception e) {
                                                JOptionPane.showMessageDialog(null, e);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Unexpected Error");
                                        }
                                    } catch(Exception e) {
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                }
                            }
                        } catch(Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Pin");
                    }
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_HomeTransferButtonActionPerformed

    private void HomeTransferVerifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeTransferVerifyButtonActionPerformed
        // Verify Button
        String creditAcc = HomeTransTFCredAccNo.getText();
        if(creditAcc.equals(acc)) {
            JOptionPane.showMessageDialog(null, "You cannot enter your own account number");
        }
        else {
            String sql = "select First_Name, Last_Name from balance where Account_No = '"+creditAcc+"'";
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()) {
                    String s1 = rs.getString("First_Name");
                    HomeTransTFFirstName.setText(s1);
                    String s2 = rs.getString("Last_Name");
                    HomeTransferTFLastName.setText(s2);
                    HomeTransTFCredAccNo.setEditable(false);
                    HomeTransferButton.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Account Number");
                    HomeTransTFCredAccNo.setEditable(true);
                    HomeTransferButton.setVisible(false);
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_HomeTransferVerifyButtonActionPerformed

    private void HomeWithdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeWithdrawButtonActionPerformed
        // Withdraw Button
        try {
            String s1 = HomeWithdrawTFBal.getText();
            String s2 = HomeWithdrawTFAmount.getText();
            int newBal = Integer.parseInt(s1) - Integer.parseInt(s2);
            if(newBal >= 0 && Integer.parseInt(s2) > 0) {
                String bal = String.valueOf(newBal);
                String sql = "update balance set Balance = '"+bal+"' where Account_No = '"+acc+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Withdraw successfull");
                initiate();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please enter valid amount");
                HomeWithdrawTFAmount.setText("");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_HomeWithdrawButtonActionPerformed

    private void HomeDepositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeDepositButtonActionPerformed
        try {
            int s2 = Integer.parseInt(HomeDepositTFAmount.getText());
            if(s2 > 0) {
                String sql = "update account set Check_Deposit = '"+1+"', Deposit_Amount = '"+s2+"' where Acc_No = '"+acc+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Your request will be processed shortly");
                getDepositInfo();
                getWithdrawInfo();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
                HomeDepositTFAmount.setText("");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_HomeDepositButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Action on Deposits
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Deposit_Amount from account where Check_Deposit = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                int d = rs.getInt(4);
                int res = JOptionPane.showConfirmDialog(null, "Account Number: "+a+"  Name: "+f+" "+l+"Amount: "+d+"\nDo you want to proceed with the deposit?","Verify deposit requests",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION) {
                    try {
                        String q1 = "select Balance from balance where Account_No = '"+a+"'";
                        pst = conn.prepareStatement(q1);
                        rs = pst.executeQuery();
                        if(rs.next()) {
                            int ba = rs.getInt("Balance");
                            int am = d;
                            int newBal = ba + am;
                            String bal = String.valueOf(newBal);
                            String q2 = "update balance set Balance = '"+bal+"' where Account_No = '"+a+"'";
                            pst = conn.prepareStatement(q2);
                            pst.execute();
                            String q3 = "update account set Check_Deposit = '"+0+"', Deposit_Amount = '"+0+"', Info_Deposit = '"+1+"' where Acc_No = '"+a+"'";
                            pst = conn.prepareStatement(q3);
                            pst.execute();
                            initiate();
                        } else {
                            JOptionPane.showMessageDialog(null, "Unexpected Error");
                        }
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else if (res == JOptionPane.NO_OPTION){
                    String q3 = "update account set Check_Deposit = '"+0+"', Deposit_Amount = '"+0+"', Info_Deposit = '"+2+"' where Acc_No = '"+a+"'";
                    pst1 = conn.prepareStatement(q3);
                    pst1.execute();
                    initiate();
                } else if (res == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.getRootFrame().dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        initiate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void HomeDepositButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeDepositButton1ActionPerformed
        // Apply Loan
        try {
            int am = Integer.parseInt(HomeDepositTFBal1.getText());
            int yr = Integer.parseInt(HomeDepositTFAmount1.getText());
            if(am > 10000 && yr > 0) {
                String sql = "update account set Applied_For_Loan = '"+1+"', Loan_Amount = '"+am+"', Loan_Duration = '"+yr+"' where Acc_No = '"+acc+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Bank will verify your loan application and get back to you shortly");
                getLoanInfo();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
                HomeDepositTFAmount.setText("");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_HomeDepositButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Action on Loan
        try {
            String sql = "select Acc_No, First_Name, Last_Name, Loan_Amount, Loan_Duration from account where Applied_For_Loan = '"+1+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int a = rs.getInt(1);
                String f = rs.getString(2);
                String l = rs.getString(3);
                int am = rs.getInt(4);
                int yr = rs.getInt(5);
                int res = JOptionPane.showConfirmDialog(null, "Account Number: "+a+"  Name: "+f+" "+l+"\nLoan Amount: "+am+"    Loan Duration: "+yr+"years"+"\nDo you want to approve the loan request?","Verify loan requests",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(res == JOptionPane.YES_OPTION) {
                    try {
                        String q1 = "select Balance from balance where Account_No = '"+a+"'";
                        pst1 = conn.prepareStatement(q1);
                        rs1 = pst1.executeQuery();
                        if(rs1.next()) {
                            int ba = rs1.getInt("Balance");
                            int lam = am;
                            int newBal = ba + lam;
                            String bal = String.valueOf(newBal);
                            String q2 = "update balance set Balance = '"+bal+"' where Account_No = '"+a+"'";
                            pst1 = conn.prepareStatement(q2);
                            pst1.execute();
                            String dateTime = getDateTime();
                            String q3 = "update account set Applied_For_Loan = '"+0+"', Is_Loan_Pending = '"+1+"', Info_Loan = '"+1+"', Loan_Approve_Date = '"+dateTime+"' where Acc_No = '"+a+"'";
                            pst1 = conn.prepareStatement(q3);
                            pst1.execute();
                            String q4 = "select Funds from bank";
                            pst1 = conn.prepareStatement(q4);
                            rs1 = pst1.executeQuery();
                            if(rs1.next()) {
                                int funds = rs1.getInt("Funds");
                                int newFunds = funds - lam;
                                String q5 = "update bank set Funds = '"+newFunds+"'";
                                pst1 = conn.prepareStatement(q5);
                                pst1.execute();
                            }
                            initiate();
                        } else {
                            JOptionPane.showMessageDialog(null, "Unexpected Error");
                        }
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else if (res == JOptionPane.NO_OPTION){
                    String q3 = "update account set Applied_For_Loan = '"+0+"', Info_Loan = '"+2+"', Loan_Amount = '"+0+"' where Acc_No = '"+a+"'";
                    pst1 = conn.prepareStatement(q3);
                    pst1.execute();
                    initiate();
                } else if (res == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.getRootFrame().dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Pay Loan button
        if(jTextField5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the pin");
        }
        else {
            String sql = "select Pin from account where Acc_No = '"+acc+"'";
            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()) {
                    int p = rs.getInt("Pin");
                    if(p == Integer.parseInt(jTextField5.getText())) {
                        String q = "select Balance from balance where Account_No = '"+acc+"'";
                        try {
                            pst = conn.prepareStatement(q);
                            rs = pst.executeQuery();
                            if(rs.next()) {
                                int bal = rs.getInt("Balance");
                                double pamD = Double.parseDouble(jTextField3.getText());
                                int pam = (int)pamD;
                                if(bal > pam) {
                                    int newbal = bal - pam;
                                    String q1 = "update balance set Balance = '"+newbal+"' where Account_No = '"+acc+"'";
                                    pst = conn.prepareStatement(q1);
                                    pst.execute();
                                    String q4 = "select Funds from bank";
                                    pst = conn.prepareStatement(q4);
                                    rs = pst.executeQuery();
                                    if(rs.next()) {
                                        int newFunds = rs.getInt("Funds") + pam;
                                        String q2 = "update bank set Funds = '"+newFunds+"'";
                                        pst = conn.prepareStatement(q2);
                                        pst.execute();
                                        String q3 = "update account set Is_Loan_Pending = '"+0+"' where Acc_No = '"+acc+"'";
                                        pst = conn.prepareStatement(q3);
                                        pst.execute();
                                        JOptionPane.showMessageDialog(null, "Loan amount paid successfully");
                                        initiate();
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Not enough balance");
                            }
                        } catch(Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect pin");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(acc).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HomeAdminTable;
    private javax.swing.JTable HomeCustomerListTable;
    private javax.swing.JButton HomeDepositButton;
    private javax.swing.JButton HomeDepositButton1;
    private javax.swing.JTextField HomeDepositTFAccNo;
    private javax.swing.JTextField HomeDepositTFAccNo1;
    private javax.swing.JTextField HomeDepositTFAmount;
    private javax.swing.JTextField HomeDepositTFAmount1;
    private javax.swing.JTextField HomeDepositTFBal;
    private javax.swing.JTextField HomeDepositTFBal1;
    private javax.swing.JTextField HomeDepositTFFName;
    private javax.swing.JTextField HomeDepositTFFName1;
    private javax.swing.JTextField HomeDepositTFIFSCCode;
    private javax.swing.JTextField HomeDepositTFIFSCCode1;
    private javax.swing.JTextField HomeDepositTFLName;
    private javax.swing.JTextField HomeDepositTFLName1;
    private javax.swing.JTable HomeLoanRequestTable;
    private javax.swing.JTable HomeLoanTable;
    private javax.swing.JButton HomeProfEditButton;
    private javax.swing.JComboBox<String> HomeProfGenderComboBox;
    private javax.swing.JButton HomeProfSaveButton;
    private javax.swing.JTextField HomeProfTFAadharNo;
    private javax.swing.JTextField HomeProfTFAddress;
    private javax.swing.JTextField HomeProfTFDOB;
    private javax.swing.JTextField HomeProfTFFirstName;
    private javax.swing.JTextField HomeProfTFLastName;
    private javax.swing.JTextField HomeProfTFMobileNum;
    private javax.swing.JTextField HomeProfTFReligion;
    private javax.swing.JTable HomeRequestListTable;
    private javax.swing.JTextField HomeTFACNo;
    private javax.swing.JTextField HomeTFDate;
    private javax.swing.JTextField HomeTFIFSC;
    private javax.swing.JTextField HomeTransTFCredAccNo;
    private javax.swing.JTextField HomeTransTFFirstName;
    private javax.swing.JTextField HomeTransTFPin;
    private javax.swing.JTextField HomeTransTFTransAmount;
    private javax.swing.JTable HomeTransactionsTable;
    private javax.swing.JButton HomeTransferButton;
    private javax.swing.JTextField HomeTransferTFLastName;
    private javax.swing.JButton HomeTransferVerifyButton;
    private javax.swing.JButton HomeWithdrawButton;
    private javax.swing.JTextField HomeWithdrawTFACNo;
    private javax.swing.JTextField HomeWithdrawTFAmount;
    private javax.swing.JTextField HomeWithdrawTFBal;
    private javax.swing.JTextField HomeWithdrawTFFirstN;
    private javax.swing.JTextField HomeWithdrawTFIFSCCode;
    private javax.swing.JTextField HomeWithdrawTFLastN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
