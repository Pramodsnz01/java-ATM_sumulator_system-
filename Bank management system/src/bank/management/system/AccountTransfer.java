package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AccountTransfer extends JFrame implements ActionListener {

    JTextField senderAccountField, recipientAccountField, transferAmountField;
    JButton transfer, back;
    String formno;

    AccountTransfer(String formno) {
        this.formno = formno;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/transfer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1000, 600);
        add(image);

        JLabel text = new JLabel("ACCOUNT TO ACCOUNT TRANSFER");
        text.setBounds(190, 70, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System", Font.BOLD, 35));
        image.add(text);

        JLabel senderAccountText = new JLabel("Sender Account No:");
        senderAccountText.setBounds(195, 240, 300, 25);
        senderAccountText.setForeground(Color.WHITE);
        senderAccountText.setFont(new Font("System", Font.BOLD, 19));
        image.add(senderAccountText);

        senderAccountField = new JTextField();
        senderAccountField.setFont(new Font("Raleway", Font.BOLD, 20));
        senderAccountField.setBounds(195, 290, 185, 25);
        image.add(senderAccountField);

        JLabel recipientAccountText = new JLabel("Recipient Account No:");
        recipientAccountText.setBounds(585, 240, 300, 25);
        recipientAccountText.setForeground(Color.WHITE);
        recipientAccountText.setFont(new Font("System", Font.BOLD, 19));
        image.add(recipientAccountText);

        recipientAccountField = new JTextField();
        recipientAccountField.setFont(new Font("Raleway", Font.BOLD, 20));
        recipientAccountField.setBounds(585, 290, 200, 25);
        image.add(recipientAccountField);

        JLabel transferAmountText = new JLabel("Amount:");
        transferAmountText.setBounds(195, 340, 180, 25);
        transferAmountText.setForeground(Color.WHITE);
        transferAmountText.setFont(new Font("System", Font.BOLD, 19));
        image.add(transferAmountText);

        transferAmountField = new JTextField();
        transferAmountField.setFont(new Font("Raleway", Font.BOLD, 20));
        transferAmountField.setBounds(195, 390, 185, 25);
        image.add(transferAmountField);

        transfer = new JButton("Transfer");
        transfer.setBounds(425, 350, 125, 25);
        transfer.setFont(new Font("Raleway", Font.BOLD, 20));
        transfer.setBackground(Color.BLACK);
        transfer.setForeground(Color.WHITE);
        transfer.addActionListener(this);
        transfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(transfer);

        back = new JButton("Back");
        back.setBounds(425, 390, 125, 25);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE); 
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(1000, 525);
        setUndecorated(true);
        setLocation(150, 80);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == transfer) {
            String senderAccount = senderAccountField.getText();
            String recipientAccount = recipientAccountField.getText();
            String transferAmount = transferAmountField.getText();

            if (senderAccount.equals("") || recipientAccount.equals("") || transferAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
                return;
            }

            if (senderAccount.equals(recipientAccount)) {
                JOptionPane.showMessageDialog(null, "Sender and recipient account numbers cannot be the same");
                return;
            }

            try {
                Conn conn = new Conn();
                String querySender = "SELECT * FROM login WHERE accountnumber = '" + senderAccount + "'";
                ResultSet rsSender = conn.s.executeQuery(querySender);

                if (!rsSender.next()) {
                    JOptionPane.showMessageDialog(null, "Incorrect Sender Account Number");
                    return;
                }

                String query = "SELECT * FROM bank WHERE accountnumber = '" + senderAccount + "'";
                ResultSet rs = conn.s.executeQuery(query);

                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                int amount = Integer.parseInt(transferAmount);
                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance");
                    return;
                }

                String queryRecipient = "SELECT * FROM login WHERE accountnumber = '" + recipientAccount + "'";
                ResultSet rsRecipient = conn.s.executeQuery(queryRecipient);

                if (!rsRecipient.next()) {
                    JOptionPane.showMessageDialog(null, "Recipient account number is incorrect");
                    return;
                }

                Date date = new Date();
                String withdrawQuery = "INSERT INTO bank(accountnumber, date, type, amount) VALUES ('" + senderAccount + "', '" + new java.sql.Timestamp(date.getTime()) + "', 'Withdrawal', '" + amount + "')";
                conn.s.executeUpdate(withdrawQuery);

                String depositQuery = "INSERT INTO bank(accountnumber, date, type, amount) VALUES ('" + recipientAccount + "', '" + new java.sql.Timestamp(date.getTime()) + "', 'Deposit', '" + amount + "')";
                conn.s.executeUpdate(depositQuery);

                JOptionPane.showMessageDialog(null, "Amount transferred successfully");

                setVisible(false);
                new Transactions(formno).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Transactions(formno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AccountTransfer("").setVisible(true);
    }
}
