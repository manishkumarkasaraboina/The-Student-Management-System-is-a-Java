# Complete Beginner's Guide - How to Run Student Management System

This guide will walk you through every step needed to run this project, assuming you're starting from scratch.

---

## 📋 Step 1: Install Prerequisites

### 1.1 Install Java (JDK)

**What is JDK?** Java Development Kit - needed to compile and run Java programs.

**How to install:**
1. Go to: https://www.oracle.com/java/technologies/downloads/
2. Download **JDK 11 or higher** for Windows
3. Run the installer (accept all defaults)
4. **Verify installation:**
   - Open Command Prompt (Press `Windows + R`, type `cmd`, press Enter)
   - Type: `java -version`
   - You should see something like: `java version "11.0.x"` or higher
   - If you see an error, Java is not installed correctly

**Alternative:** You can also use OpenJDK from: https://adoptium.net/

---

### 1.2 Install MySQL Database

**What is MySQL?** A database server where we'll store student data.

**How to install:**
1. Go to: https://dev.mysql.com/downloads/installer/
2. Download **MySQL Installer for Windows**
3. Run the installer:
   - Choose "Developer Default" setup type
   - During installation, you'll be asked to set a **root password**
   - **IMPORTANT:** Remember this password! (e.g., `root123` or `password`)
   - Complete the installation
4. **Verify installation:**
   - Open Command Prompt
   - Type: `mysql --version`
   - You should see MySQL version number

**Note:** If MySQL is not in your PATH, you can use MySQL Workbench (installed with MySQL) instead.

---

### 1.3 Install MySQL Workbench (Optional but Recommended)

**What is MySQL Workbench?** A visual tool to manage your database easily.

**How to install:**
- Usually installed automatically with MySQL Installer
- If not, download from: https://dev.mysql.com/downloads/workbench/
- Launch MySQL Workbench from Start Menu

---

## 📋 Step 2: Set Up the Database

### 2.1 Start MySQL Server

**Option A: Using Windows Services**
1. Press `Windows + R`, type `services.msc`, press Enter
2. Find "MySQL80" (or similar) in the list
3. Right-click → Start (if not already running)
4. Status should show "Running"

**Option B: Using MySQL Workbench**
1. Open MySQL Workbench
2. It will automatically try to connect to MySQL
3. If it connects, MySQL is running!

---

### 2.2 Create Database and Table

**Method 1: Using MySQL Workbench (Easier for Beginners)**

1. Open MySQL Workbench
2. Click on the local connection (usually named "Local instance MySQL80")
3. Enter your root password (the one you set during installation)
4. You should see a SQL editor window
5. Open the file: `src/main/resources/database/schema.sql` in a text editor (Notepad)
6. **Copy ALL the content** from that file
7. **Paste it into MySQL Workbench** SQL editor
8. Click the **Execute** button (⚡ icon) or press `Ctrl + Shift + Enter`
9. You should see "Query OK" messages
10. In the left panel, refresh the database list (right-click → Refresh All)
11. You should see `student_management` database

**Method 2: Using Command Line**

1. Open Command Prompt
2. Navigate to MySQL bin folder (usually `C:\Program Files\MySQL\MySQL Server 8.0\bin`)
   - Or add MySQL to your PATH
3. Type: `mysql -u root -p`
4. Enter your MySQL root password
5. Copy and paste the content from `schema.sql` file
6. Press Enter after each command
7. Type: `USE student_management;`
8. Type: `SHOW TABLES;` - you should see `students` table
9. Type: `SELECT * FROM students;` - you should see 3 sample students
10. Type: `exit;` to leave MySQL

---

## 📋 Step 3: Configure Database Connection

### 3.1 Update Database Credentials

1. Open the file: `src/main/java/com/sms/util/DatabaseConnection.java`
2. Find these lines (around line 9-11):
   ```java
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "root";
   ```
3. **Change the password** to match your MySQL root password:
   ```java
   private static final String DB_PASSWORD = "your_actual_password";
   ```
