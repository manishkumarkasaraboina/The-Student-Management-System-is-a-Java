package com.sms.service;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service layer for Student business logic.
 * Handles validation and coordinates between DAO and UI layers.
 */
public class StudentService {
    private StudentDAO studentDAO;
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    /**
     * Add a new student with validation.
     * 
     * @param student Student object to be added
     * @return true if added successfully, false otherwise
     */
    public boolean addStudent(Student student) {
        // Validate student data
        String validationError = validateStudent(student);
        if (validationError != null) {
            System.err.println("Validation Error: " + validationError);
            return false;
        }

        // Check if student ID already exists
        if (studentDAO.studentIdExists(student.getStudentId())) {
            System.err.println("Error: Student ID already exists!");
            return false;
        }

        return studentDAO.addStudent(student);
    }

    /**
     * Update an existing student with validation.
     * 
     * @param student Student object with updated information
     * @return true if updated successfully, false otherwise
     */
    public boolean updateStudent(Student student) {
        // Validate student data
        String validationError = validateStudent(student);
        if (validationError != null) {
            System.err.println("Validation Error: " + validationError);
            return false;
        }

        // Check if student exists
        if (!studentDAO.studentIdExists(student.getStudentId())) {
            System.err.println("Error: Student ID does not exist!");
            return false;
        }

        return studentDAO.updateStudent(student);
    }

    /**
     * Delete a student by ID.
     * 
     * @param studentId ID of the student to be deleted
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("Error: Student ID cannot be empty!");
            return false;
        }

        if (!studentDAO.studentIdExists(studentId)) {
            System.err.println("Error: Student ID does not exist!");
            return false;
        }

        return studentDAO.deleteStudent(studentId);
    }

    /**
     * Get all students.
     * 
     * @return List of all Student objects
     */
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    /**
     * Search for a student by ID.
     * 
     * @param studentId ID to search for
     * @return Student object if found, null otherwise
     */
    public Student getStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("Error: Student ID cannot be empty!");
            return null;
        }
        return studentDAO.getStudentById(studentId);
    }

    /**
     * Search for students by name.
     * 
     * @param name Name or partial name to search for
     * @return List of matching Student objects
     */
    public List<Student> searchStudentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Error: Search name cannot be empty!");
            return List.of();
        }
        return studentDAO.searchStudentsByName(name.trim());
    }

    /**
     * Validate student data.
     * 
     * @param student Student object to validate
     * @return Error message if validation fails, null if valid
     */
    private String validateStudent(Student student) {
        if (student == null) {
            return "Student object cannot be null";
        }

        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return "Student ID is mandatory";
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "Name is mandatory";
        }

        if (student.getDateOfBirth() == null) {
            return "Date of Birth is mandatory";
        }

        // Check if date of birth is not in the future
        if (student.getDateOfBirth().isAfter(LocalDate.now())) {
            return "Date of Birth cannot be in the future";
        }

        if (student.getClassName() == null || student.getClassName().trim().isEmpty()) {
            return "Class is mandatory";
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return "Email is mandatory";
        }

        // Validate email format
        if (!Pattern.matches(EMAIL_PATTERN, student.getEmail())) {
            return "Invalid email format";
        }

        if (student.getAddress() == null || student.getAddress().trim().isEmpty()) {
            return "Address is mandatory";
        }

        return null; // Validation passed
    }

    /**
     * Parse date string to LocalDate.
     * 
     * @param dateString Date string in yyyy-MM-dd format
     * @return LocalDate object
     * @throws DateTimeParseException if date format is invalid
     */
    public LocalDate parseDate(String dateString) throws DateTimeParseException {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    /**
     * Get date formatter for display purposes.
     * 
     * @return DateTimeFormatter instance
     */
    public DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }
}

