package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount, accountNumberTextField;
    JButton deposit, back;
    String formno;

    Deposit(String formno) {
        this.formno = formno;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 760);
        add(image);

        JLabel accountNumberLabel = new JLabel("Account No:");
        accountNumberLabel.setFont(new Font("Raleway", Font.BOLD, 25));
        accountNumberLabel.setForeground(Color.WHITE);
        accountNumberLabel.setBounds(155, 250, 200, 22);
        image.add(accountNumberLabel);

        accountNumberTextField = new JTextField();
        accountNumberTextField.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberTextField.setBounds(320, 250, 130, 22);
        image.add(accountNumberTextField);

        JLabel text = new JLabel("Enter the amount you want to deposit:");
        text.setBounds(155, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(155, 350, 290, 25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(325, 412, 125, 25);
        deposit.addActionListener(this);
        deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(325, 443, 125, 25);
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(580, 600);
        setLocation(300, 70);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String accountnumber = accountNumberTextField.getText();
            String number = amount.getText();
            Date date = new Date();

            if (accountnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the account number");
                return;
            }

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
                return;
            }

            // Validate the amount is a valid number
            int depositAmount;
            try {
                depositAmount = Integer.parseInt(number);
                if (depositAmount <= 0) {
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
                    // Account number is correct, proceed with deposit
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = sdf.format(date);

                        String query1 = "INSERT INTO bank VALUES ('" + accountnumber + "', '" + dateString + "', 'Deposit', '" + depositAmount + "')";
                        conn.s.executeUpdate(query1);
                        JOptionPane.showMessageDialog(null, "Rs " + depositAmount + " Deposited Successfully");
                        setVisible(false);
                        new Transactions(accountnumber).setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        new Deposit("");
    }
}