4. **Save the file**

**Note:** If your MySQL username is not "root", change `DB_USER` as well.

---

## 📋 Step 4: Download MySQL JDBC Driver

**What is JDBC Driver?** A library that allows Java to talk to MySQL database.

**How to download:**
1. Go to: https://dev.mysql.com/downloads/connector/j/
2. Click "Download" (choose Platform Independent)
3. Download the ZIP file (e.g., `mysql-connector-j-8.0.33.zip`)
4. Extract the ZIP file
5. Find the JAR file inside (e.g., `mysql-connector-j-8.0.33.jar`)
6. **Copy this JAR file** to your project folder:
   - Create a folder called `lib` in your project root
   - Place the JAR file there
   - Full path should be: `java_project/lib/mysql-connector-j-8.0.33.jar`

**Example structure:**
```
java_project/
├── lib/
│   └── mysql-connector-j-8.0.33.jar  ← JAR file here
├── src/
└── pom.xml
```

---

## 📋 Step 5: Compile the Project

### 5.1 Open Command Prompt in Project Folder

1. Open File Explorer
2. Navigate to: `C:\Users\Lenovo\OneDrive\Desktop\java_project`
3. Click in the address bar, type `cmd`, press Enter
   - This opens Command Prompt in the project folder

---

### 5.2 Compile Java Files

**Copy and paste this command** (replace JAR filename if different):

```bash
javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out src/main/java/com/sms/model/Student.java src/main/java/com/sms/util/DatabaseConnection.java src/main/java/com/sms/dao/StudentDAO.java src/main/java/com/sms/service/StudentService.java src/main/java/com/sms/main/Main.java
```

**What this does:**
- `javac` = Java compiler
- `-cp` = classpath (tells Java where to find libraries)
- `-d out` = output directory (compiled files go to `out` folder)
- Last part = list of all Java files to compile

**Expected result:**
- If successful: No output, just returns to prompt
- If error: You'll see error messages (read them carefully)

**Common errors:**
- `'javac' is not recognized` → Java not installed or not in PATH
- `package does not exist` → Check file paths are correct
- `cannot find symbol` → Missing JAR file or wrong path

---

## 📋 Step 6: Run the Application

### 6.1 Execute the Program

**Copy and paste this command** (replace JAR filename if different):

```bash
java -cp "out;lib/mysql-connector-j-8.0.33.jar" com.sms.main.Main
```

**Note:** Use semicolon (`;`) on Windows, colon (`:`) on Mac/Linux

**Expected output:**
```
Database connection established successfully.
========================================
   Student Management System (SMS)
========================================

========== MAIN MENU ==========
1. Add New Student
2. Update Student
3. Delete Student
4. List All Students
5. Search Student by ID
6. Search Student by Name
7. Exit
===============================
Enter your choice:
```

**If you see errors:**
- `ClassNotFoundException` → Check JAR file path
- `SQLException` → Check database connection (Step 2 and 3)
- `Access denied` → Wrong MySQL password (Step 3)

---

## 📋 Step 7: Test the Application

### 7.1 Try These Operations

1. **List All Students:**
   - Type `4` and press Enter
   - You should see 3 sample students

2. **Search by ID:**
   - Type `5` and press Enter
   - Enter: `STU001`
   - You should see John Doe's information

3. **Add a New Student:**
   - Type `1` and press Enter
   - Enter the following:
     - Student ID: `STU004`
     - Name: `Alice Brown`
     - Date of Birth: `2002-03-20`
     - Class: `Class 12A`
     - Email: `alice.brown@example.com`
     - Address: `321 Elm Street, City`
   - You should see "✓ Student added successfully!"

4. **List All Students Again:**
   - Type `4` and press Enter
   - You should now see 4 students (including Alice)

5. **Exit:**
   - Type `7` and press Enter
   - Application closes

---

## 🔧 Troubleshooting Common Issues

