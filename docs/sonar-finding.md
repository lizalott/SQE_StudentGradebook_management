SonarCloud Findings: SQE_StudentGradebook_management

Severity Summary
Severity	Count
Blocker	1
Critical	0
Major	0
Medium	1
Low	1
Info	0

Sonar's newer UI labels severity per software quality (Reliability / Security / Maintainability) as Blocker, High, Medium, Low, or Info. The classic Critical / Major / Minor labels map approximately to High / Medium / Low.

Triaged Issue List
#	Rule name	File : line	Quality / Severity	Risk (in my own words)
1	An 'int' is expected rather than a String	src/main/java/com/library/services/Gradebook.java : L59	Reliability / Blocker	A String is passed where the code expects an int, so the call will not behave as intended and can fail or silently do the wrong thing at runtime, which affects the correctness of the gradebook logic.
2	Make the com.library.models.Student class "Serializable" or don't write it (java:S2118)	src/main/java/com/library/services/PersistenceService.java : L37	Reliability / Medium	Student is written to an ObjectOutputStream but is not Serializable, so writeObject throws NotSerializableException. The code catches this as a generic IOException, so students are never saved and the failure is hidden.
3	Make sure this debug feature is deactivated before delivering the code in production (java:S4507)	src/main/java/com/library/services/PersistenceService.java : L42	Security / Low	e.printStackTrace() prints stack traces to the console, which can expose internal class names and file paths and bypasses proper logging.


## Details and Planned Fixes
1. Gradebook.java L59: wrong argument type (Blocker)
Category: Bug (Reliability), tagged cert
Effort: 10 min
Plan: Open line 59, find the String argument, and convert it to an int with Integer.parseInt(...) (with validation), or change the variable's type so an int is passed.
2. PersistenceService.java L37: non-serializable class written (Medium)
Category: Bug (Reliability), tagged serialization
Effort: 15 min
Plan: Make Student implement java.io.Serializable and add private static final long serialVersionUID = 1L;. Also stop swallowing the exception in saveStudent().
3. PersistenceService.java L42: debug feature left active (Low)
Category: Security hotspot / vulnerability, tagged cwe, debug
Effort: 1 min
Plan: Remove e.printStackTrace() and log the error with a proper logger (for example java.util.logging.Logger).



## Task 4: Code Smells Addressed

1. **java:S109 (Magic Numbers):** Extracted grade cut-offs (60, 70, 80, 90) and score limits (0, 100) into named constants.
2. **java:S1192 (Duplicated Strings):** Extracted the score range error message into a constant string format.
3. **java:S1118 (Utility Constructor):** Added a private constructor to `GradebookUtils` to prevent instantiation.
4. **java:S1128 (Unused Imports):** Cleaned up wildcard imports (`import java.util.*`).
5. **java:S1066 (Collapsible Ifs):** Combined nested validation if-statements in `Student.java`


---

## Task 5: Final Re-scan & Quality Gate

### Quality Gate Status:  PASSED

After addressing the identified issues, the final scan yielded the following results:

| Metric | Before Fixes | After Fixes |
|--------|--------------|-------------|
| **Bugs** | 2 | 0 |
| **Vulnerabilities** | 1 | 0 |
| **Code Smells** | 8+ | 0 |
| **Quality Gate** |  Failed |  Passed |

### Regression Testing
To ensure the refactoring and bug fixes did not break existing functionality, the full test suite was executed.

**Test Results:**
- **Total Tests:** 57
- **Failures:** 0
- **Errors:** 0
- **Pass Rate:** 100%

### Key Takeaways
- **Static Analysis is powerful:** It caught a Blocker bug (Type mismatch) and a Security risk (printStackTrace) that standard unit tests missed.
- **Maintainability matters:** Removing magic numbers and duplicated strings makes the code much easier to read and maintain.
- **Quality Gates enforce standards:** Having a pass/fail gate forces the team to keep the codebase clean before merging