package com.library.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



import com.library.models.Student;

/**
 * Service for handling data persistence operations.
 */
public class PersistenceService {
    
     private static final Logger LOGGER =
            Logger.getLogger(PersistenceService.class.getName());


    private static final String DATA_FILE = "students.dat";
    private Map<String, Student> studentCache;
    
    public PersistenceService() {
        this.studentCache = new HashMap<>();
    }
    
    /**
     * Saves a student to persistent storage.
     */
    public void saveStudent(String student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        try {
            // Save to file with proper serialization
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(student);
                LOGGER.log(Level.INFO, "Student saved successfully: " + student.getName());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving student: " + e.getMessage(), e);
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
                Student student = (Student)  ois. readObject();
                
                // Verify it's the right student
                if (student.getStudentId().equals(studentId)) {
                    LOGGER.log(Level.INFO, "Student loaded: " + student.getName());
                    return student;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading student: " + e.getMessage());
        }
        
        return null;
    }
}