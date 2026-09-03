import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a student in the library management system.
 * This class manages student information and their assessment scores.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Student {
    
    // Instance variables
    private String name;
    private String studentId;
    private List<Double> assessmentScores;
    
    // Constants
    private static final String STUDENT_ID_PATTERN = "^STU-\\d{3}$";
    private static final double MAX_SCORE = 100.0;
    private static final double MIN_SCORE = 0.0;
    
    /**
     * Constructs a new Student with the specified name and ID.
     * 
     * @param name The student's full name (cannot be null or empty)
     * @param studentId The student's unique identifier (format: STU-XXX)
     * @throws IllegalArgumentException if name is null/empty or ID format is invalid
     */
    public Student(String name, String studentId) {
        validateName(name);
        this.name = name.trim();
        this.studentId = validateAndNormalizeStudentId(studentId);
        this.assessmentScores = new ArrayList<>();
    }
    
    /**
     * Validates that the student name is not null or empty.
     * 
     * @param name The name to validate
     * @throws IllegalArgumentException if name is invalid
     */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
    }
    
    /**
     * Validates and normalizes the student ID.
     * 
     * @param studentId The ID to validate
     * @return The normalized student ID
     * @throws IllegalArgumentException if ID format is invalid
     */
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
    
    /**
     * Calculates the average of all assessment scores.
     * 
     * @return Optional containing the average, or empty if no scores exist
     */
    public Optional<Double> getAverageScore() {
        if (assessmentScores.isEmpty()) {
            return Optional.empty();
        }
        
        double sum = 0.0;
        for (Double score : assessmentScores) {
            sum += score;
        }
        
        return Optional.of(sum / assessmentScores.size());
    }
    
    /**
     * Gets the highest assessment score.
     * 
     * @return Optional containing the highest score, or empty if no scores exist
     */
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
    
    /**
     * Gets the lowest assessment score.
     * 
     * @return Optional containing the lowest score, or empty if no scores exist
     */
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
    
    /**
     * Gets the total sum of all assessment scores.
     * 
     * @return The sum of all scores, or 0.0 if no scores exist
     */
    public double getTotalScore() {
        double sum = 0.0;
        for (Double score : assessmentScores) {
            sum += score;
        }
        return sum;
    }
    
    /**
     * Gets the count of assessment scores.
     * 
     * @return The number of scores recorded
     */
    public int getScoreCount() {
        return assessmentScores.size();
    }
    
    /**
     * Checks if the student has any assessment scores.
     * 
     * @return true if the student has at least one score, false otherwise
     */
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
        return String.format("Student{name='%s', studentId='%s', scores=%d}",
                name, studentId, getScoreCount());
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