-- Student Management System Database Schema
-- Database: student_management

-- Create database (run this first if database doesn't exist)
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    class VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_name (name),
    INDEX idx_class (class)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample data (optional - for testing)
INSERT INTO students (student_id, name, date_of_birth, class, email, address) VALUES
('STU001', 'John Doe', '2000-05-15', 'Class 10A', 'john.doe@example.com', '123 Main Street, City'),
('STU002', 'Jane Smith', '2001-08-22', 'Class 10B', 'jane.smith@example.com', '456 Oak Avenue, Town'),
('STU003', 'Bob Johnson', '2000-12-10', 'Class 11A', 'bob.johnson@example.com', '789 Pine Road, Village');

