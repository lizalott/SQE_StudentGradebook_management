package com.library.services;

import com.library.models.Student;
import java.io.*;
import java.util.*;

/**
 * Service for handling data persistence operations for students.
 * Provides methods to save, load, and manage student data
 * using file-based storage with in-memory caching.
 * 
 * @author Your Name
 * @version 1.0
 */
public class PersistenceService {
    
    private static final String DATA_FILE = "students_data.ser";
    private Map<String, Student> studentCache;
    
    /**
     * Constructs a new PersistenceService and loads existing data.
     */
    public PersistenceService() {
        this.studentCache = new HashMap<>();
        loadAllStudents();
        System.out.println("PersistenceService initialized with " + 
                          studentCache.size() + " students");
    }
    
    /**
     * Saves a student to persistent storage and updates cache.
     * 
     * @param student The student to save (must not be null)
     * @throws IllegalArgumentException if student is null
     */
    public void saveStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // Add to cache
        studentCache.put(student.getStudentId(), student);
        
        try {
            // Serialize entire cache to file
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(studentCache);
                System.out.println("Student saved successfully: " + student.getName() + 
                                 " (ID: " + student.getStudentId() + ")");
            }
        } catch (IOException e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Loads a student from persistent storage by ID.
     * 
     * @param studentId The ID of the student to load
     * @return The student if found, null otherwise
     * @throws IllegalArgumentException if studentId is null or empty
     */
    public Student loadStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        // Check cache first
        if (studentCache.containsKey(studentId)) {
            System.out.println("Student found in cache: " + studentId);
            return studentCache.get(studentId);
        }
        
        // Try loading from file
        try {
            // Reload entire cache from file
            loadAllStudents();
            
            if (studentCache.containsKey(studentId)) {
                System.out.println("Student loaded: " + 
                                 studentCache.get(studentId).getName());
                return studentCache.get(studentId);
            }
        } catch (Exception e) {
            System.err.println("Error loading student: " + e.getMessage());
        }
        
        System.out.println("Student not found: " + studentId);
        return null;
    }
    
    /**
     * Loads all students from the data file into cache.
     */
    @SuppressWarnings("unchecked")
    private void loadAllStudents() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(file))) {
                    studentCache = (Map<String, Student>) ois.readObject();
                    System.out.println("Loaded " + studentCache.size() + 
                                     " students from file");
                }
            } else {
                System.out.println("No data file found. Starting with empty cache.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }
    
    /**
     * Gets all cached students.
     * 
     * @return A copy of the student cache
     */
    public Map<String, Student> getAllStudents() {
        return new HashMap<>(studentCache);
    }
    
    /**
     * Deletes a student from storage by ID.
     * 
     * @param studentId The ID of the student to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        if (studentCache.containsKey(studentId)) {
            studentCache.remove(studentId);
            saveAllStudents();
            System.out.println("Student deleted: " + studentId);
            return true;
        }
        
        System.out.println("Student not found for deletion: " + studentId);
        return false;
    }
    
    /**
     * Saves all cached students to file.
     */
    private void saveAllStudents() {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(studentCache);
                System.out.println("Saved " + studentCache.size() + 
                                 " students to file");
            }
        } catch (IOException e) {
            System.err.println("Error saving all students: " + e.getMessage());
        }
    }
}