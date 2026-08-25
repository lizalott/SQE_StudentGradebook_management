package com.library.services;

import com.library.models.Student;
import java.io.*;
import java.util.*;

/**
 * Service for handling data persistence operations.
 */
public class PersistenceService {
    
    private static final String DATA_FILE = "students.dat";
    private Map<String, Student> studentCache;
    
    public PersistenceService() {
        this.studentCache = new HashMap<>();
    }
    
    /**
     * Saves a student to persistent storage.
     */
    public void saveStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        try {
            // Save to file with proper serialization
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(student);
                System.out.println("Student saved successfully: " + student.getName());
            }
        } catch (IOException e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace(); // Added for debugging
        }
    }
    
    /**
     * Loads a student from persistent storage by ID.
     */
    public Student loadStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        try {
            // Load from file
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(DATA_FILE))) {
                Student student = (Student) ois.readObject();
                
                // Verify it's the right student
                if (student.getStudentId().equals(studentId)) {
                    System.out.println("Student loaded: " + student.getName());
                    return student;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading student: " + e.getMessage());
        }
        
        return null;
    }
}