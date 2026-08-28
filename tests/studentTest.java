package com.library.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Student Class Tests")
class StudentTest {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("John Doe", "STU-001");
    }
    
    @Test
    @DisplayName("Should reject negative scores")
    void shouldRejectNegativeScores() {
        // FIX: Test that negative scores are rejected
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> student.addScore(-15.0)
        );
        assertTrue(exception.getMessage().contains("Score must be between 0.0 and 100.0"));
    }
    
    @Test
    @DisplayName("Should reject scores above 100")
    void shouldRejectScoresAbove100() {
        // FIX: Test that scores > 100 are rejected
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> student.addScore(105.0)
        );
        assertTrue(exception.getMessage().contains("Score must be between 0.0 and 100.0"));
    }
    
    @Test
    @DisplayName("Should reject null scores")
    void shouldRejectNullScores() {
        // FIX: Test that null scores are rejected
        assertThrows(
            NullPointerException.class,
            () -> student.addScore(null)
        );
    }
    
    @Test
    @DisplayName("Should reject NaN scores")
    void shouldRejectNaNScores() {
        // FIX: Test that NaN scores are rejected
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> student.addScore(Double.NaN)
        );
        assertTrue(exception.getMessage().contains("must be a finite number"));
    }
    
    @Test
    @DisplayName("Should accept valid scores between 0 and 100")
    void shouldAcceptValidScores() {
        // FIX: Test that valid scores are accepted
        assertDoesNotThrow(() -> student.addScore(0.0));
        assertDoesNotThrow(() -> student.addScore(50.0));
        assertDoesNotThrow(() -> student.addScore(100.0));
        assertEquals(3, student.getScoreCount());
    }
    
    @Test
    @DisplayName("Should add multiple scores correctly")
    void shouldAddMultipleScores() {
        student.addScores(85.5, 92.0, 78.5, 95.5);
        assertEquals(4, student.getScoreCount());
        assertEquals(351.5, student.getTotalScore(), 0.001);
    }
    
    @Test
    @DisplayName("Should calculate average correctly")
    void shouldCalculateAverageCorrectly() {
        student.addScores(80.0, 90.0, 100.0);
        Optional<Double> avg = student.getAverageScore();
        assertTrue(avg.isPresent());
        assertEquals(90.0, avg.get(), 0.001);
    }
    
    @Test
    @DisplayName("Should return empty Optional when calculating average with no scores")
    void shouldReturnEmptyOptionalWhenNoScores() {
        Optional<Double> avg = student.getAverageScore();
        assertTrue(avg.isEmpty());
    }
}