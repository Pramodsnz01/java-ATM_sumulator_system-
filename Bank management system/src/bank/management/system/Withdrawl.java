package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount, cardTextField;
    JButton withdraw, back;
    String formno;

    Withdrawl(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 760);
        add(image);

        JLabel cardno = new JLabel("Account No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 17));
        cardno.setBounds(150, 250, 150, 30);
        cardno.setForeground(Color.WHITE);
        image.add(cardno);

        cardTextField = new JTextField();
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        cardTextField.setBounds(270, 250, 180, 30);
        image.add(cardTextField);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(150, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(150, 340, 300, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(325, 412, 125, 25);
        withdraw.addActionListener(this);
        withdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(325, 443, 125, 25);
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(550, 550);
        setLocation(300, 30);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String accountnumber = cardTextField.getText();
            String number = amount.getText();
            Date date = new Date();

            if (accountnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the Account number");
                return;
            }

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                return;
            }

            // Validate the amount is a valid number
            int withdrawAmount;
            try {
                withdrawAmount = Integer.parseInt(number);
                if (withdrawAmount <= 0) {
                    throw new NumberFormatException("Amount must be greater than zero");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM login WHERE accountnumber = '" + accountnumber + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    // Verify the balance
                    query = "SELECT * FROM bank WHERE accountnumber = '" + accountnumber + "'";
                    rs = conn.s.executeQuery(query);
                    int balance = 0;
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }

                    if (balance < withdrawAmount) {
                        JOptionPane.showMessageDialog(null, "Not enough balance");
                        return;
                    }

                    // Perform withdrawal
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = sdf.format(date);
                    query = "INSERT INTO bank (accountnumber, date, type, amount) VALUES ('" + accountnumber + "', '" + dateString + "', 'Withdrawl', '" + withdrawAmount + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + withdrawAmount + " Withdrawn Successfully");

                    setVisible(false);
                    new Transactions(accountnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Account Number");
                }

                rs.close();
                conn.s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(formno).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Withdrawl("");
    }
}
