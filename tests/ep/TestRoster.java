package ep;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.models.Student;
import com.library.utils.GradebookUtils;



@DisplayName("Score Count Equivalence Partitioning Tests")
public class TestRoster {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("Liza Lott", "STU-001");
    }
 
    @Test
    @DisplayName("Student with 0 scores should be invalid")
    void testScoreCount_0Scores_ThrowsException() {
        // Student has 0 scores by default
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateScoreCount(student));
    }
  
    
    @Test
    @DisplayName("Student with 3 scores should be valid")
    void testScoreCount_3Scores_Passes() throws Exception {
        student.addScore(85.5);
        student.addScore(90.0);
        student.addScore(78.5);
        
        assertDoesNotThrow(() -> GradebookUtils.validateScoreCount(student));
    }
    
 
    @Test
    @DisplayName("Student with 8 scores should be invalid")
    void testScoreCount_8Scores_ThrowsException() {
        student.addScores(85.5, 90.0, 78.5, 95.5, 88.0, 92.0, 76.0, 84.0);
        
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateScoreCount(student));
    }
}