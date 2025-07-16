# 🏧 ATM Simulator System

The **ATM Simulator System** is a Java-based desktop application developed using **NetBeans** that simulates core ATM operations. It features secure login, admin and cashier modules, transaction tracking, and user management — making it a great learning project for banking and software systems.

---

## 📌 Key Features

### 🔐 Login System
- Role-based login (Admin / Cashier)
- Secure authentication using credentials

### 👨‍💼 Admin Panel
- Create New User
- Edit User Details
- Delete User
- View All Users
- View All Transactions
- Logout

### 🧾 Cashier Panel
- Fast Cash Withdrawal
- Custom Withdrawal
- Deposit Funds
- Account-to-Account Transfer
- Balance Enquiry
- Logout

### ✅ Other Functionalities
- Transaction limits per day
- Input validation and user-friendly error messages
- Clear and intuitive GUI

---

## 🛠️ Technologies Used

| Component       | Details                    |
|----------------|----------------------------|
| Language        | Java (JDK 8+)              |
| IDE             | NetBeans                   |
| GUI Framework   | Java Swing                 |
| Database        | MySQL                      |
| Date Picker     | JCalendar (Swing component)|

---

## 📁 Project Structure

ATM-Simulator-System/
│
├── src/                  # Source files
│   ├── Login.java
│   ├── AdminPanel.java
│   ├── CashierPanel.java
│   ├── TransactionHandler.java
│   └── ...              # Other source files
│
├── lib/                  # External libraries
│   └── jcalendar-x.x.jar
│
├── sql/                  # Database SQL dump
│   └── atm_database.sql
│
├── README.md             # Project documentation
└── LICENSE               # License file


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

👤 Author
Pramod Singh
📧 Email: pramodsnz01@gmail.com
🌐 GitHub: Pramodsnz01

📄 License
This project is licensed under the MIT License — see the LICENSE file for details.

🌟 Support
If you like this project, consider giving it a ⭐ on GitHub!



