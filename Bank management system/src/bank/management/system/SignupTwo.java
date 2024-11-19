package bank.management.system;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class SignupTwo extends JFrame implements ActionListener {
    
    JButton next, back;
    JRadioButton syes, sno, eyes, eno;
    JComboBox<String> religion1, occupation1, education1, income1;
    String formno;
    
    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);
        
        JLabel religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));
        religion.setBounds(100, 140, 100, 30);
        add(religion);
        
        String[] valReligion = {"Hindu", "Muslim", "Christian", "Other"};
        religion1 = new JComboBox<>(valReligion);
        religion1.setBounds(300, 140, 400, 30);
        religion1.setBackground(Color.WHITE);
        add(religion1);
        
        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 20));
        income.setBounds(100, 220, 200, 30);
        add(income);
        
        String[] incomeCategory = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000"};
        income1 = new JComboBox<>(incomeCategory);
        income1.setBounds(300, 220, 400, 30);
        income1.setBackground(Color.WHITE);
        add(income1);
        
        JLabel education = new JLabel("Education");
        education.setFont(new Font("Raleway", Font.BOLD, 20));
        education.setBounds(100, 290, 200, 30);
        add(education);
        
        JLabel qualification = new JLabel("Qualification:");
        qualification.setFont(new Font("Raleway", Font.BOLD, 20));
        qualification.setBounds(100, 315, 200, 30);
        add(qualification);
        
        String[] eduValues = {"Non Graduation", "Graduate", "Post Graduation", "Doctorate", "Others"};
        education1 = new JComboBox<>(eduValues);
        education1.setBounds(300, 315, 400, 30);
        education1.setBackground(Color.WHITE);
        add(education1);
        
        JLabel occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        occupation.setBounds(100, 400, 200, 30);
        add(occupation);
        
        String[] occuValues = {"Salaried", "Self Employed", "Business", "Student", "Retired", "Others"};
        occupation1 = new JComboBox<>(occuValues);
        occupation1.setBounds(300, 400, 400, 30);
        occupation1.setBackground(Color.WHITE);
        add(occupation1);
        
        JLabel seniorCitizen = new JLabel("Senior Citizen:");
        seniorCitizen.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorCitizen.setBounds(100, 490, 200, 30);
        add(seniorCitizen);
        
        syes = new JRadioButton("Yes");
        syes.setBounds(300, 490, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);
        
        sno = new JRadioButton("No");
        sno.setBounds(450, 490, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);
        
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);
        
        JLabel existingAccount = new JLabel("Existing Account:");
        existingAccount.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccount.setBounds(100, 560, 200, 30);
        add(existingAccount);
        
        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 560, 100, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
        
        eno = new JRadioButton("No");
        eno.setBounds(450, 560, 100, 30);
        eno.setBackground(Color.WHITE);
        add(eno);
        
        ButtonGroup existGroup = new ButtonGroup();
        existGroup.add(eyes);
        existGroup.add(eno);
        
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
            new SignupOne().setVisible(true);
            return;
        }
        String sreligion = (String) religion1.getSelectedItem();
        String sincome = (String) income1.getSelectedItem();
        String seducation = (String) education1.getSelectedItem();
        String soccupation = (String) occupation1.getSelectedItem();
        String seniorcitizen = syes.isSelected() ? "Yes" : "No";
        String existingaccount = eyes.isSelected() ? "Yes" : "No";
        
        try {
            Conn c = new Conn();
            String query = "INSERT INTO signupTwo (formno, religion, income, education, occupation, seniorcitizen, existingaccount) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.getConnection().prepareStatement(query);
            ps.setString(1, formno);
            ps.setString(2, sreligion);
            ps.setString(3, sincome);
            ps.setString(4, seducation);
            ps.setString(5, soccupation);
            ps.setString(6, seniorcitizen);
            ps.setString(7, existingaccount);
            ps.executeUpdate();
            
            setVisible(false);
            new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        new SignupTwo("");
    }
}
