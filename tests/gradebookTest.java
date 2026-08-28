package com.library.services;

import com.library.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Gradebook Tests")
class GradebookTest {
    
    private Gradebook gradebook;
    private Student student;
    
    @BeforeEach
    void setUp() {
        gradebook = new Gradebook();
        student = new Student("John Doe", "STU-001");
        gradebook.addStudent(student);
    }
    
    @Test
    @DisplayName("Should return empty Optional when calculating average with no scores")
    void shouldReturnEmptyOptionalWhenNoScores() {
        // FIX: Test that calculateAverage returns empty for no scores
        Optional<Double> avg = gradebook.calculateAverage("STU-001");
        assertTrue(avg.isEmpty());
    }
    
    @Test
    @DisplayName("Should not throw exception when calculating average with no scores")
    void shouldNotThrowExceptionWhenNoScores() {
        // FIX: Test that no exception is thrown for empty scores
        assertDoesNotThrow(() -> gradebook.calculateAverage("STU-001"));
    }
    
    @Test
    @DisplayName("Should calculate average correctly with scores")
    void shouldCalculateAverageCorrectly() {
        student.addScore(85.0);
        student.addScore(90.0);
        student.addScore(95.0);
        
        Optional<Double> avg = gradebook.calculateAverage("STU-001");
        assertTrue(avg.isPresent());
        assertEquals(90.0, avg.get(), 0.001);
    }
    
    @Test
    @DisplayName("Should throw exception when student not found")
    void shouldThrowExceptionWhenStudentNotFound() {
        assertThrows(IllegalArgumentException.class,
            () -> gradebook.calculateAverage("STU-999"));
    }
    
    @Test
    @DisplayName("Should return default value when no scores")
    void shouldReturnDefaultValueWhenNoScores() {
        // FIX: Test the default value method
        double avg = gradebook.calculateAverageOrDefault("STU-001", 0.0);
        assertEquals(0.0, avg, 0.001);
        
        avg = gradebook.calculateAverageOrDefault("STU-001", 100.0);
        assertEquals(100.0, avg, 0.001);
    }
    
    @Test
    @DisplayName("Should return calculated average when default method called with scores")
    void shouldReturnCalculatedAverageWhenScoresExist() {
        student.addScore(85.0);
        student.addScore(90.0);
        student.addScore(95.0);
        
        double avg = gradebook.calculateAverageOrDefault("STU-001", 0.0);
        assertEquals(90.0, avg, 0.001);
    }
}