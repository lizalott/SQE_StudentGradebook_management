# Equivalence Partitioning Analysis - Lab 5

**Project:** SQE Library Management System  
**Date:** 2026-08-29  
**Author:** Liza Lott

---

## Task 1: Equivalence Class Tables

### 1. Letter Grade Conversion (score → letter grade)

| Class ID | Class Description | Type | Representative Value | Expected Result |
|----------|-------------------|------|---------------------|-----------------|
| C1 | Score < 0 | Invalid | -10 | IllegalArgumentException |
| C2 | Score 0-59 | Valid -  F | 45 | "F" |
| C3 | Score 60-69 | Valid - D | 65 | "D" |
| C4 | Score 70-79 | Valid - C | 75 | "C" |
| C5 | Score 80-89 | Valid - B | 85 | "B" |
| C6 | Score 90-100 | Valid - A| 95 | "A" |s
| C7 | Score > 100 | Invalid | 150 | IllegalArgumentException |

**Test Cases:**
- TC-LG-01: Test representative values 45, 65, 75, 85, 95
- TC-LG-02: Test invalid values -10 and 150

---

### 2. Score Count Validation (1-6 scores per student)

| Class ID | Class Description | Type | Representative Value | Expected Result |
|----------|-------------------|------|---------------------|-----------------|
| C8 | 0 scores | Invalid | 0 | IllegalArgumentException |
| C9 | 1-6 scores | Valid | 3 | Passes validation |
| C10 | 7+ scores | Invalid | 8 | IllegalArgumentException |

**Test Cases:**
- TC-SC-01: Student with 0 scores → Validation fails
- TC-SC-02: Student with 3 scores → Validation passes
- TC-SC-03: Student with 8 scores → Validation fails

---

### 3. Name Validation (non-empty, max 50 chars, letters/spaces/hyphens)

| Class ID | Class Description | Type | Representative Value | Expected Result |
|----------|-------------------|------|---------------------|-----------------|
| C11 | Null/Empty string | Invalid | "" or null | IllegalArgumentException |
| C12 | Valid name | Valid | "Liza Lott-victor" | Passes validation |
| C13 | Over 50 characters | Invalid | "A very long name that exceeds fifty characters..." | IllegalArgumentException |
| C14 | Contains digits | Invalid | "Liza123" | IllegalArgumentException |
| C15 | Contains special chars | Invalid | "Liza@Lott" | IllegalArgumentException |

**Test Cases:**
- TC-NM-01: Empty string → Validation fails
- TC-NM-02: "Liza Lott-Victor" → Validation passes
- TC-NM-03: 51-character string → Validation fails
- TC-NM-04: "Liza123" → Validation fails
- TC-NM-05: "Liza@Lott" → Validation fails

---

## Task 2: Test Implementation

### Letter Grade Tests (TestLetterGrade.java)

Tests implemented for all 7 equivalence classes (2 invalid, 5 valid).

### Score Count Tests (TestRoster.java)

Tests implemented for all 3 equivalence classes (0, 3, 8 scores).

### Name Validation Tests (TestValidateName.java)

Tests implemented for all 5 equivalence classes identified.

---

## Task 3: Test Execution Results

### /Java Test Output
EQUIVALENCE PARTITIONING TESTS - LAB 5
============================================================

Letter Grade Tests:
✅ testLetterGrade_Valid_45_F
✅ testLetterGrade_Valid_65_D
✅ testLetterGrade_Valid_75_C
✅ testLetterGrade_Valid_85_B
✅ testLetterGrade_Valid_95_A
✅ testLetterGrade_Invalid_Negative_ThrowsException
✅ testLetterGrade_Invalid_Above100_ThrowsException

Score Count Tests:
✅ testValidateScoreCount_0Scores_ThrowsException
✅ testValidateScoreCount_3Scores_Passes
✅ testValidateScoreCount_8Scores_ThrowsException

Name Validation Tests:
✅ testValidateName_Empty_ThrowsException
✅ testValidateName_ValidName_Passes
✅ testValidateName_OverLength_ThrowsException
✅ testValidateName_ContainsDigits_ThrowsException
✅ testValidateName_ContainsSpecialChars_ThrowsException

============================================================
TEST SUMMARY
============================================================
Total Tests: 12
✅ Passed: 12
❌ Failed: 0
Pass Rate: 100%


---

## Task 4: Limitations of Equivalence Partitioning

### Boundary-Blind-Spot Limitation

Equivalence Partitioning tests only **representative values** from each class, not the boundaries between classes.

For example, in letter grade conversion:
- We test 59 → "F" and 60 → "D" separately (they're in different classes)
- But we don't test 59.999, 60.0 exactly, or 59.5
- If the code incorrectly uses `score < 60` instead of `score <= 59`, we'd miss this bug!

### Why This Matters

| Class | Representative Tested | Boundary Not Tested |
|-------|----------------------|---------------------|
| F (0-59) | 45 | 59 (edge to D) |
| D (60-69) | 65 | 60 (edge from F) |

**Off-by-one errors** are common and EP alone won't catch them.

### Pairing with Boundary Value Analysis

In Lab 6, we'll combine EP with **Boundary Value Analysis (BVA)** to test:
- Values just inside boundaries (59, 60, 69, 70)
- Values just outside boundaries (60, 70) [these overlap]

### Recommendation

Use EP to design the **high-level test strategy**, then apply BVA to test the **edges** of each class. This combination provides the best coverage with minimal test cases.

---


