package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JTextField accountNumberTextField;
    JLabel mini, card, balance;
    String formno;
    JButton back;

    MiniStatement(String formno) {
        this.formno = formno;

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Global Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel accountNumberLabel = new JLabel("Account No:");
        accountNumberLabel.setFont(new Font("Raleway", Font.BOLD, 25));
        accountNumberLabel.setBounds(20, 70, 200, 30);
        add(accountNumberLabel);

        accountNumberTextField = new JTextField();
        accountNumberTextField.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberTextField.setBounds(220, 70, 150, 30);
        add(accountNumberTextField);

        JButton checkAccount = new JButton("Check");
        checkAccount.setBounds(245, 500, 125, 25);
        checkAccount.addActionListener(e -> checkAccountNumber());
        add(checkAccount);

        mini = new JLabel();
        mini.setBounds(20, 100, 360, 300);  
        add(mini);

        card = new JLabel();
        card.setBounds(20, 150, 300, 20);
        add(card);

        balance = new JLabel();
        balance.setBounds(20, 420, 300, 20);  
        add(balance);
        
        back = new JButton("Back");
        back.setBounds(20, 500, 125, 25);
        back.addActionListener(this);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(back);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
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
            String query = "select * from login where accountnumber = '" + accountnumber + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                displayMiniStatement(accountnumber);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Account Number");
            }
            rs.close();
            conn.s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayMiniStatement(String accountnumber) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where accountnumber = '" + accountnumber + "'");
            if (rs.next()) {
                card.setText("Account Number: " + rs.getString("accountnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("accountnumber").substring(rs.getString("accountnumber").length() - 4));
            }
            rs.close();

            int bal = 0;
            rs = conn.s.executeQuery("select * from bank where accountnumber = '" + accountnumber + "'");
            StringBuilder miniText = new StringBuilder("<html>");
            while (rs.next()) {
                miniText.append(rs.getString("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("type")).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("amount")).append("<br>");
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            miniText.append("</html>");
            mini.setText(miniText.toString());
            balance.setText("Your Current Account Balance Is Rs " + bal);

            rs.close();
            conn.s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new AdminInterface("").setVisible(true);
        } 
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}
