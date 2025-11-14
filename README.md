# Student Management System (SMS)

A Java-based console application for managing student records with full CRUD (Create, Read, Update, Delete) operations. This project demonstrates Java OOP principles, JDBC database connectivity, and clean architecture patterns.

## 📚 Getting Started

**New to Java/Programming?** Start here:
- **[QUICK_START.md](QUICK_START.md)** - 5-minute quick start guide
- **[SETUP_GUIDE.md](SETUP_GUIDE.md)** - Complete beginner-friendly step-by-step instructions

**Already familiar with Java?** Jump to [Setup Instructions](#setup-instructions) below.

## Features

- ✅ **Add New Student** - Register new students with validation
- ✅ **Update Student** - Modify existing student information
- ✅ **Delete Student** - Remove student records with confirmation
- ✅ **List All Students** - View all registered students in tabular format
- ✅ **Search by ID** - Quick lookup by student ID
- ✅ **Search by Name** - Partial name matching for flexible search
- ✅ **Input Validation** - Comprehensive validation for all fields
- ✅ **SQL Injection Prevention** - Uses prepared statements
- ✅ **Database Transactions** - Ensures data integrity

## Technology Stack

- **Java** - Core programming language
- **JDBC** - Database connectivity
- **MySQL** - Relational database (can be adapted for PostgreSQL)
- **Maven** - Project structure (optional)

## Project Structure

```
java_project/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── sms/
│       │           ├── model/
│       │           │   └── Student.java          # Student entity class
│       │           ├── dao/
│       │           │   └── StudentDAO.java       # Data Access Object
│       │           ├── service/
│       │           │   └── StudentService.java   # Business logic layer
│       │           ├── util/
│       │           │   └── DatabaseConnection.java # DB connection utility
│       │           └── main/
│       │               └── Main.java              # Application entry point
│       └── resources/
│           └── database/
│               └── schema.sql                    # Database schema
├── README.md
└── pom.xml (optional - for Maven projects)
```

## Prerequisites

1. **Java Development Kit (JDK)**
   - JDK 8 or higher
   - Verify installation: `java -version`

2. **MySQL Database**
   - MySQL Server 5.7 or higher (or MySQL 8.0)
   - MySQL Workbench or command-line access

3. **MySQL JDBC Driver**
   - Download: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Version: 8.0.x or higher
   - Place `mysql-connector-java-x.x.x.jar` in your classpath

4. **IDE (Optional but Recommended)**
   - IntelliJ IDEA
   - Eclipse
   - VS Code with Java extensions

## Setup Instructions

### Step 1: Database Setup

1. **Start MySQL Server**
   ```bash
   # Windows (if MySQL is installed as service, it should start automatically)
   # Or use MySQL Workbench to start the server
   ```

2. **Create Database and Table**
   - Open MySQL command line or MySQL Workbench
   - Run the SQL script:
   ```bash
   mysql -u root -p < src/main/resources/database/schema.sql
   ```
   - Or manually execute the SQL commands from `src/main/resources/database/schema.sql`

3. **Verify Database**
   ```sql
   USE student_management;
   SHOW TABLES;
   SELECT * FROM students;
   ```

### Step 2: Configure Database Connection

Edit `src/main/java/com/sms/util/DatabaseConnection.java` and update:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
private static final String DB_USER = "root";        // Your MySQL username
private static final String DB_PASSWORD = "root";    // Your MySQL password
```

### Step 3: Add MySQL JDBC Driver

**Option A: Using Maven (Recommended)**
- Create `pom.xml` in project root (see below)
- Run: `mvn clean install`

**Option B: Manual Setup**
1. Download MySQL Connector/J JAR file
2. Add to classpath:
   - **IntelliJ IDEA**: File → Project Structure → Libraries → Add JAR
   - **Eclipse**: Right-click project → Build Path → Add External Archives
   - **Command Line**: Use `-cp` flag: `javac -cp ".:mysql-connector-java-8.0.xx.jar" *.java`

### Step 4: Compile and Run

**Using Command Line:**
```bash
# Navigate to project root
cd java_project

# Compile (ensure MySQL JAR is in classpath)
javac -cp ".:mysql-connector-java-8.0.xx.jar" -d out src/main/java/com/sms/**/*.java

# Run
java -cp "out:mysql-connector-java-8.0.xx.jar" com.sms.main.Main
```

**Using IDE:**
- Import project into your IDE
- Add MySQL JDBC driver to classpath
- Run `Main.java`

## Usage

1. **Start the Application**
   - Run `Main.java`
   - The system will test database connection on startup

2. **Main Menu Options**
   ```
   1. Add New Student
   2. Update Student
   3. Delete Student
   4. List All Students
   5. Search Student by ID
   6. Search Student by Name
   7. Exit
   ```

3. **Example Workflow**
   - Choose option 1 to add a student
   - Enter required information:
     - Student ID: STU004
     - Name: Alice Brown
     - Date of Birth: 2002-03-20
     - Class: Class 12A
     - Email: alice.brown@example.com
     - Address: 321 Elm Street, City
   - View all students using option 4
   - Search for a student using option 5 or 6

## Database Schema

### Students Table

| Column        | Type         | Constraints           | Description            |
|--------------|--------------|-----------------------|------------------------|
| student_id   | VARCHAR(20)  | PRIMARY KEY           | Unique student ID      |
| name         | VARCHAR(100) | NOT NULL              | Student full name      |
| date_of_birth| DATE         | NOT NULL              | Date of birth          |
| class        | VARCHAR(50)  | NOT NULL              | Class/grade            |
| email        | VARCHAR(100) | NOT NULL              | Email address          |
| address      | VARCHAR(255) | NOT NULL              | Physical address       |
| created_at   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP | Record creation time |
| updated_at   | TIMESTAMP    | AUTO UPDATE           | Last update time       |

## Design Patterns Used

1. **DAO Pattern** - Separates data access logic from business logic
2. **Service Layer Pattern** - Encapsulates business rules and validation
3. **Singleton Pattern** - Database connection management
4. **MVC-like Structure** - Model (Student), View (Main/Console), Controller (Service)

## Security Features

- ✅ **Prepared Statements** - Prevents SQL injection attacks
- ✅ **Input Validation** - Validates all user inputs
- ✅ **Email Format Validation** - Regex pattern matching
- ✅ **Date Validation** - Prevents future dates

## Error Handling

- Database connection errors are caught and displayed
- Input validation errors provide clear feedback
- SQL exceptions are logged with descriptive messages
- Graceful shutdown with resource cleanup

## Future Enhancements (Out of Scope for MVP)

- Web-based UI (Spring Boot + Thymeleaf/React)
- Role-based access control (Admin, Teacher, Student)
- Attendance management module
- Fee management system
- Report generation (PDF/Excel)
- RESTful API endpoints
- Unit tests with JUnit
- Logging framework (Log4j/SLF4J)

## Troubleshooting

### Connection Issues
- **Error: "Access denied"** → Check MySQL username/password
- **Error: "Unknown database"** → Run schema.sql to create database
- **Error: "Communications link failure"** → Ensure MySQL server is running

### Compilation Issues
- **Error: "package com.mysql.cj.jdbc does not exist"** → Add MySQL JDBC JAR to classpath
- **Error: "cannot find symbol"** → Check package declarations match directory structure

### Runtime Issues
- **Error: "Table doesn't exist"** → Run schema.sql to create tables
- **Error: "Duplicate entry"** → Student ID already exists (validation working correctly)

## Contributing

This is a learning project. Feel free to:
- Add new features
- Improve error handling
- Add unit tests
- Enhance UI/UX
- Optimize database queries

## License

This project is for educational purposes.

## Author

Created as part of Cognizant campus project / Java learning journey.

---

**Note**: Remember to update database credentials in `DatabaseConnection.java` before running the application!

