package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JRadioButton adminRadioButton, cashierRadioButton;
    ButtonGroup roleGroup;

    Login() {
        setTitle("WELCOME TO OUR SYSTEM");

        setLayout(null);
        
                
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/logbg.jpg"));
        Image i8 = i7.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel label2 = new JLabel(i9);
        label2.setBounds(0,0, 1000, 600);
        add(label2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(170, 20, 50, 50);
        label2.add(label);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/use.png"));
        Image i5 = i4.getImage().getScaledInstance(185, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel label1 = new JLabel(i6);
        label1.setBounds(670, 140, 185, 250);
        label2.add(label1);


        JLabel text = new JLabel("Integrated Bank Management System");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(230, 10, 600, 80);
        label2.add(text);

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Raleway", Font.BOLD, 30));
        username.setBounds(120, 150, 200, 30);
        label2.add(username);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        usernameTextField.setBounds(300, 150, 230, 30);
        label2.add(usernameTextField);

        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Raleway", Font.BOLD, 30));
        password.setBounds(120, 220, 250, 30);
        label2.add(password);

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordTextField.setBounds(300, 220, 230, 30);
        label2.add(passwordTextField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        roleLabel.setBounds(120, 290, 150, 30);
        label2.add(roleLabel);

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setFont(new Font("Raleway", Font.BOLD, 16));
        adminRadioButton.setBounds(300, 290, 100, 30);
        label2.add(adminRadioButton);

        cashierRadioButton = new JRadioButton("Cashier");
        cashierRadioButton.setFont(new Font("Raleway", Font.BOLD, 16));
        cashierRadioButton.setBounds(430, 290, 100, 30);
        label2.add(cashierRadioButton);

        roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(cashierRadioButton);

        login = new JButton("LOGIN");
        login.setBounds(300, 350, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(this);
        label2.add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 350, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.addActionListener(this);
        label2.add(clear);

        getContentPane().setBackground(Color.WHITE);

        setSize(1000, 500);
        setVisible(true);
        setLocation(100, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            usernameTextField.setText("");
            passwordTextField.setText("");
            roleGroup.clearSelection();
        } else if (ae.getSource() == login) {
            String username = usernameTextField.getText();
            String password = new String(passwordTextField.getPassword());
            String role = adminRadioButton.isSelected() ? "admin" : cashierRadioButton.isSelected() ? "cashier" : "";

            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields and select a role");
                return;
            }

            Conn conn = new Conn();
            String query = "SELECT * FROM Users WHERE username = ? AND password = ? AND role = ?";
            try {
                PreparedStatement ps = conn.getConnection().prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    if (role.equals("admin")) {
                        new AdminInterface(username).setVisible(true);
                    } else {
                        new CashierInterface(username).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username, Password or Role");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
