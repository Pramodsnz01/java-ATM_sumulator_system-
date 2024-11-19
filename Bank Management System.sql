create database bankmanagementsystem;

show databases;

use bankmanagementsystem;

CREATE TABLE signupOne (
    formno VARCHAR(20) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    fname VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    marital VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    pin VARCHAR(10) NOT NULL
);

ALTER TABLE signupOne
CHANGE COLUMN pin phone VARCHAR(255);

drop table signupThree;
show tables;

select * from signupOne;

CREATE TABLE signupTwo (
    formno VARCHAR(20) PRIMARY KEY,
    religion VARCHAR(50),
    income VARCHAR(50),
    education VARCHAR(50),
    occupation VARCHAR(50),
    seniorcitizen VARCHAR(10),
    existingaccount VARCHAR(10)
);

CREATE TABLE signupThree (
    formno VARCHAR(20) NOT NULL,
    accountType VARCHAR(50) NOT NULL,
    accountnumber VARCHAR(20) NOT NULL,
    servicesRequired VARCHAR(255),
    PRIMARY KEY (formno)
);




show tables;


select * from signupTwo;

select * from signupThree;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'cashier') NOT NULL
  
);
INSERT INTO Users (username, password, role) VALUES
('ram', 'ram123', 'admin'),
('mahesh', 'mahesh123', 'admin'),
('hari', 'hari123', 'cashier'),
('pram', 'pram123', 'cashier');

CREATE TABLE login (
    formno VARCHAR(20) NOT NULL,
    accountnumber VARCHAR(20) NOT NULL,
    PRIMARY KEY (formno)
);
 select * from login;
 select * from signupOne;
 select * from signupTwo;
 select * from signupThree;
 
 
 drop table login;
  drop table bank;
 create table bank(accountnumber varchar(30), date varchar(50), type varchar(20), amount varchar(20));
 
 select * from bank;