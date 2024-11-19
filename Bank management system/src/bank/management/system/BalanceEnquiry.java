package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField accountNumberTextField;
    JButton checkAccount, back;
    JLabel balanceLabel;
    String formno;

    BalanceEnquiry(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 760);
        add(image);

        JLabel accountNumberLabel = new JLabel("Account No:");
        accountNumberLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        accountNumberLabel.setBounds(150, 250, 200, 30);
        accountNumberLabel.setForeground(Color.WHITE);
        image.add(accountNumberLabel);

        accountNumberTextField = new JTextField();
        accountNumberTextField.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberTextField.setBounds(290, 250, 150, 30);
        image.add(accountNumberTextField);

        checkAccount = new JButton("Check");
        checkAccount.setBounds(150, 443, 125, 25);
        checkAccount.addActionListener(e -> checkAccountNumber());
        checkAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(checkAccount);
 
        balanceLabel = new JLabel("");
        balanceLabel.setBounds(150, 300, 700, 35);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 15));
        image.add(balanceLabel);
        
        back = new JButton("Back");
        back.setBounds(325, 443, 125, 25);
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(570, 600);
        setLocation(300, 50);
        setUndecorated(true);
        setVisible(true);
    }

    private void checkAccountNumber() {
        String accountnumber = accountNumberTextField.getText();
        if (accountnumber.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the account number");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM login WHERE accountnumber = '" + accountnumber + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                displayBalance(accountnumber);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Account Number");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayBalance(String accountnumber) {
        Conn c = new Conn();
        int balance = 0;
        try {
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE accountnumber = '" + accountnumber + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        balanceLabel.setText("Your Current Account Balance is Rs " + balance);
    }

    public void actionPerformed(ActionEvent ae) {
         setVisible(false);
         new CashierInterface("").setVisible(true);
    }

    public static void main(String args[]) {
        new BalanceEnquiry("").setVisible(true);
    }
}
