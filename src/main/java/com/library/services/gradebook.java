package com.library.services;

import com.library.models.Student;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        
        // Using composite key for uniqueness
        String key = String.format("%s:%s", student.getStudentId(), student.getName());
        
        if (students.containsKey(key)) {
            throw new IllegalArgumentException(
                String.format("Student with ID %s already exists", student.getStudentId())
            );
        }
        
        students.put(key, student);
        System.out.printf("Added student: %s (ID: %s)%n", 
                         student.getName(), student.getStudentId());
    }
    
    public Optional<Student> findStudent(String studentId) {
        // Bug: This doesn't work properly - searches by key not studentId
        return Optional.ofNullable(students.get(studentId));
    }
    
    public double calculateAverage(String studentId) {
        // BUG #1: No guard against empty score list
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        
        List<Double> scores = student.getAssessmentScores();
        double sum = 0.0;
        for (Double score : scores) {
            sum += score;
        }
        return sum / scores.size();     
    }
    
    public Map<String, Student> getStudents() {
        return new HashMap<>(students);
    }
}