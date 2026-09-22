# GitHub Issues Log - SQE Library Management System

**Project:** SQE Library Management System  
**Repository:** [sqe-library-management](https://github.com/lizalott/SQE_StudentGradebook_management)  
**Last Updated:** 2026-09-22  
**Total Issues:** 18+  
---

### Lab 1-2: Initial Setup & Feature Development

#### Issue #1: Add score-adding capability to Student
- **Status:**  Closed (Fixed)
- **Type:** `enhancement`, `high-priority`, `quality`
- **Created:** Lab 2
- **Resolved:** PR #1 - feature/add-student branch
- **Description:** Implement score-adding functionality for the Student class with validation.
- **Resolution:** Added `addScore()` method with validation, documentation, and tests.

---

#### Issue #2: Rename roll_no to student_id in Gradebook
- **Status:**  Closed (Fixed)
- **Type:** `refactor`
- **Created:** Lab 2
- **Resolved:** PR #2 - feature/rename-field-a branch
- **Description:** Rename `roll_no` field to `student_id` for consistency with Student class.
- **Resolution:** Renamed field, updated all references, resolved merge conflict with alternative naming.

---

#### Issue #3: Rename roll_no to id_number in Gradebook (Alternative)
- **Status:**  Closed (Won't Fix)
- **Type:** `refactor`
- **Created:** Lab 2
- **Resolved:** PR #3 - feature/rename-field-b branch (merged with conflict resolution)
- **Description:** Alternative naming proposal to use `id_number` instead of `student_id`.
- **Resolution:** Merged with `student_id` (from branch A). Conflict resolved by choosing the more standard naming convention.

---

### Lab 3: Defect Management System

#### Issue #4: ZeroDivisionError when calculating average
- **Status:**  Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 3 - Task 1
- **Resolved:** Task 3 - Fix branch
- **Description:** `calculateAverage()` throws `ZeroDivisionError` when student has no scores.
- **Severity Justification:** High - Application crash on valid use case.
- **Priority Justification:** P1 - Critical user-facing error blocking basic functionality.
- **Resolution:** Added empty check, returns `Optional.empty()` instead of crashing.
- **Regression Notes:** Verified with unit tests and manual testing.

---

#### Issue #5: Negative scores accepted in Student.addScore()
- **Status:**  Closed (Fixed)
- **Type:** `type:bug`, `severity:critical`, `priority:P0`
- **Created:** Lab 3 - Task 1
- **Resolved:** Task 3 - Fix branch
- **Description:** `addScore()` accepts negative scores without validation.
- **Severity Justification:** Critical - Data integrity violation, corrupts all calculations.
- **Priority Justification:** P0 - Must fix immediately; affects entire system integrity.
- **Resolution:** Added validation to reject scores < 0 and > 100.
- **Regression Notes:** Comprehensive unit tests added for boundary values.

---

#### Issue #6: Duplicate student IDs allowed in Gradebook
- **Status:**  Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 3 - Task 1
- **Resolved:** Task 3 - Fix branch
- **Description:** `addStudent()` allows multiple students with the same ID.
- **Severity Justification:** High - Data integrity issue causing duplicate records.
- **Priority Justification:** P1 - Must fix soon; primary key must be unique.
- **Resolution:** Added duplicate check before adding student.
- **Regression Notes:** Tests verify both duplicate rejection and unique acceptance.

---

#### Issue #7: Case-sensitive name comparison
- **Status:**  Closed (Deferred)
- **Type:** `type:bug`, `severity:medium`, `priority:P2`, `status:wontfix`
- **Created:** Lab 3 - Task 1
- **Resolved:** Deferred to v0.3
- **Description:** Student search is case-sensitive ("John" ≠ "john").
- **Severity Justification:** Medium - Usability issue, not a crash or data corruption.
- **Priority Justification:** P2 - Workaround exists (use exact case).
- **Resolution:** Deferred; will implement case-insensitive search in future sprint.

---

#### Issue #8: Averages not rounded to 2 decimal places
- **Status:**  Closed (Won't Fix)
- **Type:** `type:bug`, `severity:medium`, `priority:P3`, `status:wontfix`
- **Created:** Lab 3 - Task 1
- **Resolved:** Won't fix in current version
- **Description:** Average displays long decimals (e.g., 85.66666667).
- **Severity Justification:** Medium - Presentation issue only.
- **Priority Justification:** P3 - Low impact; can format in display layer.
- **Resolution:** Won't fix; it's a display concern, not a calculation error.

---

### Lab 4: Test Planning & Test Case Management

#### Issue #9: Negative scores accepted (BVA confirmed)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:critical`, `priority:P0`
- **Created:** Lab 4 - Task 4 (Test Execution)
- **Resolved:** Fix branch - `Fixes #9`
- **Description:** Testing revealed `addScore()` accepts invalid scores: -15, 105, null.
- **Severity Justification:** Critical - Data integrity violation confirmed by BVA testing.
- **Priority Justification:** P0 - Same issue as #5 but discovered via testing.
- **Resolution:** Added comprehensive validation to reject:
  - Negative scores (< 0)
  - Scores > 100
  - Null scores
  - NaN and Infinite values
- **Regression Notes:** Re-ran all 12 test cases; all now pass.

---

#### Issue #10: Division by zero in average calculation (BVA confirmed)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 4 - Task 4 (Test Execution)
- **Resolved:** Fix branch - `Fixes #10`
- **Description:** Testing confirmed `getAverageScore()` throws `ArithmeticException` for empty scores.
- **Severity Justification:** High - Application crash confirmed by testing.
- **Priority Justification:** P1 - Fix this sprint.
- **Resolution:** Added `if (assessmentScores.isEmpty()) return Optional.empty();`
- **Regression Notes:** TC-008 now passes.

---

#### Issue #11: Duplicate student IDs allowed (BVA confirmed)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 4 - Task 4 (Test Execution)
- **Resolved:** Fix branch - `Fixes #11`
- **Description:** Testing confirmed `Gradebook.addStudent()` accepts duplicates.
- **Severity Justification:** High - Data integrity violation.
- **Priority Justification:** P1 - Fix this sprint.
- **Resolution:** Added duplicate ID check.
- **Regression Notes:** TC-010 now passes.

---

### Lab 5: Equivalence Partitioning

#### Issue #12: Letter grade EP validation
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:medium`, `priority:P2`
- **Created:** Lab 5 - Task 2
- **Resolved:** EP test implementation
- **Description:** EP testing revealed potential off-by-one errors in letter grade boundaries.
- **Resolution:** Implemented EP tests; verified correct mapping:
  - 45 → F, 65 → D, 75 → C, 85 → B, 95 → A
  - -10, 150 → IllegalArgumentException
- **Regression Notes:** All 7 EP classes passing.

---

#### Issue #13: Score count EP validation
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:medium`, `priority:P2`
- **Created:** Lab 5 - Task 3
- **Resolved:** EP test implementation
- **Description:** EP testing for score count rules (1-6 valid).
- **Resolution:** Implemented tests for 0, 3, 8 scores:
  - 0 scores → Invalid (exception)
  - 3 scores → Valid
  - 8 scores → Invalid (exception)
- **Regression Notes:** All 3 EP classes passing.

---

#### Issue #14: Name validation EP tests
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:medium`, `priority:P2`
- **Created:** Lab 5 - Task 4
- **Resolved:** EP test implementation
- **Description:** EP testing for name field (non-empty, ≤50 chars, letters/spaces/hyphens).
- **Resolution:** Implemented 5 test classes:
  - Empty string → Invalid
  - Valid name → Valid
  - Over-length (51+) → Invalid
  - Contains digits → Invalid
  - Contains special chars → Invalid
- **Regression Notes:** All 5 EP classes passing.

---

### Lab 6: Boundary Value Analysis

#### Issue #15: Missing validation in Student.addScore() (BVA defect)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:critical`, `priority:P0`
- **Created:** Lab 6 - Task 2
- **Resolved:** Fix branch - `Fixes #15`
- **Description:** BVA testing at boundaries confirmed negative/null/>100 scores accepted.
- **Boundary Values Tested:** -1, 0, 1, 59, 60, 61, 69, 70, 71, 79, 80, 81, 89, 90, 91, 99, 100, 101
- **Severity Justification:** Critical - Data integrity violation.
- **Priority Justification:** P0 - Must fix immediately.
- **Resolution:** Added comprehensive boundary validation.
- **Regression Notes:** All 18 boundary test cases now pass.

---

#### Issue #16: Division by zero in average calculation (BVA defect)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 6 - Task 2
- **Resolved:** Fix branch - `Fixes #16`
- **Description:** BVA confirmed division by zero at boundary (0 scores).
- **Boundary Values Tested:** -1, 0, 1 for score count
- **Severity Justification:** High - Application crash.
- **Priority Justification:** P1 - Fix this sprint.
- **Resolution:** Added empty-list check before division.
- **Regression Notes:** TC-008 passes; BVA tests confirm.

---

#### Issue #17: Duplicate student IDs (BVA defect)
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:high`, `priority:P1`
- **Created:** Lab 6 - Task 3
- **Resolved:** Fix branch - `Fixes #17`
- **Description:** BVA testing at boundaries (0, 1, 2, 5, 6, 7 scores) revealed duplicate IDs.
- **Boundary Values Tested:** 0, 1, 2, 5, 6, 7
- **Severity Justification:** High - Data integrity violation.
- **Priority Justification:** P1 - Fix this sprint.
- **Resolution:** Added duplicate ID prevention in `addStudent()`.
- **Regression Notes:** All 6 boundary tests pass.

---

#### Issue #18: Name length boundary validation
- **Status:** Closed (Fixed)
- **Type:** `type:bug`, `severity:medium`, `priority:P2`
- **Created:** Lab 6 - Task 4
- **Resolved:** Fix branch
- **Description:** BVA testing for name length boundaries (0, 1, 2, 49, 50, 51 chars).
- **Boundary Values Tested:** 0, 1, 2, 49, 50, 51 characters
- **Severity Justification:** Medium - Validation issue.
- **Priority Justification:** P2 - Fix in next sprint.
- **Resolution:** Verified `validateName()` correctly handles:
  - 0 chars → Exception
  - 1 char → Valid
  - 2 chars → Valid
  - 49 chars → Valid
  - 50 chars → Valid
  - 51 chars → Exception 
- **Regression Notes:** All 6 boundary tests pass.

