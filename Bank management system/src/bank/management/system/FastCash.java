package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {

    JButton cash100, cash500, cash1000, cash2000, cash5000, cash10000, back;
    JTextField accountNumberTextField;
    String formno;

    FastCash(String formno) {
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
        accountNumberLabel.setForeground(Color.WHITE);
        accountNumberLabel.setBounds(150, 250, 150, 30);
        image.add(accountNumberLabel);

        accountNumberTextField = new JTextField();
        accountNumberTextField.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberTextField.setBounds(270, 250, 180, 30);
        image.add(accountNumberTextField);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(150, 290, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 18));
        image.add(text);

        cash100 = new JButton("Rs 100");
        cash100.setBounds(145, 352, 125, 25);
        cash100.addActionListener(this);
        cash100.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash100);

        cash500 = new JButton("Rs 500");
        cash500.setBounds(325, 352, 125, 25);
        cash500.addActionListener(this);
        cash500.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash500);

        cash1000 = new JButton("Rs 1000");
        cash1000.setBounds(145, 382, 125, 25);
        cash1000.addActionListener(this);
        cash1000.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash1000);

        cash2000 = new JButton("Rs 2000");
        cash2000.setBounds(325, 382, 125, 25);
        cash2000.addActionListener(this);
        cash2000.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash2000);

        cash5000 = new JButton("Rs 5000");
        cash5000.setBounds(145, 412, 125, 25);
        cash5000.addActionListener(this);
        cash5000.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash5000);

        cash10000 = new JButton("Rs 10000");
        cash10000.setBounds(325, 412, 125, 25);
        cash10000.addActionListener(this);
        cash10000.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(cash10000);

        back = new JButton("Back");
        back.setBounds(325, 443, 125, 25);
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(600, 600);
        setLocation(300, 50);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(formno).setVisible(true);
        } else {
            String accountNumber = accountNumberTextField.getText();
            if (accountNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the account number");
                return;
            }

            String amount = ((JButton) ae.getSource()).getText().substring(3); // Rs 500
            Conn c = new Conn();
            try {
                String query = "select * from login where accountnumber = '" + accountNumber + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    ResultSet rsBank = c.s.executeQuery("select * from bank where accountnumber = '" + accountNumber + "'");
                    int balance = 0;
                    while (rsBank.next()) {
                        if (rsBank.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rsBank.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rsBank.getString("amount"));
                        }
                    }

                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    Date date = new Date();
                    String query1 = "insert into bank (accountnumber, date, type, amount) values('" + accountNumber + "', '" + new java.sql.Timestamp(date.getTime()) + "', 'Withdrawl', '" + amount + "')";
                    c.s.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                    setVisible(false);
                    new Transactions(formno).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Account Number");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new FastCash("");
    }
}
