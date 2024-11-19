package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdrawl, accountTransfer, fastcash, exit;
    String formno;

    Transactions(String formno) {

        this.formno = formno;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 760);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(170, 250, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 18));
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(145, 352, 125, 25);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Cash Withdrawl");
        withdrawl.setBounds(325, 352, 125, 25);
        withdrawl.addActionListener(this);
        withdrawl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(withdrawl);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(145, 382, 125, 25);
        fastcash.addActionListener(this);
        fastcash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(fastcash); 
        
       
        accountTransfer = new JButton("Account Transfer");
        accountTransfer.setBounds(325, 382, 125, 25);
        accountTransfer.addActionListener(this);
        accountTransfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(accountTransfer);
 
        exit = new JButton("Exit");
        exit.setBounds(325, 443, 125, 25);
        exit.addActionListener(this);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(exit);

        setSize(580, 600);
        setLocation(300, 50);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new CashierInterface("").setVisible(true);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(formno).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(formno).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(formno).setVisible(true);
        } else if (ae.getSource() == accountTransfer) {
            setVisible(false);
            new AccountTransfer(formno).setVisible(true);
        } 
    }

    public static void main(String args[]) {
        new Transactions("");
    }
}
