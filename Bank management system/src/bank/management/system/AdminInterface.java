package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminInterface extends JFrame implements ActionListener {

    JButton createUser, viewTransactions, logout, deleteUser, editUser;
    String formno;

    AdminInterface(String formno) {
        this.formno = formno;

        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Adminbg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 0, 1000, 600);
        add(label);
        
        
        JLabel text = new JLabel("ADMIN DASHBOARD");
        text.setFont(new Font("Serif", Font.BOLD, 38));
        text.setBounds(300, 90, 400, 40);
        label.add(text);

        createUser = new JButton("CREATE ACCOUNT");
        createUser.setFont(new Font("Raleway", Font.BOLD, 15));
        createUser.setBounds(150, 225, 200, 50);
        createUser.setBackground(Color.BLACK);
        createUser.setForeground(Color.WHITE);
        createUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createUser.addActionListener(this);
        label.add(createUser);
        
        deleteUser = new JButton("DELETE ACCOUNT");
        deleteUser.setFont(new Font("Raleway", Font.BOLD, 15));
        deleteUser.setBounds(270, 350, 200, 50);
        deleteUser.setBackground(Color.BLACK);
        deleteUser.setForeground(Color.WHITE);
        deleteUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteUser.addActionListener(this);
        label.add(deleteUser);
        
        editUser = new JButton("UPDATE ACCOUNT");
        editUser.setFont(new Font("Raleway", Font.BOLD, 15));
        editUser.setBounds(650, 225, 200, 50);
        editUser.setBackground(Color.BLACK);
        editUser.setForeground(Color.WHITE);
        editUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editUser.addActionListener(this);
        label.add(editUser);

        viewTransactions = new JButton("VIEW TRANSACTIONS");
        viewTransactions.setFont(new Font("Raleway", Font.BOLD, 15));
        viewTransactions.setBounds(400, 225, 200, 50);
        viewTransactions.setBackground(Color.BLACK);
        viewTransactions.setForeground(Color.WHITE);
        viewTransactions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewTransactions.addActionListener(this);
        label.add(viewTransactions);

        logout = new JButton("LOGOUT");
        logout.setFont(new Font("Raleway", Font.BOLD, 15));
        logout.setBounds(520, 350, 200, 50);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addActionListener(this);
        label.add(logout);

        getContentPane().setBackground(Color.WHITE);

        setSize(1000, 500);
//        setUndecorated(true);
        setVisible(true);
        setLocation(150, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createUser) {
            setVisible(false);
            new SignupOne().setVisible(true);
             
        } else if (ae.getSource() == viewTransactions) {
            setVisible(false);
            new MiniStatement(formno).setVisible(true); 
        
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login().setVisible(true);
            
        }else if (ae.getSource() == deleteUser) {
            setVisible(false);
            new DeleteAccount().setVisible(true);
            
        }else if (ae.getSource() == editUser) {
            setVisible(false);
            new EditUserDetails().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new AdminInterface("").setVisible(true);
    }
}
