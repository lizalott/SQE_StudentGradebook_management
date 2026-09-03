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
    
    public Gradebook(String courseName, String semester) {
        this.students = new HashMap<>();
        this.courseName = courseName;
        this.semester = semester;
    }
    
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        String key = student.getStudentId();
        
        if (students.containsKey(key)) {
            throw new IllegalArgumentException(
                String.format("Student with ID %s already exists in the gradebook", key)
            );
        }
        
        students.put(key, student);
        System.out.printf("Added student: %s (ID: %s)%n", 
                         student.getName(), student.getStudentId());
    }
    
    public Optional<Student> findStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(students.get(studentId.trim()));
    }
    
    public boolean removeStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        
        if (students.containsKey(studentId.trim())) {
            students.remove(studentId.trim());
            System.out.printf("Removed student with ID: %s%n", studentId);
            return true;
        }
        return false;
    }
    
    public Optional<Double> calculateAverage(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getAverageScore();
    }
    
    public double calculateAverageOrDefault(String studentId, double defaultValue) {
        return calculateAverage(studentId).orElse(defaultValue);
    }
    
    public Optional<Double> getHighestScore(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getHighestScore();
    }
    
    public int getStudentCount() {
        return students.size();
    }
    
    public boolean isStudentIdUnique(String studentId) {
        return !students.containsKey(studentId);
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