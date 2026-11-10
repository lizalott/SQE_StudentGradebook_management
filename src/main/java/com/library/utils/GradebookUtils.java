package com.library.utils;

import com.library.models.Student;

/**
 * Utility class for Gradebook operations.
 * Contains functions for letter grade conversion, name validation, etc.
 */
public class GradebookUtils {

    private GradebookUtils() {
        throw new UnsupportedOperationException(
            "Utility class cannot be instantiated");
    }
    private static final double GRADE_A_MIN = 90.0;
    private static final double GRADE_B_MIN = 80.0;
    private static final double GRADE_C_MIN = 70.0;
    private static final double GRADE_D_MIN = 60.0;
    private static final double MAX_SCORE = 100.0;
    private static final double MIN_SCORE = 0.0;
    // Add at top of class
private static final String ERROR_SCORE_RANGE = "Score must be between %.1f and %.1f. Received: %.2f";

  
    /**
     * Converts a numeric score to a letter grade (A-F).
     * 
     * @param score The numeric score (0-100)
     * @return The letter grade as a string
     * @throws IllegalArgumentException if score is outside 0-100 range
     */
    public static String letterGrade(double score) {
        
    

       
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(
                String.format(ERROR_SCORE_RANGE, MIN_SCORE, MAX_SCORE, score)
            );
        }
        
     
            if (score >= GRADE_A_MIN) return "A";
            else if (score >= GRADE_B_MIN) return "B";
            else if (score >= GRADE_C_MIN) return "C";
            else if (score >= GRADE_D_MIN) return "D";
            else return "F";
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