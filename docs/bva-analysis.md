# Boundary Value Analysis - Lab 6

**Project:** SQE Library Management System  
**Date:** 2026-09-17  
**Author:** [Your Name]  

---

## Task 1: Boundary Analysis Tables

### 1. Letter Grade Function Boundaries

**Domain:** Score 0-100  
**Cut-offs:** 60 (D/F), 70 (C/D), 80 (B/C), 90 (A/B)

| Boundary | Value-1 | Value | Value+1 | Expected at Value-1 | Expected at Value | Expected at Value+1 |
|----------|---------|-------|---------|---------------------|-------------------|---------------------|
| Domain min (0) | -1 | 0 | 1 | IllegalArgumentException | "F" | "F" |
| F/D cut-off (60) | 59 | 60 | 61 | "F" | "D" | "D" |
| D/C cut-off (70) | 69 | 70 | 71 | "D" | "C" | "C" |
| C/B cut-off (80) | 79 | 80 | 81 | "C" | "B" | "B" |
| A/B cut-off (90) | 89 | 90 | 91 | "B" | "A" | "A" |
| Domain max (100) | 99 | 100 | 101 | "A" | "A" | IllegalArgumentException |

**Total Boundary Test Cases:** 18  
**Boundary Values:** -1, 0, 1, 59, 60, 61, 69, 70, 71, 79, 80, 81, 89, 90, 91, 99, 100, 101

---

### 2. Score Count Rule Boundaries

**Domain:** 1-6 scores per student (valid range)

| Boundary | Value-1 | Value | Value+1 | Expected at Value-1 | Expected at Value | Expected at Value+1 |
|----------|---------|-------|---------|---------------------|-------------------|---------------------|
| Domain min (1) | 0 | 1 | 2 | IllegalArgumentException | Valid | Valid |
| Domain max (6) | 5 | 6 | 7 | Valid | Valid | IllegalArgumentException |

**Total Boundary Test Cases:** 6  
**Boundary Values:** 0, 1, 2, 5, 6, 7

---

### 3. Name Length Boundaries

**Domain:** 1-50 characters (valid range)

| Boundary | Value-1 | Value | Value+1 | Expected at Value-1 | Expected at Value | Expected at Value+1 |
|----------|---------|-------|---------|---------------------|-------------------|---------------------|
| Domain min (1) | 0 | 1 | 2 | IllegalArgumentException | Valid | Valid |
| Domain max (50) | 49 | 50 | 51 | Valid | Valid | IllegalArgumentException |

**Total Boundary Test Cases:** 6  
**Boundary Values:** 0, 1, 2, 49, 50, 51 characters

---

## Summary

| Function | Boundary Values | Test Cases |
|----------|----------------|-----------|
| letterGrade | -1,0,1,59,60,61,69,70,71,79,80,81,89,90,91,99,100,101 | 18 |
| validateScoreCount | 0,1,2,5,6,7 | 6 |
| validateName | 0,1,2,49,50,51 chars | 6 |
| **Total** | | **30** |