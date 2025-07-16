# 🏧 ATM Simulator System

A Java-based desktop application simulating core ATM functionalities. This project is developed using **NetBeans** and **MySQL**, offering secure login for Admin and Cashier roles, transaction handling, and user management features.

---

## 🚀 Features

### 🔐 Login System
- Role-based login (Admin / Cashier)
- Secure and simple authentication

### 👨‍💼 Admin Panel
- Create, edit, and delete users
- View transaction history
- Manage system access

### 💼 Cashier Panel
- Fast Cash Withdrawal
- Withdraw Custom Amount
- Deposit Funds
- Account-to-Account Transfer
- Balance Enquiry

### ⚙️ Additional
- Daily withdrawal limits
- Error handling (e.g., insufficient funds, invalid input)
- Clean and interactive GUI

---

## 🛠️ Tech Stack

| Tool             | Purpose                       |
|------------------|-------------------------------|
| Java (Swing)     | Desktop application GUI       |
| NetBeans         | Development IDE               |
| MySQL            | Relational database backend   |
| JCalendar        | Date picker for transactions  |

---

## 📁 Project Structure

```plaintext
ATM-Simulator-System/
├── src/                    # Java source files
│   ├── Login.java
│   ├── AdminPanel.java
│   ├── CashierPanel.java
│   ├── TransactionHandler.java
│   └── ...                # Additional classes
│
├── lib/                   # External libraries
│   └── jcalendar-x.x.jar
│
├── sql/                   # SQL database setup
│   └── atm_database.sql
│
├── README.md              # This documentation file
└── LICENSE                # Open-source license

---

## 💻 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/Pramodsnz01/ATM-Simulator-System.git
2. Open in NetBeans
Launch NetBeans IDE

Open the project folder

3. Configure the Database
Use MySQL or any compatible SQL tool

Import the SQL script from:

pgsql
Copy
Edit
/sql/atm_database.sql
4. Add External Library
Add jcalendar.jar from the lib/ folder to your project libraries in NetBeans

5. Run the Application
Use one of the default credentials below to log in:

txt
Copy
Edit
Admin Login:
Username: admin
Password: admin123

Cashier Login:
Username: cashier
Password: cashier123
📷 Screenshots (Optional)
<!-- Add images like: ![Login Page](screenshots/login.png) ![Admin Panel](screenshots/admin-panel.png) -->
👤 Author
Pramod Singh
📧 Email: pramodsnz01@gmail.com
🌐 GitHub: Pramodsnz01

📄 License
This project is licensed under the MIT License — see the LICENSE file for details.