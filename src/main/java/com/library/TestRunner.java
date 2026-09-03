package com.library;

import com.library.models.Student;
import com.library.services.Gradebook;
import java.util.Optional;

public class TestRunner {
    
    private static int passed = 0;
    private static int failed = 0;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("GRADEBOOK TEST EXECUTION - LAB 4 TASK 4");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Run all test cases
        testTC001();
        testTC002();
        testTC003();
        testTC004();
        testTC005();
        testTC006();
        testTC007();
        testTC008();
        testTC009();
        testTC010();
        testTC011();
        testTC012();
        
        // Print summary
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println("Total Tests: " + (passed + failed));
        System.out.println("✅ Passed: " + passed);
        System.out.println("❌ Failed: " + failed);
        if (passed + failed > 0) {
            System.out.println("Pass Rate: " + (passed * 100 / (passed + failed)) + "%");
        }
        System.out.println("=".repeat(60));
    }
    
    // ============================================
    // TC-001: Add Valid Score
    // ============================================
    private static void testTC001() {
        System.out.println("TC-001: Add Valid Score");
        try {
            Student s = new Student("John Doe", "STU-001");
            s.addScore(85.5);
            
            if (s.getScoreCount() == 1 && s.getAssessmentScores().contains(85.5)) {
                System.out.println("  ✅ PASS - Score 85.5 added successfully");
                passed++;
            } else {
                System.out.println("  ❌ FAIL - Score not added correctly");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-002: Add Negative Score (Should Reject)
    // ============================================
    private static void testTC002() {
        System.out.println("TC-002: Add Negative Score (Should Reject)");
        try {
            Student s = new Student("Jane Doe", "STU-002");
            s.addScore(-15.0);
            // BUG: This should throw exception but doesn't!
            System.out.println("  ❌ FAIL - Negative score was accepted! Bug #1 confirmed.");
            failed++;
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ PASS - Exception thrown: " + e.getMessage());
            passed++;
        } catch (Exception e) {
            System.out.println("  ⚠️ PARTIAL - Wrong exception type: " + e.getClass().getSimpleName());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-003: Add Score > 100 (Should Reject)
    // ============================================
    private static void testTC003() {
        System.out.println("TC-003: Add Score > 100 (Should Reject)");
        try {
            Student s = new Student("Bob Smith", "STU-003");
            s.addScore(105.0);
            // BUG: This should throw exception but doesn't!
            System.out.println("  ❌ FAIL - Score > 100 was accepted! Bug #1 confirmed.");
            failed++;
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ PASS - Exception thrown: " + e.getMessage());
            passed++;
        } catch (Exception e) {
            System.out.println("  ⚠️ PARTIAL - Wrong exception type: " + e.getClass().getSimpleName());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-004: Add Null Score (Should Reject)
    // ============================================
    private static void testTC004() {
        System.out.println("TC-004: Add Null Score (Should Reject)");
        try {
            Student s = new Student("Alice Brown", "STU-004");
            s.addScore(null);
            // BUG: This should throw exception but may not!
            System.out.println("  ❌ FAIL - Null score was accepted! Bug #1 confirmed.");
            failed++;
        } catch (NullPointerException e) {
            System.out.println("  ✅ PASS - Exception thrown: " + e.getMessage());
            passed++;
        } catch (Exception e) {
            System.out.println("  ⚠️ PARTIAL - Wrong exception type: " + e.getClass().getSimpleName());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-005: Add Score at Minimum Boundary (0)
    // ============================================
    private static void testTC005() {
        System.out.println("TC-005: Add Score at Minimum Boundary (0)");
        try {
            Student s = new Student("Charlie Wilson", "STU-005");
            s.addScore(0.0);
            
            if (s.getScoreCount() == 1 && s.getAssessmentScores().contains(0.0)) {
                System.out.println("  ✅ PASS - Score 0.0 added successfully");
                passed++;
            } else {
                System.out.println("  ❌ FAIL - Score 0.0 not added correctly");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-006: Add Score at Maximum Boundary (100)
    // ============================================
    private static void testTC006() {
        System.out.println("TC-006: Add Score at Maximum Boundary (100)");
        try {
            Student s = new Student("Diana Prince", "STU-006");
            s.addScore(100.0);
            
            if (s.getScoreCount() == 1 && s.getAssessmentScores().contains(100.0)) {
                System.out.println("  ✅ PASS - Score 100.0 added successfully");
                passed++;
            } else {
                System.out.println("  ❌ FAIL - Score 100.0 not added correctly");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-007: Calculate Average with Scores
    // ============================================
    private static void testTC007() {
        System.out.println("TC-007: Calculate Average with Scores");
        try {
            Student s = new Student("Eva Green", "STU-007");
            s.addScores(85.5, 90.0, 95.5);
            
            Optional<Double> avg = s.getAverageScore();
            
            if (avg.isPresent()) {
                double expected = 90.3333333333333;
                double actual = avg.get();
                if (Math.abs(actual - expected) < 0.0001) {
                    System.out.println("  ✅ PASS - Average: " + actual + " (expected: " + expected + ")");
                    passed++;
                } else {
                    System.out.println("  ❌ FAIL - Average: " + actual + " (expected: " + expected + ")");
                    failed++;
                }
            } else {
                System.out.println("  ❌ FAIL - Average returned empty");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
  // ============================================
// TC-008: Calculate Average with Empty Scores
// ============================================
private static void testTC008() {
    System.out.println("TC-008: Calculate Average with Empty Scores");
    try {
        Student s = new Student("Frank White", "STU-008");
        Optional<Double> avg = s.getAverageScore();
        
        // FIX: Actually check the result!
        if (avg.isEmpty()) {
            System.out.println("  ✅ PASS - Returned empty (no division by zero)");
            passed++;
        } else {
            System.out.println("  ❌ FAIL - Should return empty, got: " + avg.get());
            failed++;
        }
    } catch (ArithmeticException e) {
        System.out.println("  ❌ FAIL - Division by zero error occurred!");
        failed++;
    } catch (Exception e) {
        System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
        failed++;
    }
    System.out.println();
}
    // ============================================
    // TC-009: Calculate Average with Single Score
    // ============================================
    private static void testTC009() {
        System.out.println("TC-009: Calculate Average with Single Score");
        try {
            Student s = new Student("Grace Lee", "STU-009");
            s.addScore(92.0);
            
            Optional<Double> avg = s.getAverageScore();
            
            if (avg.isPresent()) {
                double expected = 92.0;
                double actual = avg.get();
                if (Math.abs(actual - expected) < 0.0001) {
                    System.out.println("  ✅ PASS - Average: " + actual + " (expected: " + expected + ")");
                    passed++;
                } else {
                    System.out.println("  ❌ FAIL - Average: " + actual + " (expected: " + expected + ")");
                    failed++;
                }
            } else {
                System.out.println("  ❌ FAIL - Average returned empty");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-010: Reject Duplicate Student ID
    // ============================================
    private static void testTC010() {
        System.out.println("TC-010: Reject Duplicate Student ID");
        try {
            Gradebook gb = new Gradebook();
            Student s1 = new Student("Henry Ford", "STU-010");
            gb.addStudent(s1);
            
            Student s2 = new Student("Helen Ford", "STU-010");
            gb.addStudent(s2);
            
            // BUG: This should throw exception but doesn't!
            System.out.println("  ❌ FAIL - Duplicate ID was accepted! Bug #4 confirmed.");
            failed++;
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ PASS - Exception thrown: " + e.getMessage());
            passed++;
        } catch (Exception e) {
            System.out.println("  ⚠️ PARTIAL - Wrong exception type: " + e.getClass().getSimpleName());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-011: Find Student by ID
    // ============================================
    private static void testTC011() {
        System.out.println("TC-011: Find Student by ID");
        try {
            Gradebook gb = new Gradebook();
            Student s = new Student("Ivy Chen", "STU-011");
            gb.addStudent(s);
            
            Optional<Student> found = gb.findStudent("STU-011");
            
            if (found.isPresent() && found.get().getName().equals("Ivy Chen")) {
                System.out.println("  ✅ PASS - Found student: " + found.get().getName());
                passed++;
            } else {
                System.out.println("  ❌ FAIL - Student not found");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
    
    // ============================================
    // TC-012: Calculate Highest Score
    // ============================================
    private static void testTC012() {
        System.out.println("TC-012: Calculate Highest Score");
        try {
            Student s = new Student("Jack Ryan", "STU-012");
            s.addScores(85.5, 92.0, 78.5, 95.5);
            
            Optional<Double> highest = s.getHighestScore();
            
            if (highest.isPresent()) {
                double expected = 95.5;
                double actual = highest.get();
                if (Math.abs(actual - expected) < 0.0001) {
                    System.out.println("  ✅ PASS - Highest: " + actual + " (expected: " + expected + ")");
                    passed++;
                } else {
                    System.out.println("  ❌ FAIL - Highest: " + actual + " (expected: " + expected + ")");
                    failed++;
                }
            } else {
                System.out.println("  ❌ FAIL - Highest returned empty");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("  ❌ FAIL - Exception thrown: " + e.getMessage());
            failed++;
        }
        System.out.println();
    }
}