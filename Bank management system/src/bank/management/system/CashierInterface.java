package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CashierInterface extends JFrame implements ActionListener {

    JButton handleTransactions, logout, balanceenquiry;
    String formno;

    CashierInterface(String formno) {
        this.formno = formno;

        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cashier1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 1000, 500);
        add(label);

        JLabel text = new JLabel("CASHIER DASHBOARD");
        text.setFont(new Font("Serif", Font.BOLD, 38));
        text.setBounds(290, 100, 500, 40);
        label.add(text);

        handleTransactions = new JButton("HANDLE TRANSACTIONS");
        handleTransactions.setFont(new Font("Raleway", Font.BOLD, 17));
        handleTransactions.setBounds(70, 250, 250, 50);
        handleTransactions.setBackground(Color.BLACK);
        handleTransactions.setForeground(Color.WHITE);
        handleTransactions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        handleTransactions.addActionListener(this);
        label.add(handleTransactions);
        
        balanceenquiry = new JButton("BALANCE ENQUIRY");
        balanceenquiry.setFont(new Font("Raleway", Font.BOLD, 17));
        balanceenquiry.setBounds(370, 250, 250, 50);
        balanceenquiry.setBackground(Color.BLACK);
        balanceenquiry.setForeground(Color.WHITE);
        balanceenquiry.setCursor(new Cursor(Cursor.HAND_CURSOR));
        balanceenquiry.addActionListener(this);
        label.add(balanceenquiry);

        logout = new JButton("LOGOUT");
        logout.setFont(new Font("Raleway", Font.BOLD, 17));
        logout.setBounds(670, 250, 250, 50);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addActionListener(this);
        label.add(logout);
     

        getContentPane().setBackground(Color.WHITE);

        setSize(1000, 500);
//        setUndecorated(true);
        setVisible(true);
        setLocation(150, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == handleTransactions) {
            setVisible(false);
            new Transactions(formno).setVisible(true);
        
        } else if (ae.getSource() == balanceenquiry) {
            setVisible(false);
            new BalanceEnquiry(formno).setVisible(true);
            
        }else if (ae.getSource() == logout) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new CashierInterface("").setVisible(true);
    }
}
