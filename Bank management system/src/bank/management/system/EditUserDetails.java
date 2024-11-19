package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EditUserDetails extends JFrame implements ActionListener {

    JTextField accountNumberField, emailField, addressField, cityField, stateField, phoneField;
    JButton search, update, back;
    
    EditUserDetails() {
        setTitle("Edit User Details");
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/edit.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 600);
        add(image);
        
        JLabel title = new JLabel("UPDATE YOUR DETAILS");
        title.setBounds(280, 70, 300, 30);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("System", Font.BOLD, 20));
        image.add(title);
        
        JLabel searchText = new JLabel("Enter Account Number:");
        searchText.setBounds(150, 160, 200, 30);
        searchText.setForeground(Color.BLACK);
        searchText.setFont(new Font("System", Font.BOLD, 16));
        image.add(searchText);

        accountNumberField = new JTextField();
        accountNumberField.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberField.setBounds(350, 160, 230, 30);
        image.add(accountNumberField);

        search = new JButton("Search");
        search.setBounds(590, 160, 100, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.addActionListener(this);
        image.add(search);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 210, 100, 30);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("System", Font.BOLD, 16));
        image.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 14));
        emailField.setBounds(350, 210, 230, 30);
        image.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(150, 260, 100, 30);
        addressLabel.setForeground(Color.BLACK);
        addressLabel.setFont(new Font("System", Font.BOLD, 16));
        image.add(addressLabel);

        addressField = new JTextField();
        addressField.setFont(new Font("Arial", Font.BOLD, 14));
        addressField.setBounds(350, 260, 230, 30);
        image.add(addressField);
        
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(150, 310, 100, 30);
        cityLabel.setForeground(Color.BLACK);
        cityLabel.setFont(new Font("System", Font.BOLD, 16));
        image.add(cityLabel);

        cityField = new JTextField();
        cityField.setFont(new Font("Arial", Font.BOLD, 14));
        cityField.setBounds(350, 310, 230, 30);
        image.add(cityField);
        
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(150, 360, 100, 30);
        stateLabel.setForeground(Color.BLACK);
        stateLabel.setFont(new Font("System", Font.BOLD, 16));
        image.add(stateLabel);

        stateField = new JTextField();
        stateField.setFont(new Font("Arial", Font.BOLD, 14));
        stateField.setBounds(350, 360, 230, 30);
        image.add(stateField);
        
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(150, 410, 100, 30);
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(new Font("System", Font.BOLD, 16));
        image.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Arial", Font.BOLD, 14));
        phoneField.setBounds(350, 410, 230, 30);
        image.add(phoneField);
        
        update = new JButton("UPDATE");
        update.setBounds(350, 480, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.addActionListener(this);
        image.add(update);

        back = new JButton("BACK");
        back.setBounds(480, 480, 100, 30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        image.add(back);

        setSize(850, 600);
        setLocation(200, 50);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            searchUser();
        } else if (ae.getSource() == update) {
            updateUserDetails();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new AdminInterface("").setVisible(true);  
        }
    }

    private void searchUser() {
        String accountNumber = accountNumberField.getText();
        if (accountNumber.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the account number");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "SELECT s.email, s.address, s.city, s.state, s.phone FROM signupOne s INNER JOIN signupThree s3 ON s.formno = s3.formno WHERE s3.accountnumber = ?";
            PreparedStatement pst = conn.getConnection().prepareStatement(query);
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                emailField.setText(rs.getString("email"));
                addressField.setText(rs.getString("address"));
                cityField.setText(rs.getString("city"));
                stateField.setText(rs.getString("state"));
                phoneField.setText(rs.getString("phone"));
            } else {
                JOptionPane.showMessageDialog(null, "Account not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserDetails() {
        String accountNumber = accountNumberField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String phone = phoneField.getText();

        if (email.equals("") || address.equals("") || city.equals("") || state.equals("") || phone.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }

        // Regular expressions for validation
        String emailRegex = "^[\\w-\\.]+@gmail\\.com$";
        String phoneRegex = "^[0-9]{10}$";

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Gmail address");
            return;
        }

        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = "UPDATE signupOne s INNER JOIN signupThree s3 ON s.formno = s3.formno SET s.email = ?, s.address = ?, s.city = ?, s.state = ?, s.phone = ? WHERE s3.accountnumber = ?";
            PreparedStatement pst = conn.getConnection().prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, address);
            pst.setString(3, city);
            pst.setString(4, state);
            pst.setString(5, phone);
            pst.setString(6, accountNumber);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Details updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EditUserDetails().setVisible(true);
    }
}
