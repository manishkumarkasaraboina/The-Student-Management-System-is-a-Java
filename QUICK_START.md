# 🚀 Quick Start Guide (5 Minutes)

If you're in a hurry, follow these minimal steps:

## Prerequisites Check
- ✅ Java installed? Run: `java -version`
- ✅ MySQL installed? Run: `mysql --version`

## Step 1: Setup Database (2 minutes)

1. Open MySQL Workbench
2. Connect to MySQL (enter your root password)
3. Open file: `src/main/resources/database/schema.sql`
4. Copy ALL content and paste into MySQL Workbench
5. Click Execute (⚡ button)
6. Done! Database is ready.

## Step 2: Configure Connection (30 seconds)

1. Open: `src/main/java/com/sms/util/DatabaseConnection.java`
2. Change line 11: `DB_PASSWORD = "your_mysql_password"`
3. Save file

## Step 3: Download JDBC Driver (1 minute)

1. Go to: https://dev.mysql.com/downloads/connector/j/
2. Download ZIP file (Platform Independent)
3. Extract and find the JAR file (e.g., `mysql-connector-j-8.0.33.jar`)
4. Create `lib` folder in project root
5. Copy JAR file to `lib` folder

## Step 4: Run (1 minute)

### Option A: Using Batch Script (Easiest)
1. Double-click `run.bat`
2. Done! Application should start.

### Option B: Using Command Line
1. Open Command Prompt in project folder
2. Run:
   ```bash
   javac -cp "lib\mysql-connector-j-8.0.33.jar" -d out src\main\java\com\sms\model\Student.java src\main\java\com\sms\util\DatabaseConnection.java src\main\java\com\sms\dao\StudentDAO.java src\main\java\com\sms\service\StudentService.java src\main\java\com\sms\main\Main.java
   ```
3. Then run:
   ```bash
   java -cp "out;lib\mysql-connector-j-8.0.33.jar" com.sms.main.Main
   ```

## Step 5: Test

1. Type `4` to list all students
2. You should see 3 sample students
3. Type `7` to exit

---

**That's it!** 🎉

For detailed instructions, see `SETUP_GUIDE.md`

