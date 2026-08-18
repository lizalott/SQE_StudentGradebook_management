// src/Student.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Student module for managing student records in the library system.
 * 
 * This class handles student information and academic scores with proper validation.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Student {
    
    /**
     * The student's full name
     */
    private String name;
    
    /**
     * Unique identifier for the student (format: STU-XXX)
     */
    private String studentId;
    
    /**
     * List of scores for the student
     */
    private List<Double> scores;
    
    /**
     * Initialize a new Student instance.
     * 
     * @param name Student's full name (non-empty string)
     * @param studentId Unique student identifier (format: STU-XXX)
     * @throws IllegalArgumentException If name is empty or studentId format is invalid
     */
    public Student(String name, String studentId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (studentId == null || !studentId.startsWith("STU-")) {
            throw new IllegalArgumentException("Student ID must be in format STU-XXX");
        }
        
        this.name = name.trim();
        this.studentId = studentId;
        this.scores = new ArrayList<>();
    }
    
    /**
     * Add a score to the student's record with validation.
     * 
     * This method validates that the score is non-negative before adding
     * it to the student's scores list. Scores are stored as doubles for
     * precision.
     * 
     * @param score The score to add (must be >= 0)
     * @throws IllegalArgumentException If score is negative
     */
    public void addScore(double score) {
        // Validate score is non-negative
        if (score < 0) {
            throw new IllegalArgumentException(
                String.format("Score cannot be negative. Received: %.1f", score)
            );
        }
        
        // Add the valid score
        this.scores.add(score);
    }
    
    /**
     * Calculate the average of all scores.
     * 
     * @return The average score as a Double, or null if no scores exist
     */
    public Double getAverageScore() {
        if (scores.isEmpty()) {
            return null;
        }
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }
    
    /**
     * Get the highest score.
     * 
     * @return The highest score as a Double, or null if no scores exist
     */
    public Double getHighestScore() {
        if (scores.isEmpty()) {
            return null;
        }
        return Collections.max(scores);
    }
    
    /**
     * Get the lowest score.
     * 
     * @return The lowest score as a Double, or null if no scores exist
     */
    public Double getLowestScore() {
        if (scores.isEmpty()) {
            return null;
        }
        return Collections.min(scores);
    }
    
    /**
     * Get the total number of scores.
     * 
     * @return The count of scores
     */
    public int getScoreCount() {
        return scores.size();
    }
    
    /**
     * Check if the student has any scores.
     * 
     * @return true if scores exist, false otherwise
     */
    public boolean hasScores() {
        return !scores.isEmpty();
    }
    
    /**
     * Get the student's name.
     * 
     * @return The student's full name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the student's ID.
     * 
     * @return The student's unique identifier
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Get an unmodifiable list of scores.
     * 
     * @return An unmodifiable view of the scores list
     */
    public List<Double> getScores() {
        return Collections.unmodifiableList(scores);
    }
    
    /**
     * Add multiple scores at once.
     * 
     * @param newScores List of scores to add
     * @throws IllegalArgumentException If any score is negative
     */
    public void addScores(List<Double> newScores) {
        if (newScores == null) {
            return;
        }
        for (Double score : newScores) {
            if (score == null) {
                continue;
            }
            addScore(score);
        }
    }
    
    /**
     * Clear all scores from the student's record.
     */
    public void clearScores() {
        scores.clear();
    }
    
    /**
     * Returns a string representation of the student.
     * 
     * @return A formatted string containing student information
     */
    @Override
    public String toString() {
        Double average = getAverageScore();
        String avgStr = (average != null) ? String.format("%.2f", average) : "No scores";
        return String.format(
            "Student{name='%s', studentId='%s', scores=%s, average=%s}",
            name, studentId, scores, avgStr
        );
    }
    
    /**
     * Example usage of the Student class.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example usage
        try {
            Student student = new Student("John Doe", "STU-001");
            student.addScore(85.5);
            student.addScore(92.0);
            
            System.out.println("Student: " + student.getName());
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Average score: " + student.getAverageScore());
            System.out.println("Highest score: " + student.getHighestScore());
            System.out.println("Lowest score: " + student.getLowestScore());
            System.out.println("Full details: " + student);
            
            // This will throw an exception
            // student.addScore(-10);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}