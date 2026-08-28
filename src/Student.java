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
        // FIX: Added comprehensive validation
        validateScore(score);
        assessmentScores.add(score);
    }
    
    /**
     * Validates that the score is not null and is within acceptable range.
     * 
     * @param score The score to validate
     * @throws IllegalArgumentException if score is outside range [0, 100]
     * @throws NullPointerException if score is null
     */
    private void validateScore(Double score) {
        // Check for null
        if (score == null) {
            throw new NullPointerException("Score cannot be null");
        }
        
        // Check for NaN or Infinite
        if (score.isNaN() || score.isInfinite()) {
            throw new IllegalArgumentException("Score must be a finite number");
        }
        
        // Check range (0 to 100)
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(
                String.format("Score must be between %.1f and %.1f. Received: %.2f", 
                              MIN_SCORE, MAX_SCORE, score)
            );
        }
    }
    
    /**
     * Adds multiple scores at once.
     * 
     * @param scores Array of scores to add
     * @throws IllegalArgumentException if any score is invalid
     */
    public void addScores(Double... scores) {
        for (Double score : scores) {
            addScore(score);
        }
    }
    
    public Optional<Double> getAverageScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(assessmentScores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0));
    }
    
    public Optional<Double> getHighestScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(assessmentScores.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0));
    }
    
    public Optional<Double> getLowestScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(assessmentScores.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0));
    }
    
    public double getTotalScore() {
        return assessmentScores.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    
    public int getScoreCount() {
        return assessmentScores.size();
    }
    
    public boolean hasScores() {
        return !assessmentScores.isEmpty();
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
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
        return String.format("Student{name='%s', studentId='%s', scores=%d, avg=%.2f}",
                name, studentId, getScoreCount(), 
                getAverageScore().orElse(0.0));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }
    
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}