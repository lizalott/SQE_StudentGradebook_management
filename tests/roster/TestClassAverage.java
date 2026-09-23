package roster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.models.Student;
import com.library.services.Roster;

import fixtures.TestFixtures;

@DisplayName("Roster.classAverage() Tests")
public class TestClassAverage {
    
    private Roster roster;
    
    @BeforeEach
    void setUp() {
        roster = new Roster();
    }
    
    @Test
    @DisplayName("Empty roster returns 0.0")
    void testClassAverage_EmptyRoster_ReturnsZero() {
        // Arrange: Empty roster from setUp
        
        // Act
        double average = roster.classAverage();
        
        // Assert
        assertEquals(0.0, average, 0.001);
    }
    
    @Test
    @DisplayName("Single student returns their average")
    void testClassAverage_SingleStudent_ReturnsStudentAverage() {
        // Arrange
        Student student = TestFixtures.createStudentWithScores(
            "STU-001", "Ali Khan", 80.0, 90.0, 100.0
        );
        roster.addStudent(student);
        
        // Act
        double average = roster.classAverage();
        
        // Assert
        assertEquals(90.0, average, 0.001);
    }
    
    @Test
    @DisplayName("Multiple students returns class average")
    void testClassAverage_MultipleStudents_ReturnsClassAverage() {
        // Arrange: Use fixture
        roster = TestFixtures.createPopulatedRoster();
        // Ali: (80+90)/2 = 85
        // Sara: 70
        // Expected class average: (85 + 70) / 2 = 77.5
        
        // Act
        double average = roster.classAverage();
        
        // Assert
        assertEquals(77.5, average, 0.001);
    }
    
    
    @Test
    @DisplayName("Students with no scores don't affect average")
    void testClassAverage_StudentsWithNoScores_Ignored() {
        // Arrange
        Student s1 = TestFixtures.createStudentWithScores("STU-001", "Ali", 80.0);
        Student s2 = new Student("Sara", "STU-002");  // No scores
        roster.addStudent(s1);
        roster.addStudent(s2);
        
        // Act
        double average = roster.classAverage();
        
        // Assert
        assertEquals(80.0, average, 0.001);
    }
}

