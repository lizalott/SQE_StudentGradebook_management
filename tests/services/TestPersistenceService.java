package services;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.models.Student;
import com.library.services.PersistenceService;

@DisplayName("PersistenceService Coverage Tests")
public class TestPersistenceService {
    
    private PersistenceService service;
    private static final String DATA_FILE = new File("students.dat").getAbsolutePath();

    @BeforeEach
    void setUp() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        service = new PersistenceService();
    }

    @AfterEach
    void tearDown() {
        // Cleanup after tests
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    // ============================================
    // 1. COVER saveStudent()
    // ============================================
   @Test
    @DisplayName("saveStudent: Should save student and trigger cache update")
    void testSaveStudent_Success() {
        Student s = new Student("Save Test", "STU-001");
        s.addScore(85.0);
        
        // Act
        service.saveStudent(s);
        
        // Assert file was created
        File file = new File(DATA_FILE);
        assertTrue(file.exists(), "Data file should be created after saving. Looked in: " + DATA_FILE);
    }

    // ============================================
    // 2. COVER loadStudent() - Valid and Invalid branches
    // ============================================
    @Test
    @DisplayName("loadStudent: Should load an existing student from file")
    void testLoadStudent_Success() {
        Student s = new Student("Load Test", "STU-002");
        s.addScore(90.0);
        service.saveStudent(s); // Save first so file exists

        // Act
        Student loaded = service.loadStudent("STU-002");
        
        // Assert
        assertNotNull(loaded);
        assertEquals("Load Test", loaded.getName());
        assertEquals(90.0, loaded.getAssessmentScores().get(0));
    }

    @Test
    @DisplayName("loadStudent: Should return null if student not found")
    void testLoadStudent_NotFound() {
        Student s = new Student("Someone Else", "STU-003");
        service.saveStudent(s);

        // Act - search for a different ID
        Student loaded = service.loadStudent("STU-999");
        
        // Assert
        assertNull(loaded, "Should return null for non-existent student ID");
    }

    // ============================================
    // 3. COVER the Exception/Catch Branches
    // ============================================
    
    @Test
    @DisplayName("loadStudent: Should catch IOException/ClassNotFound gracefully")
    void testLoadStudent_CorruptFile_CatchesException() {
        // Arrange: Write garbage data to the file to force a ClassNotFoundException/StreamCorruptedException
        try {
            java.io.FileWriter writer = new java.io.FileWriter(DATA_FILE);
            writer.write("THIS IS NOT A VALID SERIALIZED OBJECT");
            writer.close();
        } catch (Exception e) {
            fail("Setup failed");
        }

        // Act
        Student loaded = service.loadStudent("STU-001");
        
        // Assert
        assertNull(loaded, "Should return null when file is corrupted and exception is caught");
    }

    // ============================================
    // Extra: Cover getting all students
    // ============================================
            @Test
    @DisplayName("getAllStudents: Should return the cached students")
    void testGetAllStudents() {
        Student s = new Student("Cache Test", "STU-004");
        service.saveStudent(s);

        // ⚠️ YOU ARE MISSING THIS LINE: Declare 'all' by calling the method
        Map<String, Student> all = service.getAllStudents();

        // Now the compiler knows what 'all' is, and these will stop being red:
        assertEquals(1, all.size());
        assertTrue(all.containsKey("STU-004"));
    }

}
    