package com.sms.model;

import java.time.LocalDate;

/**
 * Student entity class representing a student record in the system.
 */
public class Student {
    private String studentId;
    private String name;
    private LocalDate dateOfBirth;
    private String className;
    private String email;
    private String address;

    // Default constructor
    public Student() {
    }

    // Constructor with all fields
    public Student(String studentId, String name, LocalDate dateOfBirth, 
                   String className, String email, String address) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.className = className;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
            "Student ID: %s | Name: %s | DOB: %s | Class: %s | Email: %s | Address: %s",
            studentId, name, dateOfBirth, className, email, address
        );
    }
}

