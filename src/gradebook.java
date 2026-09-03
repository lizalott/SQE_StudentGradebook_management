import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Gradebook service for managing student grades.
 * Handles student registration, score management, and statistics.
 */
public class Gradebook {
    
    private final Map<String, Student> students;
    private String courseName;
    private String semester;
    
    /**
     * Constructs a new Gradebook with default course information.
     */
    public Gradebook() {
        this.students = new HashMap<>();
        this.courseName = "Library Science 101";
        this.semester = "Fall 2026";
    }
    
    /**
     * Constructs a new Gradebook with custom course information.
     * 
     * @param courseName The name of the course
     * @param semester The semester
     */
    public Gradebook(String courseName, String semester) {
        this.students = new HashMap<>();
        this.courseName = courseName;
        this.semester = semester;
    }
    
    /**
     * Adds a student to the gradebook with duplicate ID validation.
     * 
     * @param student The student to add (must not be null)
     * @throws IllegalArgumentException if student is null or ID already exists
     */
    public void addStudent(Student student) {
        // Validate student is not null
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        // Use studentId as key
        String key = student.getStudentId();
        
        // Check for duplicate ID
        if (students.containsKey(key)) {
            throw new IllegalArgumentException(
                String.format("Student with ID %s already exists in the gradebook", key)
            );
        }
        
        students.put(key, student);
        System.out.printf("Added student: %s (ID: %s)%n", 
                         student.getName(), student.getStudentId());
    }
    
    /**
     * Finds a student by their unique ID.
     * 
     * @param studentId The student ID to search for
     * @return Optional containing the student if found, empty otherwise
     */
    public Optional<Student> findStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(students.get(studentId.trim()));
    }
    
    /**
     * Removes a student from the gradebook.
     * 
     * @param studentId The ID of the student to remove
     * @return true if removed, false if not found
     */
    public boolean removeStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        
        if (students.containsKey(studentId.trim())) {
            students.remove(studentId.trim());
            System.out.printf("Removed student with ID: %s%n", studentId);
            return true;
        }
        return false;
    }
    
    /**
     * Calculates the average score for a student.
     * 
     * @param studentId The ID of the student
     * @return Optional containing the average, or empty if student has no scores
     * @throws IllegalArgumentException if student not found
     */
    public Optional<Double> calculateAverage(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getAverageScore();
    }
    
    /**
     * Calculates the average score for a student, returning a default value.
     * 
     * @param studentId The ID of the student
     * @param defaultValue The value to return if student has no scores
     * @return The average score or the default value
     */
    public double calculateAverageOrDefault(String studentId, double defaultValue) {
        return calculateAverage(studentId).orElse(defaultValue);
    }
    
    /**
     * Gets the highest score for a student.
     * 
     * @param studentId The ID of the student
     * @return Optional containing the highest score, or empty if no scores exist
     * @throws IllegalArgumentException if student not found
     */
    public Optional<Double> getHighestScore(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getHighestScore();
    }
    
    /**
     * Gets the lowest score for a student.
     * 
     * @param studentId The ID of the student
     * @return Optional containing the lowest score, or empty if no scores exist
     * @throws IllegalArgumentException if student not found
     */
    public Optional<Double> getLowestScore(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getLowestScore();
    }
    
    /**
     * Gets the total score for a student.
     * 
     * @param studentId The ID of the student
     * @return The total score, or 0.0 if no scores exist
     * @throws IllegalArgumentException if student not found
     */
    public double getTotalScore(String studentId) {
        Student student = findStudent(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        
        return student.getTotalScore();
    }
    
    /**
     * Gets all students in the gradebook.
     * 
     * @return A copy of the student map
     */
    public Map<String, Student> getStudents() {
        return new HashMap<>(students);
    }
    
    /**
     * Gets the number of students in the gradebook.
     * 
     * @return The student count
     */
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * Checks if a student ID is unique.
     * 
     * @param studentId The student ID to check
     * @return true if the ID is not already used
     */
    public boolean isStudentIdUnique(String studentId) {
        return !students.containsKey(studentId);
    }
    
    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    @Override
    public String toString() {
        return String.format("Gradebook{course='%s', semester='%s', students=%d}",
                courseName, semester, getStudentCount());
    }
}