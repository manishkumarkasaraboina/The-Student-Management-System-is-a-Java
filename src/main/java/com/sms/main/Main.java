package com.sms.main;

import com.sms.model.Student;
import com.sms.service.StudentService;
import com.sms.util.DatabaseConnection;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for Student Management System.
 * Provides console-based menu-driven interface.
 */
public class Main {
    private static StudentService studentService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Test database connection
        if (!DatabaseConnection.testConnection()) {
            System.err.println("Failed to connect to database. Please check your database configuration.");
            System.exit(1);
        }

        studentService = new StudentService();
        scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   Student Management System (SMS)");
        System.out.println("========================================");
        System.out.println();

        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    listAllStudents();
                    break;
                case 5:
                    searchStudentById();
                    break;
                case 6:
                    searchStudentByName();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Student Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        // Close resources
        scanner.close();
        DatabaseConnection.closeConnection();
    }

    /**
     * Display the main menu.
     */
    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add New Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. List All Students");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Search Student by Name");
        System.out.println("7. Exit");
        System.out.println("===============================");
        System.out.print("Enter your choice: ");
    }

    /**
     * Get user's menu choice.
     * 
     * @return User's choice as integer
     */
    private static int getChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Add a new student.
     */
    private static void addStudent() {
        System.out.println("\n========== ADD NEW STUDENT ==========");
        
        Student student = new Student();
        
        System.out.print("Enter Student ID: ");
        student.setStudentId(scanner.nextLine().trim());
        
        System.out.print("Enter Name: ");
        student.setName(scanner.nextLine().trim());
        
        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dobString = scanner.nextLine().trim();
        try {
            LocalDate dob = studentService.parseDate(dobString);
            student.setDateOfBirth(dob);
        } catch (Exception e) {
            System.err.println("Invalid date format! Please use yyyy-MM-dd format.");
            return;
        }
        
        System.out.print("Enter Class: ");
        student.setClassName(scanner.nextLine().trim());
        
        System.out.print("Enter Email: ");
        student.setEmail(scanner.nextLine().trim());
        
        System.out.print("Enter Address: ");
        student.setAddress(scanner.nextLine().trim());
        
        if (studentService.addStudent(student)) {
            System.out.println("\n✓ Student added successfully!");
        } else {
            System.out.println("\n✗ Failed to add student. Please check the error messages above.");
        }
    }

    /**
     * Update an existing student.
     */
    private static void updateStudent() {
        System.out.println("\n========== UPDATE STUDENT ==========");
        
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();
        
        Student existingStudent = studentService.getStudentById(studentId);
        if (existingStudent == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\nCurrent Student Information:");
        System.out.println(existingStudent);
        System.out.println("\nEnter new information (press Enter to keep current value):");
        
        Student updatedStudent = new Student();
        updatedStudent.setStudentId(studentId);
        
        System.out.print("Enter Name [" + existingStudent.getName() + "]: ");
        String name = scanner.nextLine().trim();
        updatedStudent.setName(name.isEmpty() ? existingStudent.getName() : name);
        
        System.out.print("Enter Date of Birth (yyyy-MM-dd) [" + existingStudent.getDateOfBirth() + "]: ");
        String dobString = scanner.nextLine().trim();
        try {
            LocalDate dob = dobString.isEmpty() 
                ? existingStudent.getDateOfBirth() 
                : studentService.parseDate(dobString);
            updatedStudent.setDateOfBirth(dob);
        } catch (Exception e) {
            System.err.println("Invalid date format! Keeping current date.");
            updatedStudent.setDateOfBirth(existingStudent.getDateOfBirth());
        }
        
        System.out.print("Enter Class [" + existingStudent.getClassName() + "]: ");
        String className = scanner.nextLine().trim();
        updatedStudent.setClassName(className.isEmpty() ? existingStudent.getClassName() : className);
        
        System.out.print("Enter Email [" + existingStudent.getEmail() + "]: ");
        String email = scanner.nextLine().trim();
        updatedStudent.setEmail(email.isEmpty() ? existingStudent.getEmail() : email);
        
        System.out.print("Enter Address [" + existingStudent.getAddress() + "]: ");
        String address = scanner.nextLine().trim();
        updatedStudent.setAddress(address.isEmpty() ? existingStudent.getAddress() : address);
        
        if (studentService.updateStudent(updatedStudent)) {
            System.out.println("\n✓ Student updated successfully!");
        } else {
            System.out.println("\n✗ Failed to update student. Please check the error messages above.");
        }
    }

    /**
     * Delete a student.
     */
    private static void deleteStudent() {
        System.out.println("\n========== DELETE STUDENT ==========");
        
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\nStudent to be deleted:");
        System.out.println(student);
        System.out.print("\nAre you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("yes") || confirmation.equals("y")) {
            if (studentService.deleteStudent(studentId)) {
                System.out.println("\n✓ Student deleted successfully!");
            } else {
                System.out.println("\n✗ Failed to delete student.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    /**
     * List all students.
     */
    private static void listAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");
        
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            System.out.println(String.format("%-12s %-25s %-12s %-15s %-30s %-30s", 
                "Student ID", "Name", "DOB", "Class", "Email", "Address"));
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            
            for (Student student : students) {
                System.out.println(String.format("%-12s %-25s %-12s %-15s %-30s %-30s",
                    student.getStudentId(),
                    student.getName(),
                    student.getDateOfBirth(),
                    student.getClassName(),
                    student.getEmail(),
                    student.getAddress()
                ));
            }
            
            System.out.println("\nTotal students: " + students.size());
        }
    }

    /**
     * Search for a student by ID.
     */
    private static void searchStudentById() {
        System.out.println("\n========== SEARCH STUDENT BY ID ==========");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = studentService.getStudentById(studentId);
        
        if (student != null) {
            System.out.println("\nStudent Found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    /**
     * Search for students by name.
     */
    private static void searchStudentByName() {
        System.out.println("\n========== SEARCH STUDENT BY NAME ==========");
        
        System.out.print("Enter Student Name (or partial name): ");
        String name = scanner.nextLine().trim();
        
        List<Student> students = studentService.searchStudentsByName(name);
        
        if (students.isEmpty()) {
            System.out.println("No students found matching the search criteria.");
        } else {
            System.out.println("\nSearch Results:");
            System.out.println(String.format("%-12s %-25s %-12s %-15s %-30s %-30s", 
                "Student ID", "Name", "DOB", "Class", "Email", "Address"));
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            
            for (Student student : students) {
                System.out.println(String.format("%-12s %-25s %-12s %-15s %-30s %-30s",
                    student.getStudentId(),
                    student.getName(),
                    student.getDateOfBirth(),
                    student.getClassName(),
                    student.getEmail(),
                    student.getAddress()
                ));
            }
            
            System.out.println("\nTotal matches: " + students.size());
        }
    }
}

