@echo off
echo ========================================
echo   Student Management System - Launcher
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH!
    echo Please install Java JDK first.
    pause
    exit /b 1
)

REM Check if MySQL JDBC driver exists
if not exist "lib\mysql-connector-j-8.0.33.jar" (
    echo WARNING: MySQL JDBC driver not found!
    echo Expected location: lib\mysql-connector-j-8.0.33.jar
    echo.
    echo Please download MySQL Connector/J from:
    echo https://dev.mysql.com/downloads/connector/j/
    echo.
    echo Extract the JAR file and place it in the 'lib' folder.
    pause
    exit /b 1
)

echo Step 1: Compiling Java files...
echo.

REM Compile Java files
javac -cp "lib\mysql-connector-j-8.0.33.jar" -d out src\main\java\com\sms\model\Student.java src\main\java\com\sms\util\DatabaseConnection.java src\main\java\com\sms\dao\StudentDAO.java src\main\java\com\sms\service\StudentService.java src\main\java\com\sms\main\Main.java

if errorlevel 1 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Step 2: Running the application...
echo.
echo ========================================
echo.

REM Run the application
java -cp "out;lib\mysql-connector-j-8.0.33.jar" com.sms.main.Main

if errorlevel 1 (
    echo.
    echo ERROR: Application failed to run!
    echo Please check:
    echo 1. MySQL server is running
    echo 2. Database 'student_management' exists
    echo 3. Database credentials in DatabaseConnection.java are correct
    pause
    exit /b 1
)

pause

