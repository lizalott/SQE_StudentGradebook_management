// src/Student.java
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentId;
    private List<Double> scores;
    
    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.scores = new ArrayList<>();
    }
    
    public void addScore(double score) {
        // Validation in next commit
        scores.add(score);
    }
    
    
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public List<Double> getScores() { return scores; }
}