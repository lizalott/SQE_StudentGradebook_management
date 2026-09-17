package ep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.library.utils.GradebookUtils;


@DisplayName("Letter Grade Equivalence Partitioning Tests")
public class TestLetterGrade {
    
    @ParameterizedTest
    @DisplayName("Valid letter grade classes")
    @CsvSource({
        "45, F",      // Class: 0-59 → F
        "65, D",      // Class: 60-69 → D
        "75, C",      // Class: 70-79 → C
        "85, B",      // Class: 80-89 → B
        "95, A"       // Class: 90-100 → A
    })
    void testLetterGrade_ValidClasses(double score, String expected) {
        assertEquals(expected, GradebookUtils.letterGrade(score));
    }
 
    @ParameterizedTest
    @DisplayName("Invalid letter grade classes throw IllegalArgumentException")
    @ValueSource(doubles = {-10, 150})
    void testLetterGrade_InvalidClasses_ThrowsException(double score) {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.letterGrade(score));
    }
}