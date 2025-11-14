# 📋 Execution Steps - Visual Guide

Follow these steps in order to run the Student Management System:

```
┌─────────────────────────────────────────────────────────────┐
│                    STEP 1: INSTALL JAVA                      │
│  Download JDK from oracle.com or adoptium.net               │
│  Verify: java -version                                       │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                  STEP 2: INSTALL MYSQL                       │
│  Download MySQL Installer                                    │
│  Set root password (remember it!)                            │
│  Verify: mysql --version                                     │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│              STEP 3: CREATE DATABASE                         │
│  Open MySQL Workbench                                        │
│  Connect with root password                                  │
│  Open: src/main/resources/database/schema.sql                │
│  Copy & Paste → Execute (⚡ button)                         │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│          STEP 4: UPDATE DATABASE PASSWORD                    │
│  Open: src/main/java/com/sms/util/DatabaseConnection.java   │
│  Change: DB_PASSWORD = "your_mysql_password"                 │
│  Save file                                                   │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│          STEP 5: DOWNLOAD MYSQL JDBC DRIVER                  │
│  Go to: dev.mysql.com/downloads/connector/j/                │
│  Download ZIP (Platform Independent)                         │
│  Extract → Find JAR file                                     │
│  Create 'lib' folder in project root                         │
│  Copy JAR to lib/ folder                                     │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│              STEP 6: COMPILE & RUN                           │
│                                                              │
│  OPTION A: Double-click run.bat                              │
│                                                              │
│  OPTION B: Command Line:                                     │
│  1. Open CMD in project folder                               │
│  2. Compile:                                                 │
│     javac -cp "lib\mysql-connector-j-8.0.33.jar" -d out ...  │
│  3. Run:                                                     │
│     java -cp "out;lib\mysql-connector-j-8.0.33.jar" ...     │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                    STEP 7: USE APPLICATION                   │
│  Menu appears → Choose option (1-7)                          │
│  Test: Type 4 to list students                               │
│  Exit: Type 7                                                │
└─────────────────────────────────────────────────────────────┘
```

## 🎯 Command Reference

### Compile Command (Windows)
```bash
javac -cp "lib\mysql-connector-j-8.0.33.jar" -d out src\main\java\com\sms\model\Student.java src\main\java\com\sms\util\DatabaseConnection.java src\main\java\com\sms\dao\StudentDAO.java src\main\java\com\sms\service\StudentService.java src\main\java\com\sms\main\Main.java
```

### Run Command (Windows)
```bash
java -cp "out;lib\mysql-connector-j-8.0.33.jar" com.sms.main.Main
```

### Compile Command (Mac/Linux)
```bash
javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out src/main/java/com/sms/model/Student.java src/main/java/com/sms/util/DatabaseConnection.java src/main/java/com/sms/dao/StudentDAO.java src/main/java/com/sms/service/StudentService.java src/main/java/com/sms/main/Main.java
```

### Run Command (Mac/Linux)
```bash
java -cp "out:lib/mysql-connector-j-8.0.33.jar" com.sms.main.Main
```

## ✅ Checklist Before Running

- [ ] Java installed (`java -version` works)
- [ ] MySQL installed and running
- [ ] Database `student_management` created
- [ ] Table `students` exists (check with `SELECT * FROM students;`)
- [ ] Password updated in `DatabaseConnection.java`
- [ ] MySQL JDBC JAR in `lib` folder
- [ ] All files compiled (no errors)

## 🚨 Common Issues & Quick Fixes

| Issue | Quick Fix |
|-------|-----------|
| `javac not recognized` | Install Java, add to PATH |
| `Cannot connect to database` | Check MySQL is running, verify password |
| `ClassNotFoundException` | Check JAR file path in `lib` folder |
| `Unknown database` | Run `schema.sql` again |
| `Access denied` | Update password in `DatabaseConnection.java` |

## 📞 Need Help?

1. Check error messages carefully
2. Read `SETUP_GUIDE.md` for detailed explanations
3. Verify each step above is completed
4. Check file paths are correct

---

**Ready?** Start with Step 1! 🚀

