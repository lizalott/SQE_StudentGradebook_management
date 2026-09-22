package bva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.models.Student;
import com.library.utils.GradebookUtils;

@DisplayName("Score Count - Boundary Value Analysis (with Fixtures)")
public class TestRosterBVA {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        // Arrange: Fresh student for each test
        student = new Student("John Doe", "STU-001");
    }
    
    @Test
    @DisplayName("BVA: 0 scores (value-1) - should fail")
    void testBoundary_0Scores_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateScoreCount(student));
    }
    
    @Test
    @DisplayName("BVA: 1 score (value) - should pass")
    void testBoundary_1Score_Passes() {
        // Arrange
        student.addScore(85.0);
        
        // Act & Assert
        assertDoesNotThrow(() -> GradebookUtils.validateScoreCount(student));
    }
    
    @Test
    @DisplayName("BVA: 2 scores (value+1) - should pass")
    void testBoundary_2Scores_Passes() {
        student.addScore(85.0);
        student.addScore(90.0);
        assertDoesNotThrow(() -> GradebookUtils.validateScoreCount(student));
    }
    
    @Test
    @DisplayName("BVA: 5 scores (value-1) - should pass")
    void testBoundary_5Scores_Passes() {
        student.addScores(85.0, 90.0, 78.0, 92.0, 88.0);
        assertDoesNotThrow(() -> GradebookUtils.validateScoreCount(student));
    }
    
    @Test
    @DisplayName("BVA: 6 scores (value) - should pass")
    void testBoundary_6Scores_Passes() {
        student.addScores(85.0, 90.0, 78.0, 92.0, 88.0, 95.0);
        assertDoesNotThrow(() -> GradebookUtils.validateScoreCount(student));
    }
    
    @Test
    @DisplayName("BVA: 7 scores (value+1) - should fail")
    void testBoundary_7Scores_ThrowsException() {
        student.addScores(85.0, 90.0, 78.0, 92.0, 88.0, 95.0, 82.0);
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateScoreCount(student));
    }
}