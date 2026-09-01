# Test Cases - GradeBook Module

**Project:** SQE Library Management System  
**Module:** GradeBook  
**Version:** v0.2  
**Date:** 2026-08-29  
**Author:** Liza Lott 

---

## Test Case Summary

| Metric | Count |
|--------|-------|
| Total Test Cases | 12 |
| Positive/Functional Tests | 8 |
| Negative/Error-Path Tests | 4 |
| High Priority | 6 |
| Medium Priority | 4 |
| Low Priority | 2 |

---

## Test Cases

### TC-1: Add Valid Score

| Field | Value |
|-------|-------|
| **ID** | TC-1 |
| **Title** | Add valid score to student record |
| **Requirement** | REQ-3 |
| **Priority** | High |
| **Type** | Functional / Positive |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
 1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
 2. Call `s.addScore(95.5)` |
| **Expected Result** | Score is accepted and added to scores list. `s.getScoreCount()` returns 1. |
| **Actual Result** | (To be filled during execution) |
| **Status** |  Not Executed |


---

### TC-2: Add Negative Score (Should Reject)

| Field | Value |
|-------|-------|
| **ID** | TC-2 |
| **Title** | Reject negative score |
| **Requirement** | REQ-1 |
| **Priority** | Critical |
| **Type** | Negative / Error-Path |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
2. Call `s.addScore(-15.0)` |
| **Expected Result** | `IllegalArgumentException` is thrown. Scores list remains empty. |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-3: Add Score Exceeding 100 (Should Reject)

| Field | Value |
|-------|-------|
| **ID** | TC-3 |
| **Title** | Reject score above 100 |
| **Requirement** | REQ-2 |
| **Priority** | Critical |
| **Type** | Negative / Error-Path |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
2. Call `s.addScore(105.0)` |
| **Expected Result** | `IllegalArgumentException` is thrown. Scores list remains empty. |
| **Actual Result** | (To be filled during execution) |
| **Status** |  Not Executed |


---

### TC-4: Add Null Score (Should Reject)

| Field | Value |
|-------|-------|
| **ID** | TC-4 |
| **Title** | Reject null score |
| **Requirement** | REQ-8 |
| **Priority** | High |
| **Type** | Negative / Error-Path |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** |
 1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
 2. Call `s.addScore(null)` |
| **Expected Result** | `NullPointerException` is thrown. Scores list remains empty. |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-5: Add Score at Minimum Boundary (0)

| Field | Value |
|-------|-------|
| **ID** | TC-5 |
| **Title** | Add score at minimum boundary (0) |
| **Requirement** | REQ-3 |
| **Priority** | High |
| **Type** | Boundary / Functional |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")`
2. Call `s.addScore(0.0)` |
| **Expected Result** | Score is accepted. `s.getScoreCount()` returns 1. |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-6: Add Score at Maximum Boundary (100)

| Field | Value |
|-------|-------|
| **ID** | TC-6 |
| **Title** | Add score at maximum boundary (100) |
| **Requirement** | REQ-3 |
| **Priority** | High |
| **Type** | Boundary / Functional |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
2. Call `s.addScore(100.0)` |
| **Expected Result** | Score is accepted. `s.getScoreCount()` returns 1. |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |

---

### TC-7: Calculate Average with Scores

| Field | Value |
|-------|-------|
| **ID** | TC-7 |
| **Title** | Calculate average with multiple scores |
| **Requirement** | REQ-4 |
| **Priority** | High |
| **Type** | Functional |
| **Preconditions** | A Student object exists with scores [85.5, 90.0, 95.5] |
| **Steps** |
 1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
 2. Add scores: `s.addScore(85.5)`, `s.addScore(90.0)`, `s.addScore(95.5)`
  3. Call `s.getAverageScore()` |
| **Expected Result** | Returns `Optional.of(90.333...)` |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |

---

### TC-8: Calculate Average with Empty Scores List

