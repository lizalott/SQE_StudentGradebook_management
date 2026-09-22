package com.library.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.library.models.Student;

public class Roster {
    
    private final Map<String, Student> students;
    
    public Roster() {
        this.students = new HashMap<>();
    }
    
   
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.put(student.getStudentId(), student);
    }
    
    /**
     * Gets a student by ID.
     */
    public Optional<Student> getStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }
    

    public Collection<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * Calculates the average of all students' averages.
     * Returns 0.0 if roster is empty.
     */
    public double classAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double total = 0.0;
        int studentsWithScores = 0;
        
        for (Student student : students.values()) {
            Optional<Double> avg = student.getAverageScore();
            if (avg.isPresent()) {
                total += avg.get();
                studentsWithScores++;
            }
        }
        
        if (studentsWithScores == 0) {
            return 0.0;
        }
        
        return total / studentsWithScores;
    }
    
    /**
     * Saves the roster to a file.
     * 
     * @param path The file path to save to
     * @throws GradeBookIOError if saving fails
     */
    public void saveToFile(String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (Student student : students.values()) {
                writer.printf("%s,%s,%d%n", 
                    student.getStudentId(), 
                    student.getName(),
                    student.getScoreCount());
            }
        } catch (IOException e) {
            throw new GradeBookIOError("Failed to save roster to: " + path, e);
        }
    }
}
