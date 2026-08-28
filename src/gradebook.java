package com.library.services;

import com.library.models.Student;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Gradebook service for managing student grades.
 */
public class Gradebook {
    
    private final Map<String, Student> students;
    private String courseName;
    private String semester;
    
    public Gradebook() {
        this.students = new HashMap<>();
        this.courseName = "Library Science 101";
        this.semester = "Fall 2026";
    }
    
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        String key = student.getStudentId();
        
        if (students.containsKey(key)) {
            throw new IllegalArgumentException(
                String.format("Student with ID %s already exists", key)
            );
        }
        
        students.put(key, student);
        System.out.printf("Added student: %s (ID: %s)%n", 
                         student.getName(), student.getStudentId());
    }
    
    public Optional<Student> findStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }
    
    /**
     * Calculates the average score for a student.
     * 
     * @param studentId The ID of the student
     * @return Optional containing the average, or empty if student has no scores
     * @throws IllegalArgumentException if student not found
     */
    public Optional<Double> calculateAverage(String studentId) {
        // FIX: Handle case where student doesn't exist
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        // FIX: Handle case where student has no scores
        List<Double> scores = student.getAssessmentScores();
        if (scores.isEmpty()) {
            return Optional.empty(); // Return empty instead of dividing by zero
        }
        
        double sum = 0.0;
        for (Double score : scores) {
            sum += score;
        }
        
        return Optional.of(sum / scores.size());
    }
    
    /**
     * Calculates the average score for a student, returning a default value.
     * 
     * @param studentId The ID of the student
     * @param defaultValue The value to return if student has no scores
     * @return The average score or the default value
     */
    public double calculateAverageOrDefault(String studentId, double defaultValue) {
        return calculateAverage(studentId).orElse(defaultValue);
    }
    
    public Map<String, Student> getStudents() {
        return new HashMap<>(students);
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
}