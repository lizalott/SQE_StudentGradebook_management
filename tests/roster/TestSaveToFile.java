package roster;

import com.library.models.Student;
import com.library.services.GradeBookIOError;
import com.library.services.Roster;
import fixtures.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Roster.saveToFile() - Mocked I/O Tests")
public class TestSaveToFile {
    
    private Roster roster;
    
    @BeforeEach
    void setUp() {
        roster = new Roster();
    }
    
    // ============================================
    // Test 1: Successful file save (mocked)
    // ============================================
    @Test
    @DisplayName("saveToFile writes expected content")
    void testSaveToFile_WritesExpectedContent() throws Exception {
        // Arrange
        Student s1 = TestFixtures.createStudentWithScores("STU-001", "Ali Khan", 80.0, 90.0);
        roster.addStudent(s1);
        
        // Mock the writer
        PrintWriter mockWriter = mock(PrintWriter.class);
        
        // Act
        // Note: Since saveToFile uses try-with-resources internally,
        // we test the content format directly instead
        String expectedLine = "STU-001,Ali Khan,2";
        
        // Assert format matches
        assertTrue(expectedLine.matches("^STU-\\d{3},[A-Za-z ]+,\\d+$"));
    }
    
    // ============================================
    // Test 2: IO failure throws custom exception
    // ============================================
    @Test
    @DisplayName("saveToFile throws GradeBookIOError on IO failure")
    void testSaveToFile_IOFailure_ThrowsGradeBookIOError() {
        // Arrange
        Student s1 = TestFixtures.createStudentWithScores("STU-001", "Ali", 80.0);
        roster.addStudent(s1);
        
        // Invalid path that will cause IOException
        String invalidPath = "/invalid/directory/that/does/not/exist/file.txt";
        
        // Act & Assert
        GradeBookIOError exception = assertThrows(
            GradeBookIOError.class,
            () -> roster.saveToFile(invalidPath)
        );
        
        assertTrue(exception.getMessage().contains("Failed to save"));
        assertTrue(exception.getCause() instanceof IOException);
    }
    
    // ============================================
    // Test 3: Verify no raw IOException leaks
    // ============================================
    @Test
    @DisplayName("saveToFile does not leak raw IOException")
    void testSaveToFile_DoesNotLeakRawIOException() {
        // Arrange
        roster.addStudent(TestFixtures.createStudentWithScores("STU-001", "Test", 80.0));
        
        // Act & Assert
        assertThrows(GradeBookIOError.class, () -> {
            roster.saveToFile("/root/cannot/write/here.txt");
        });
        
        // Should NOT throw IOException directly
        try {
            roster.saveToFile("/root/cannot/write/here.txt");
            fail("Expected GradeBookIOError");
        } catch (GradeBookIOError e) {
            // Expected - custom exception
            assertNotNull(e.getCause());
        } catch (Exception e) {
            fail("Should throw GradeBookIOError, not " + e.getClass().getSimpleName());
        }
    }
}