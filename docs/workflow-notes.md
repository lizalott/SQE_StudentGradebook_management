f548e81 (HEAD -> feature/rename-field-b, origin/feature/rename-field-b) refactor: use composit key in gradebook
c4a66e7 (origin/main, origin/HEAD, main) Merge branch 'feature/rename-feild-a'
b22e619 (feature/rename-feild-a) changed to branch a
a24b98a refactor: rename roll_no to id_number
b03b61e Merge pull request #6 from lizalott/feature/rename-feild-a
7fc981d (origin/feature/rename-feild-a) refactor: rename roll_no to student_idin grade book
b62a879 feature: add gradebookwith student management
132ef88 Merge pull request #5 from lizalott/feature/add-student
5c3f052 (origin/feature/add-student) docs: adding comprehensive docstring and type to Student class
512c93a feature: adding validation to reject negative scores adain

# Weak commit 1: "changed to branch a"
**Problems Identified:**
 Vague and meaningless description
 No context about what was changed
 Unprofessional tone

 **Rewritten Version:**
  Refactor: rename roll_no to student_id in Gradebook
  This change improves consistency with the Student class which uses student_id as the primary identifier.


## Weak Commit 2: "feature: adding validation to reject negative scores adain"

**Problems Identified:**
 Grammatical error ("adain" instead of "again")
 Too short - doesn't explain the validation logic
 No scope specified

 **Rewritten Version:**
feature: add score validation to Student.addScore() method