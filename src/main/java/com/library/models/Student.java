package com.library.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a student in the library management system.
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
     * Adds a score to the student's record with comprehensive validation.
     * 
     * @param score The score to add (must be between 0 and 100 inclusive)
     * @throws IllegalArgumentException if score is negative or > 100
     * @throws NullPointerException if score is null
     */
    public void addScore(Double score) {
        validateScore(score);
        assessmentScores.add(score);
    }
    
    /**
     * Validates that the score is not null and is within acceptable range.
     */
    private void validateScore(Double score) {
        if (score == null) {
            throw new NullPointerException("Score cannot be null");
        }
        
        if (score.isNaN() || score.isInfinite()) {
            throw new IllegalArgumentException("Score must be a finite number");
        }
        
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(
                String.format("Score must be between %.1f and %.1f. Received: %.2f", 
                              MIN_SCORE, MAX_SCORE, score)
            );
        }
    }
    
    public void addScores(Double... scores) {
        for (Double score : scores) {
            addScore(score);
        }
    }
    
    /**
     * FIXED: Calculates the average of all assessment scores.
     * Now properly handles empty score list.
     * 
     * @return Optional containing the average, or empty if no scores exist
     */
    public Optional<Double> getAverageScore() {
        // FIX: Check for empty list to prevent division by zero
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        double sum = 0.0;
        for (Double score : assessmentScores) {
            sum += score;
        }
        
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