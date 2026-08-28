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
    
    /**
     * Adds a student to the gradebook with duplicate ID validation.
     * 
     * @param student The student to add (must not be null)
     * @throws IllegalArgumentException if student is null or ID already exists
     */
    public void addStudent(Student student) {
        // Validate student is not null
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // FIX: Use studentId as key directly
        String key = student.getStudentId();
        
        // FIX: Check for duplicate ID
        if (students.containsKey(key)) {
            throw new IllegalArgumentException(
                String.format("Student with ID %s already exists in the gradebook", key)
            );
        }
        
        students.put(key, student);
        System.out.printf("Added student: %s (ID: %s)%n", 
                         student.getName(), student.getStudentId());
    }
    
    /**
     * Finds a student by their unique ID.
     * 
     * @param studentId The student ID to search for
     * @return Optional containing the student if found
     */
    public Optional<Student> findStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }
    
    /**
     * Removes a student from the gradebook.
     * 
     * @param studentId The ID of the student to remove
     * @return true if removed, false if not found
     */
    public boolean removeStudent(String studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            System.out.printf("Removed student with ID: %s%n", studentId);
            return true;
        }
        return false;
    }
    
    /**
     * Calculates the average score for a student.
     * 
     * @param studentId The ID of the student
     * @return Optional containing the average, or empty if student has no scores
     * @throws IllegalArgumentException if student not found
     */
    public Optional<Double> calculateAverage(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        List<Double> scores = student.getAssessmentScores();
        if (scores.isEmpty()) {
            return Optional.empty();
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
    
    public int getStudentCount() {
        return students.size();
    }
    
    public boolean isStudentIdUnique(String studentId) {
        return !students.containsKey(studentId);
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