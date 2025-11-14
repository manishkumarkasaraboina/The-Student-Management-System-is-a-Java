package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Student entity.
 * Handles all database operations related to students.
 */
public class StudentDAO {
    
    /**
     * Add a new student to the database.
     * 
     * @param student Student object to be added
     * @return true if student is added successfully, false otherwise
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, date_of_birth, class, email, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            pstmt.setString(4, student.getClassName());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getAddress());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Update an existing student record.
     * 
     * @param student Student object with updated information
     * @return true if student is updated successfully, false otherwise
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, date_of_birth = ?, class = ?, " +
                     "email = ?, address = ? WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setDate(2, Date.valueOf(student.getDateOfBirth()));
            pstmt.setString(3, student.getClassName());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getStudentId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete a student from the database by student ID.
     * 
     * @param studentId ID of the student to be deleted
     * @return true if student is deleted successfully, false otherwise
     */
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all students from the database.
     * 
     * @return List of all Student objects
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        
        return students;
    }

    /**
     * Search for a student by student ID.
     * 
     * @param studentId ID of the student to search for
     * @return Student object if found, null otherwise
     */
    public Student getStudentById(String studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStudent(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching student by ID: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Search for students by name (partial match).
     * 
     * @param name Name or partial name to search for
     * @return List of matching Student objects
     */
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? ORDER BY student_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Student student = mapResultSetToStudent(rs);
                    students.add(student);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching students by name: " + e.getMessage());
        }
        
        return students;
    }

    /**
     * Check if a student ID already exists in the database.
     * 
     * @param studentId ID to check
     * @return true if ID exists, false otherwise
     */
    public boolean studentIdExists(String studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking student ID existence: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Helper method to map ResultSet to Student object.
     * 
     * @param rs ResultSet from database query
     * @return Student object
     * @throws SQLException if mapping fails
     */
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("student_id"));
        student.setName(rs.getString("name"));
        
        Date dob = rs.getDate("date_of_birth");
        if (dob != null) {
            student.setDateOfBirth(dob.toLocalDate());
        }
        
        student.setClassName(rs.getString("class"));
        student.setEmail(rs.getString("email"));
        student.setAddress(rs.getString("address"));
        
        return student;
    }
}