### Issue 1: "javac is not recognized"
**Solution:**
- Java is not installed or not in PATH
- Reinstall Java and make sure to check "Add to PATH" during installation
- Or manually add Java to PATH:
  1. Find Java installation folder (usually `C:\Program Files\Java\jdk-11.x.x\bin`)
  2. Add this to Windows PATH environment variable

### Issue 2: "Cannot connect to database"
**Solution:**
- Check MySQL is running (Step 2.1)
- Verify database exists: `SHOW DATABASES;` in MySQL
- Check password in `DatabaseConnection.java` (Step 3)
- Try connecting manually: `mysql -u root -p`

### Issue 3: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solution:**
- JDBC driver JAR file is missing or wrong path
- Make sure JAR file is in `lib` folder
- Check the filename matches in your command
- Download the JAR file again (Step 4)

### Issue 4: "Access denied for user 'root'@'localhost'"
**Solution:**
- Wrong MySQL password
- Update `DatabaseConnection.java` with correct password
- Or reset MySQL root password

### Issue 5: "Unknown database 'student_management'"
**Solution:**
- Database not created
- Run `schema.sql` again (Step 2.2)

### Issue 6: Compilation errors about packages
**Solution:**
- Make sure you're in the project root folder
- Check file structure matches exactly:
  ```
  src/main/java/com/sms/model/Student.java
  src/main/java/com/sms/util/DatabaseConnection.java
  etc.
  ```

---

## 🎯 Quick Reference Commands

**Compile:**
```bash
javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out src/main/java/com/sms/model/Student.java src/main/java/com/sms/util/DatabaseConnection.java src/main/java/com/sms/dao/StudentDAO.java src/main/java/com/sms/service/StudentService.java src/main/java/com/sms/main/Main.java
```

**Run:**
```bash
java -cp "out;lib/mysql-connector-j-8.0.33.jar" com.sms.main.Main
```

**Compile and Run (one-liner for Windows):**
```bash
javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out src/main/java/com/sms/**/*.java && java -cp "out;lib/mysql-connector-j-8.0.33.jar" com.sms.main.Main
```

---

## 💡 Alternative: Using an IDE (Easier Method)

If command line seems difficult, use an IDE:

### Using IntelliJ IDEA (Recommended)

1. **Download IntelliJ IDEA Community Edition** (Free):
   - https://www.jetbrains.com/idea/download/

2. **Open Project:**
   - File → Open → Select `java_project` folder

3. **Add JDBC Driver:**
   - Right-click project → Open Module Settings
   - Libraries → + → Java → Select your JAR file
   - Click OK

4. **Run:**
   - Right-click `Main.java` → Run 'Main.main()'
   - Or press `Shift + F10`

### Using Eclipse

1. **Download Eclipse IDE:**
   - https://www.eclipse.org/downloads/

2. **Import Project:**
   - File → Import → Existing Projects into Workspace
   - Select `java_project` folder

3. **Add JDBC Driver:**
   - Right-click project → Build Path → Add External Archives
   - Select your MySQL JAR file

4. **Run:**
   - Right-click `Main.java` → Run As → Java Application

---

## ✅ Success Checklist

Before running, make sure:
- [ ] Java is installed (`java -version` works)
- [ ] MySQL is installed and running
- [ ] Database `student_management` exists
- [ ] Table `students` exists with sample data
- [ ] `DatabaseConnection.java` has correct password
- [ ] MySQL JDBC JAR file is in `lib` folder
- [ ] All Java files compiled successfully
- [ ] Application runs and shows menu

---

## 🎓 What You Learned

After completing this guide, you should understand:
- How to set up a Java development environment
- How to work with MySQL database
- How to compile and run Java programs from command line
- How Java applications connect to databases using JDBC
- Basic troubleshooting skills

---

**Need Help?** If you get stuck, check:
1. Error messages carefully (they usually tell you what's wrong)
2. All steps above are completed
3. File paths are correct
4. Passwords match

Good luck! 🚀

