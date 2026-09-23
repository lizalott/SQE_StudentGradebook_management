package students;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.library.models.Student;

@DisplayName("Student.addScore() - Parametrized Edge Cases")
public class TestAddScoreParametrized {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("Test Student", "STU-001");
    }
    
  
    @ParameterizedTest(name = "[{index}] Score {0} should be accepted")
    @DisplayName("Valid scores are accepted")
    @ValueSource(doubles = {0.0, 0.1, 25.0, 50.0, 75.0, 99.9, 100.0})
    void testValidScores_Accepted(double score) {
        // Act
        student.addScore(score);
        
        // Assert
        assertEquals(1, student.getScoreCount());
        assertTrue(student.getAssessmentScores().contains(score));
    }
    
   
    @ParameterizedTest(name = "[{index}] Negative score {0} should throw")
    @DisplayName("Negative scores are rejected")
    @ValueSource(doubles = {-0.1, -1.0, -50.0, -100.0, -999.0})
    void testNegativeScores_ThrowsException(double score) {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> student.addScore(score)
        );
        assertTrue(ex.getMessage().contains("between 0.0 and 100.0"));
    }

    @ParameterizedTest(name = "[{index}] Score {0} > 100 should throw")
    @DisplayName("Scores above 100 are rejected")
    @ValueSource(doubles = {100.1, 101.0, 150.0, 1000.0})
    void testScoresAbove100_ThrowsException(double score) {
        assertThrows(IllegalArgumentException.class, 
            () -> student.addScore(score));
    }
    

    @ParameterizedTest(name = "[{index}] Null score should throw")
    @DisplayName("Null score throws NullPointerException")
    @ValueSource(strings = {"null"})
    void testNullScore_ThrowsException(String nullMarker) {
        assertThrows(NullPointerException.class, 
            () -> student.addScore(null));
    }
  
    @ParameterizedTest(name = "[{index}] {0} should throw")
    @DisplayName("NaN and Infinity throw exception")
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity"
    })
    void testNonFiniteScores_ThrowsException(String value) {
        double score = Double.parseDouble(value);
        assertThrows(IllegalArgumentException.class, 
            () -> student.addScore(score));
    }
}