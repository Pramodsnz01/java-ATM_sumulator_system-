package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField, phoneTextField;
    JButton next, back;
    JRadioButton male, female, married, unmarried, other;
    JDateChooser dateChooser;

    SignupOne() {
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 30));
        formno.setBounds(180, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("MM/dd/yyyy");  
        dateChooser.setBounds(300, 240, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setFont(new Font("Raleway", Font.BOLD, 14));
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 290, 120, 30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 100, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(630, 390, 100, 30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 440, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel phone = new JLabel("Phone Number:");
        phone.setFont(new Font("Raleway", Font.BOLD, 20));
        phone.setBounds(100, 590, 200, 30);
        add(phone);

        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        phoneTextField.setBounds(300, 590, 400, 30);
        add(phoneTextField);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(440, 620, 100, 30);
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.addActionListener(this);
        add(next);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBounds(250, 620, 100, 30);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 700);
        setLocation(350, 5);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new AdminInterface("").setVisible(true);
            return;
        }

        if (ae.getSource() == next) {
            String formno = "" + random; // long
            String name = nameTextField.getText(); // setText
            String fname = fnameTextField.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String gender = null;
            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }

            String email = emailTextField.getText();
            String marital = null;
            if (married.isSelected()) {
                marital = "Married";
            } else if (unmarried.isSelected()) {
                marital = "Unmarried";
            } else if (other.isSelected()) {
                marital = "Other";
            }

            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String phone = phoneTextField.getText();

            // Regular expressions for validation
            String emailRegex = "^[\\w-\\.]+@gmail\\.com$";
            String phoneRegex = "^[0-9]{10}$";

            try {
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Name is Required");
                } else if (fname.equals("")) {
                    JOptionPane.showMessageDialog(null, "Father's Name is Required");
                } else if (dob.equals("")) {
                    JOptionPane.showMessageDialog(null, "Date of Birth is Required");
                } else if (email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Email is Required");
                } else if (!email.matches(emailRegex)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Gmail address");
                } else if (address.equals("")) {
                    JOptionPane.showMessageDialog(null, "Your address is Required");
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "Phone number is Required");
                } else if (!phone.matches(phoneRegex)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number");
                } else {
                    Conn c = new Conn();
                    String query = "INSERT INTO SignupOne (formno, name, fname, dob, gender, email, marital, address, city, state, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = c.getConnection().prepareStatement(query);
                    ps.setString(1, formno);
                    ps.setString(2, name);
                    ps.setString(3, fname);
                    ps.setDate(4, new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(dob).getTime()));
                    ps.setString(5, gender);
                    ps.setString(6, email);
                    ps.setString(7, marital);
                    ps.setString(8, address);
                    ps.setString(9, city);
                    ps.setString(10, state);
                    ps.setString(11, phone);
                    ps.executeUpdate();

                    setVisible(false);
                    new SignupTwo(formno).setVisible(true);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
