package fixtures;

import com.library.models.Student;
import com.library.services.Roster;


public class TestFixtures {
  
    public static Roster createPopulatedRoster() {
        Roster roster = new Roster();
        
        Student s1 = new Student("Liza Lott", "STU-001");
        s1.addScore(80.0);
        s1.addScore(90.0);  // Average: 85.0
        
        Student s2 = new Student("Attiqa Sarwar", "STU-002");
        s2.addScore(70.0);  // Average: 70.0
        
        roster.addStudent(s1);
        roster.addStudent(s2);
        
        return roster;
    }
    
   public static Roster createEmptyRoster() {
        return new Roster();
    }
    

    public static Student createStudentWithScores(String id, String name, double... scores) {
        Student student = new Student(name, id);
        for (double score : scores) {
            student.addScore(score);
        }
        return student;
    }
}