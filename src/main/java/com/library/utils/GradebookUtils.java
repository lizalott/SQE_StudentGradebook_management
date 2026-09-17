package com.library.utils;

import com.library.models.Student;

/**
 * Utility class for Gradebook operations.
 * Contains functions for letter grade conversion, name validation, etc.
 */
public class GradebookUtils {
    
    // ============================================
    // METHOD 1: letterGrade(score)
    // ============================================
    /**
     * Converts a numeric score to a letter grade (A-F).
     * 
     * @param score The numeric score (0-100)
     * @return The letter grade as a string
     * @throws IllegalArgumentException if score is outside 0-100 range
     */
    public static String letterGrade(double score) {
        // Invalid: score < 0
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative: " + score);
        }
        
        // Invalid: score > 100
        if (score > 100) {
            throw new IllegalArgumentException("Score cannot exceed 100: " + score);
        }
        
        // Valid classes
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // ============================================
    // METHOD 2: validateName(name)
    // ============================================
    /**
     * Validates a student name according to business rules.
     * Rules: non-empty, max 50 chars, only letters/spaces/hyphens.
     * 
     * @param name The name to validate
     * @return true if valid
     * @throws IllegalArgumentException if name is invalid
     */
    public static boolean validateName(String name) {
        // Invalid: null or empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        // Invalid: exceeds 50 characters
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name exceeds maximum length of 50 characters");
        }
        
        // Invalid: contains digits or special characters
        if (!name.matches("^[a-zA-Z\\s\\-]+$")) {
            throw new IllegalArgumentException("Name can only contain letters, spaces, and hyphens");
        }
        
        return true;
    }
    
    // ============================================
    // METHOD 3: validateScoreCount(student)
    // ============================================
    /**
     * Validates that a student has between 1 and 6 scores.
     * 
     * @param student The student to validate
     * @return true if valid
     * @throws IllegalArgumentException if score count is invalid
     */
    public static boolean validateScoreCount(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        int scoreCount = student.getScoreCount();
        
        // Invalid: 0 scores
        if (scoreCount == 0) {
            throw new IllegalArgumentException("Student must have at least 1 score");
        }
        
        // Invalid: more than 6 scores
        if (scoreCount > 6) {
            throw new IllegalArgumentException("Student cannot have more than 6 scores");
        }
        
        return true;
    }
}