| Field | Value |
|-------|-------|
| **ID** | TC-8 |
| **Title** | Calculate average with empty scores list |
| **Requirement** | REQ-5 |
| **Priority** | High |
| **Type** | Functional / Edge Case |
| **Preconditions** | A Student object exists with empty scores list |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")` 
2. Call `s.getAverageScore()` |
| **Expected Result** | Returns `Optional.empty()` (no division by zero error) |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |

---

### TC-9: Calculate Average with Single Score

| Field | Value |
|-------|-------|
| **ID** | TC-9 |
| **Title** | Calculate average with single score |
| **Requirement** | REQ-4 |
| **Priority** | Medium |
| **Type** | Functional |
| **Preconditions** | A Student object exists with one score [92.0] |
| **Steps** | 
1. Create student: `Student s = new Student("Liza Lott", "STU-001")`
2. Add score: `s.addScore(92.0)`  
3. Call `s.getAverageScore()` |
| **Expected Result** | Returns `Optional.of(92.0)` |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-10: Reject Duplicate Student ID

| Field | Value |
|-------|-------|
| **ID** | TC-10 |
| **Title** | Reject duplicate student ID in Gradebook |
| **Requirement** | REQ-6 |
| **Priority** | High |
| **Type** | Negative / Functional |
| **Preconditions** | Gradebook exists with one student "STU-001" |
| **Steps** | 
1. Create Gradebook: `Gradebook gb = new Gradebook()` <br> 
2. Add first student: `gb.addStudent(new Student("Liza", "STU-001"))` <br> 
3. Add second student with same ID: `gb.addStudent(new Student("attiqa", "STU-001"))` |
| **Expected Result** | `IllegalArgumentException` is thrown. Gradebook still has 1 student. |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-11: Find Student by ID

| Field | Value |
|-------|-------|
| **ID** | TC-11 |
| **Title** | Find student by valid ID |
| **Requirement** | REQ-7 |
| **Priority** | Medium |
| **Type** | Functional |
| **Preconditions** | Gradebook exists with one student "STU-001" |
| **Steps** | 1. Create Gradebook: `Gradebook gb = new Gradebook()` <br> 2. Add student: `gb.addStudent(new Student("Liza", "STU-001"))` <br> 3. Call `gb.findStudent("STU-001")` |
| **Expected Result** | Returns `Optional` containing the student with name "Liza" |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |


---

### TC-12: Calculate Highest Score

| Field | Value |
|-------|-------|
| **ID** | TC-12 |
| **Title** | Find highest score from list |
| **Requirement** | REQ-4 |
| **Priority** | Medium |
| **Type** | Functional |
| **Preconditions** | A Student object exists with scores [85.5, 92.0, 78.5, 95.5] |
| **Steps** | 1. Create student: `Student s = new Student("Liza Lott", "STU-001")` <br> 2. Add scores: `s.addScores(85.5, 92.0, 78.5, 95.5)` <br> 3. Call `s.getHighestScore()` |
| **Expected Result** | Returns `Optional.of(95.5)` |
| **Actual Result** | (To be filled during execution) |
| **Status** | Not Executed |

---

## Test Case Types Summary

| Type | Count | Test Cases |
|------|-------|------------|
| Positive/Functional | 8 | TC-001, TC-005, TC-006, TC-007, TC-009, TC-011, TC-012 |
| Negative/Error-Path | 4 | TC-002, TC-003, TC-004, TC-010 |
| Boundary | 2 | TC-005, TC-006 |
| Edge Cases | 2 | TC-008, TC-009 |

## Priority Summary

| Priority | Count | Test Cases |
|----------|-------|------------|
| Critical | 2 | TC-002, TC-003 |
| High | 6 | TC-001, TC-004, TC-005, TC-006, TC-007, TC-008, TC-010 |
| Medium | 4 | TC-009, TC-011, TC-012 |