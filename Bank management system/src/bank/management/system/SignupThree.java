package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {

        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 120, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 160, 200, 20);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(300, 160, 250, 20);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 200, 200, 20);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(300, 200, 250, 20);
        add(r4);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JLabel account = new JLabel("Account Number:");
        account.setFont(new Font("Raleway", Font.BOLD, 22));
        account.setBounds(100, 250, 200, 40);
        add(account);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-2345");
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 250, 300, 40);
        add(number);

        JLabel carddetail = new JLabel("Your 16 Digit Account Number");
        carddetail.setFont(new Font("Raleway", Font.BOLD, 12));
        carddetail.setBounds(100, 285, 300, 20);
        add(carddetail);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 370, 200, 40);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 410, 200, 30);
        add(c1);

        c2 = new JCheckBox("Online Banking Card");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(300, 410, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 450, 200, 30);
        add(c3);

        c4 = new JCheckBox("Email & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(300, 450, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 490, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(300, 490, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 13));
        c7.setBounds(100, 550, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 620, 100, 30);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(440, 620, 100, 30);
        cancel.addActionListener(this);
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(cancel);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 800);
        setLocation(300, 0);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Saving Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            Random random = new Random();
            String accountnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);

            String facility = "";
            if (c1.isSelected()) {
                facility += "ATM Card, ";
            }
            if (c2.isSelected()) {
                facility += "Internet Banking, ";
            }
            if (c3.isSelected()) {
                facility += "Mobile Banking, ";
            }
            if (c4.isSelected()) {
                facility += "Email & SMS Alerts, ";
            }
            if (c5.isSelected()) {
                facility += "Cheque Book, ";
            }
            if (c6.isSelected()) {
                facility += "E-Statement, ";
            }

            // Removing the trailing comma and space from the facility string
            if (!facility.isEmpty()) {
                facility = facility.substring(0, facility.length() - 2);
            }

            try {
                if (accountType == null) {
                    JOptionPane.showMessageDialog(null, "Account Type is Required");
                } else {
                    Conn conn = new Conn();

                    // Checking if the form number already exists in signupThree
                    String checkQuery = "SELECT formno FROM signupThree WHERE formno = ?";
                    PreparedStatement ps = conn.getConnection().prepareStatement(checkQuery);
                    ps.setString(1, formno);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Form Number already exists");
                    } else {
                        String query1 = "INSERT INTO signupThree (formno, accountType, accountnumber, servicesRequired) VALUES (?, ?, ?, ?)";
                        PreparedStatement ps1 = conn.getConnection().prepareStatement(query1);
                        ps1.setString(1, formno);
                        ps1.setString(2, accountType);
                        ps1.setString(3, accountnumber);
                        ps1.setString(4, facility);
                        ps1.executeUpdate();

                        String query2 = "INSERT INTO login (formno, accountnumber) VALUES (?, ?)";
                        PreparedStatement ps2 = conn.getConnection().prepareStatement(query2);
                        ps2.setString(1, formno);
                        ps2.setString(2, accountnumber);
                        ps2.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Account Number: " + accountnumber);

                        setVisible(false);
                        new AdminInterface(formno).setVisible(true);
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new AdminInterface(formno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
