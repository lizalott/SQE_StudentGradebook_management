# Requirements Traceability Matrix (RTM) - GradeBook Module

**Project:** SQE Library Management System  
**Module:** GradeBook  
**Version:** v0.2  
**Date:** 2026-08-29  
**Author:** Liza Lott 

---

## RTM Table

| Requirement ID | Requirement Description | Priority | Test Cases | Coverage Status |
|----------------|------------------------|----------|------------|-----------------|
| **REQ-1** | System shall reject negative scores (throw IllegalArgumentException) | Critical | TC-002 | ✅ Covered |
| **REQ-2** | System shall reject scores > 100 (throw IllegalArgumentException) | Critical | TC-003 | ✅ Covered |
| **REQ-3** | System shall accept valid scores (0-100 range) | High | TC-001, TC-005, TC-006 | ✅ Covered |
| **REQ-4** | System shall calculate average score correctly | High | TC-007, TC-009 | ✅ Covered |
| **REQ-5** | System shall handle empty score list gracefully (no division by zero) | High | TC-008 | ✅ Covered |
| **REQ-6** | System shall reject duplicate student IDs | High | TC-010 | ✅ Covered |
| **REQ-7** | System shall find student by ID | Medium | TC-011 | ✅ Covered |
| **REQ-8** | System shall reject null scores | Medium | TC-004 | ✅ Covered |

---

## Traceability Matrix Visualization
Requirements → Test Cases Mapping

REQ-1 ────────► TC-002
REQ-2 ────────► TC-003
REQ-3 ────────► TC-001, TC-005, TC-006
REQ-4 ────────► TC-007, TC-009
REQ-5 ────────► TC-008
REQ-6 ────────► TC-010
REQ-7 ────────► TC-011
REQ-8 ────────► TC-004

---

## Test Case to Requirements Mapping

| Test Case | Title | Requirements Covered |
|-----------|-------|---------------------|
| TC-001 | Add valid score | REQ-3 |
| TC-002 | Reject negative score | REQ-1 |
| TC-003 | Reject score above 100 | REQ-2 |
| TC-004 | Reject null score | REQ-8 |
| TC-005 | Add score at minimum boundary (0) | REQ-3 |
| TC-006 | Add score at maximum boundary (100) | REQ-3 |
| TC-007 | Calculate average with scores | REQ-4 |
| TC-008 | Calculate average with empty scores | REQ-5 |
| TC-009 | Calculate average with single score | REQ-4 |
| TC-010 | Reject duplicate student ID | REQ-6 |
| TC-011 | Find student by ID | REQ-7 |
| TC-012 | Calculate highest score | REQ-4 |

---

## Coverage Analysis

### Requirements Coverage

| Status | Count | Percentage |
|--------|-------|------------|
| ✅ Covered | 8 | 100% |
| ⚠️ Partial | 0 | 0% |
| ❌ Uncovered | 0 | 0% |

**All requirements have at least one test case!**

### Test Case Coverage by Priority

| Priority | Requirements Covered |
|----------|---------------------|
| Critical | REQ-1, REQ-2 |
| High | REQ-3, REQ-4, REQ-5, REQ-6 |
| Medium | REQ-7, REQ-8 |

---

## Test Case Type Coverage

| Test Type | Count | Requirements Covered |
|-----------|-------|---------------------|
| Positive/Functional | 8 | REQ-3, REQ-4, REQ-5, REQ-7 |
| Negative/Error-Path | 4 | REQ-1, REQ-2, REQ-6, REQ-8 |
| Boundary | 2 | REQ-3 |
| Edge Cases | 2 | REQ-4, REQ-5 |

---

## Gap Analysis

### Potential Gaps

| Requirement | Risk | Mitigation |
|-------------|------|------------|
| REQ-7 (Find student) | Only tests finding existing student | Add test for finding non-existent student |
| REQ-8 (Null scores) | Only tests null | Add test for empty scores |
| REQ-4 (Average) | Tests average with scores | Add test for total score calculation |

### Recommended Additional Test Cases

| Test Case | Requirement | Priority | Rationale |
|-----------|-------------|----------|-----------|
| TC-013 | REQ-7 | Low | Find non-existent student returns empty Optional |
| TC-014 | REQ-8 | Low | Add empty score list (no-op) |
| TC-015 | REQ-4 | Low | Calculate total score sum |

---

## Defect Coverage

| Defect Issue | Related Requirement | Test Case | Status |
|--------------|---------------------|-----------|--------|
| #1 - Negative scores accepted | REQ-1 | TC-002 | Fixed |
| #2 - ZeroDivisionError | REQ-5 | TC-008 | Fixed |
| #3 - Duplicate IDs allowed | REQ-6 | TC-010 | Fixed |

---

## RTM Summary

| Metric | Value |
|--------|-------|
| **Total Requirements** | 8 |
| **Total Test Cases** | 12 |
| **Requirements with Test Cases** | 8 |
| **Requirements Covered** | 100% |
| **Test Cases per Requirement** | 1.5 |
| **Critical Requirements Covered** | 100% |
| **High Requirements Covered** | 100% |

---

## Sign-off

| Role | Name | Date |
|------|------|------|
| **Test Lead** | [Your Name] | [Date] |
| **Developer** | [Your Name] | [Date] |

