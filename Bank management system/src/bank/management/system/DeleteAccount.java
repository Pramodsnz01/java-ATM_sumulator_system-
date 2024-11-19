package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteAccount extends JFrame implements ActionListener {
    
    JTextField accountNumberField;
    JLabel formNumberLabel, accountHolderNameLabel;
    JButton deleteButton, backButton, searchButton;

    DeleteAccount() {
        setLayout(null);
        
        setTitle("DELETE ACCOUNT");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/deleteBg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 1000, 600);
        add(label);
        
        JLabel deleteAccountLabel = new JLabel("DELETE ACCOUNT");
        deleteAccountLabel.setFont(new Font("Raleway", Font.BOLD, 25));
        deleteAccountLabel.setBounds(400, 80, 400, 30);
        label.add(deleteAccountLabel);
        
        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        accountNumberLabel.setBounds(250, 170, 200, 30);
        label.add(accountNumberLabel);
        
        accountNumberField = new JTextField();
        accountNumberField.setFont(new Font("Raleway", Font.BOLD, 14));
        accountNumberField.setBounds(480, 170, 150, 30);
        label.add(accountNumberField);
        
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Raleway", Font.BOLD, 14));
        searchButton.setBounds(640, 170, 100, 30);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(this);
        label.add(searchButton);
        
        JLabel formNumberTitleLabel = new JLabel("Form Number:");
        formNumberTitleLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        formNumberTitleLabel.setBounds(250, 230, 200, 30);
        label.add(formNumberTitleLabel);
        
        formNumberLabel = new JLabel();
        formNumberLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        formNumberLabel.setBounds(480, 230, 400, 30);
        label.add(formNumberLabel);
        
        JLabel accountHolderTitleLabel = new JLabel("Account Holder Name:");
        accountHolderTitleLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        accountHolderTitleLabel.setBounds(250, 290, 250, 30);
        label.add(accountHolderTitleLabel);
        
        accountHolderNameLabel = new JLabel();
        accountHolderNameLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        accountHolderNameLabel.setBounds(480, 290, 350, 30);
        label.add(accountHolderNameLabel);
        
        deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Raleway", Font.BOLD, 14));
        deleteButton.setBounds(640, 360, 100, 30);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);
        label.add(deleteButton);
        
        backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Raleway", Font.BOLD, 14));
        backButton.setBounds(250, 360, 100, 30);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        label.add(backButton);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850, 500);
        setUndecorated(true);
        setLocation(200, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new AdminInterface("").setVisible(true);
            return;
        }
        
        if (ae.getSource() == searchButton) {
            String accountNumber = accountNumberField.getText();
            if (accountNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the account number");
                return;
            }
            try {
                Conn c = new Conn();
                String query1 = "SELECT s.name, s3.formno FROM signupOne s INNER JOIN signupThree s3 ON s.formno = s3.formno WHERE s3.accountnumber = ?";
                
                PreparedStatement ps1 = c.getConnection().prepareStatement(query1);
                ps1.setString(1, accountNumber);
                ResultSet rs1 = ps1.executeQuery();
                
                if (rs1.next()) {
                    String accountHolderName = rs1.getString("name");
                    String formNumber = rs1.getString("formno");
                    accountHolderNameLabel.setText(accountHolderName);
                    formNumberLabel.setText(formNumber);
                } else {
                    accountHolderNameLabel.setText("Account not found");
                    formNumberLabel.setText("");
                }
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error fetching account details");
            }
            return;
        }
        
        if (ae.getSource() == deleteButton) {
            String accountNumber = accountNumberField.getText();
            if (accountNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the account number");
                return;
            }
            try {
                Conn c = new Conn();
                String query1 = "DELETE s1, s2, s3 FROM signupOne s1 INNER JOIN signupTwo s2 ON s1.formno = s2.formno INNER JOIN signupThree s3 ON s1.formno = s3.formno WHERE s3.accountnumber = ?";
                
                PreparedStatement ps1 = c.getConnection().prepareStatement(query1);
                ps1.setString(1, accountNumber);
                
                int deletedRows = ps1.executeUpdate();
                
                if (deletedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted successfully");
                    formNumberLabel.setText("");
                    accountHolderNameLabel.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found or already deleted");
                }
                
                setVisible(false);
                new AdminInterface("").setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error deleting account");
            }
        }
    }
    
    public static void main(String[] args) {
        new DeleteAccount();
    }
}
