package roster;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.models.Student;
import com.library.services.GradeBookIOError;
import com.library.services.Roster;

import fixtures.TestFixtures;

@DisplayName("Roster.saveToFile() Tests")
class TestSaveToFile {

    private Roster roster;

    @BeforeEach
    void setUp() {
        roster = new Roster();
    }

    @Test
    @DisplayName("saveToFile writes expected content")
    void testSaveToFile_WritesExpectedContent() throws IOException {
        // Arrange
        Path tempFile = Files.createTempFile("save-test", ".txt");
        try {
            roster.addStudent(
                    TestFixtures.createStudentWithScores("STU-001", "Ali", 80.0));

            // Act
            roster.saveToFile(tempFile.toString());

            // Assert
            String actual = Files.readString(tempFile);
            assertNotNull(actual);
            assertTrue(actual.contains("Ali"),
                    "File should contain the student name. Actual: " + actual);
            assertTrue(actual.contains("STU-001"),
                    "File should contain the student ID. Actual: " + actual);
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    @DisplayName("saveToFile throws GradeBookIOError on IO failure")
    void testSaveToFile_IOFailure_ThrowsGradeBookIOError() throws IOException {
        // Arrange
        Student s1 = TestFixtures.createStudentWithScores("STU-001", "Ali", 80.0);
        roster.addStudent(s1);

        // Guaranteed-invalid path: a path *inside* a regular file
        Path file = Files.createTempFile("not-a-dir", ".txt");
        Path invalidPath = file.resolve("child.txt");
        try {
            // Act & Assert
            GradeBookIOError ex = assertThrows(
                    GradeBookIOError.class,
                    () -> roster.saveToFile(invalidPath.toString()));

            assertTrue(ex.getMessage().contains("Failed to save"),
                    "Message was: " + ex.getMessage());
            assertTrue(ex.getCause() instanceof IOException,
                    "Cause was: " + ex.getCause());
        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    @DisplayName("saveToFile does not leak raw IOException")
    void testSaveToFile_DoesNotLeakRawIOException() throws IOException {
        // Arrange
        roster.addStudent(
                TestFixtures.createStudentWithScores("STU-001", "Test", 80.0));

        Path file = Files.createTempFile("not-a-dir", ".txt");
        Path invalidPath = file.resolve("child.txt");
        try {
            // Act & Assert: exception must be GradeBookIOError, NOT raw IOException
            GradeBookIOError ex = assertThrows(
                    GradeBookIOError.class,
                    () -> roster.saveToFile(invalidPath.toString()));
            assertNotNull(ex.getCause());
        } finally {
            Files.deleteIfExists(file);
        }
    }
}