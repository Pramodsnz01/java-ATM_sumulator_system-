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

💻 Setup Instructions
1. Clone the Repository
bash
Copy
Edit
git clone https://github.com/Pramodsnz01/ATM-Simulator-System.git
2. Open in NetBeans
Launch NetBeans IDE

Open the cloned project folder

3. Configure the Database
Ensure MySQL is installed and running

Import the SQL script located at:

pgsql
Copy
Edit
/sql/atm_database.sql
💡 You can use tools like phpMyAdmin, MySQL Workbench, or the command line to import the file.

4. Add JCalendar Library
In NetBeans:
Right-click your project → Properties → Libraries → Add JAR/Folder

Select the JAR file from:

bash
Copy
Edit
/lib/jcalendar-x.x.jar
5. Run the Application
Use the following sample credentials to log in:

🔐 Admin Login
Username: admin

Password: admin123

💼 Cashier Login
Username: cashier

Password: cashier123

 
markdown
Copy
Edit
![Login Page](screenshots/login.png)
![Admin Panel](screenshots/admin-panel.png)
👤 Author
Pramod Singh
📧 Email: pramodsnz01@gmail.com
🌐 GitHub: Pramodsnz01

📄 License
This project is licensed under the MIT License.
See the LICENSE file for more information.