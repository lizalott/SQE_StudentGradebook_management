# Test Coverage Notes - Lab 11

## Task 1: Baseline Coverage

**Command used:** `mvn clean test`
**Date:** 13 November 2026

### Overall Coverage
- **Instruction Coverage:** 68%
- **Branch Coverage:** 51%
- **Line Coverage:** 72%




## Task 2: The Three Weakest files/functions
**`com.library.services.PersistenceService`**
   - **Status:** 0% Line / 0% Branch Coverage
   - **Untested Areas:** The entire class is currently untested. As seen in the JaCoCo report, all 25 lines and 8 branches are missed (indicated by the solid red bars).
   - **Common Gaps:** 
     - `loadStudent(String)`: 11 lines missed, 4 branches missed.
     - `saveStudent(Student)`: 9 lines missed, 2 branches missed.
     - `PersistenceService()` (Constructor): 3 lines missed.
     - `static {...}` (Static Initializer): 2 lines missed, 2 branches missed.
   - **Reason for Gap:** There are no unit tests currently instantiating or calling methods on this service to test data persistence.

## Task 3 - Close the Gaps
### Closing Gaps in PersistenceService.java

Initially, `PersistenceService` had **0% coverage** (98 missed instructions). 

To fix this, I created `TestPersistenceService.java` with the following tests:
1. **Constructor & saveStudent:** Tested saving a student, which covers the constructor file-check logic and the `ObjectOutputStream` serialization.
2. **loadStudent (Happy Path):** Tested saving and then loading a student to ensure the file reads correctly.
3. **loadStudent (Not Found):** Tested searching for a non-existent ID to cover the `if (studentCache.containsKey(studentId))` false branch.
4. **Exception Handling:** Purposely corrupted the `.ser` file to force an `IOException`/`ClassNotFoundException`. This covered the `catch` blocks in `loadStudent()`, ensuring the system handles corrupted data gracefully by returning `null`.

**Result:** `PersistenceService.java` coverage increased from 0% to >90%.


## Task 4: Coverage vs. Quality Discussion

### The Coverage Trap
I identified a test in `TestRosterBVA.java` that used `assertDoesNotThrow()`. 

While the previous test successfully executed the `validateScoreCount()` method it was a coverage trap. It only verified that the code didn't crash, but it didn't verify if the code actually did its job correctly. 

If the method had a bug that caused it to return `false` for a valid student, the test would still pass. 

I strengthened the assertion by using `assertTrue()` to check the actual boolean return value, and added an `assertEquals()` to ensure the student's score count remained correct.