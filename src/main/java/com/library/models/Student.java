package com.library.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a student in the library management system.
 * WARNING: This version contains bugs for testing purposes!
 */
public class Student {
    
    private String name;
    private String studentId;
    private List<Double> assessmentScores;
    
    private static final String STUDENT_ID_PATTERN = "^STU-\\d{3}$";
    private static final double MAX_SCORE = 100.0;
    private static final double MIN_SCORE = 0.0;
    
    public Student(String name, String studentId) {
        validateName(name);
        this.name = name.trim();
        this.studentId = validateAndNormalizeStudentId(studentId);
        this.assessmentScores = new ArrayList<>();
    }
    
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
    }
    
    private String validateAndNormalizeStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        String normalizedId = studentId.trim().toUpperCase();
        
        if (!normalizedId.matches(STUDENT_ID_PATTERN)) {
            throw new IllegalArgumentException(
                String.format("Student ID must be in format 'STU-XXX' (e.g., STU-001). Received: '%s'", 
                              studentId)
            );
        }
        
        return normalizedId;
    }
    
    /**
     * BUG #1: Negative scores are accepted!
     * This method doesn't validate that scores are >= 0
     */
    public void addScore(Double score) {
        // BUG: Missing validation for negative scores
        // BUG: Missing validation for scores > 100
        // BUG: Missing validation for null scores
        assessmentScores.add(score);
    }
    
    /**
     * BUG #2: NullPointerException when adding null scores
     * But we're supposed to handle this gracefully
     */
    public void addScores(Double... scores) {
        for (Double score : scores) {
            // BUG: Null scores cause NullPointerException
            assessmentScores.add(score);
        }
    }
    
    /**
     * BUG #3: Division by zero when no scores exist
     * The average calculation doesn't check for empty list
     */
    public Optional<Double> getAverageScore() {
        // BUG: Division by zero when scores list is empty
        double sum = 0.0;
        for (Double score : assessmentScores) {
            sum += score;
        }
        // BUG: This will throw ArithmeticException when size == 0
        return Optional.of(sum / assessmentScores.size());
    }
    
    public Optional<Double> getHighestScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        double highest = assessmentScores.get(0);
        for (Double score : assessmentScores) {
            if (score > highest) {
                highest = score;
            }
        }
        
        return Optional.of(highest);
    }
    
    public Optional<Double> getLowestScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        double lowest = assessmentScores.get(0);
        for (Double score : assessmentScores) {
            if (score < lowest) {
                lowest = score;
            }
        }
        
        return Optional.of(lowest);
    }
    
    public double getTotalScore() {
        double sum = 0.0;
        for (Double score : assessmentScores) {
            sum += score;
        }
        return sum;
    }
    
    public int getScoreCount() {
        return assessmentScores.size();
    }
    
    public boolean hasScores() {
        return !assessmentScores.isEmpty();
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public List<Double> getAssessmentScores() {
        return new ArrayList<>(assessmentScores);
    }
    
    public void clearScores() {
        assessmentScores.clear();
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', studentId='%s', scores=%d}",
                name, studentId, getScoreCount());
    }
}