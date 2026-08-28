# Triage Meeting Log - Sprint v0.2

**Date:** 2026-08-25  
**Meeting Type:** Sprint Planning & Defect Triage  
**Project:** SQE Library Management System  
**Milestone:** v0.2 — Grade Statistics  
**Issues Reviewed:** 5  


---
 Issue Prioritization Summary

| Priority Order | Issue ID | Title | Severity | Priority | Decision |
|----------------|----------|-------|----------|----------|----------|
| 1 | #1 | Negative scores accepted in Student.addScore() | Critical | P0 | **Fix Now** |
| 2 | #2 | ZeroDivisionError when calculating average | High | P1 | **Fix Now** |
| 3 | #3 | Duplicate student IDs allowed in Gradebook | High | P1 | **Fix Now** |
| 4 | #4 | Case-sensitive name comparison | Medium | P2 | **Deferred** |
| 5 | #5 | Averages not rounded to 2 decimal places | Medium | P3 | **Won't Fix** |

---

##  Detailed Triage Analysis

Issue #1: Negative scores accepted in Student.addScore() (NO Validation)

| Field | Value |
|-------|-------|
| **Title** | Negative scores accepted in Student.addScore() - no validation |
| **Severity** | Critical |
| **Priority** | P0 |
| **Type** | Bug |
| **Status** | ✅ **Accepted - Priority 1** |

Severity Justification
**Critical** - This is a data integrity issue. Allowing negative scores means:
- Invalid data enters the system
- All downstream calculations become corrupted
- Reports and analytics become unreliable
- The system cannot be trusted for official records

Priority Justification
**P0 (Highest)** - This must be fixed immediately because:
- Data corruption affects every other feature
- It undermines user trust in the system
- Fixing it now prevents cascading issues later
- The fix is straightforward (add validation)

Trade-off Analysis
*Severity vs Priority: While this is technically a "simple" validation fix, the business impact of corrupt data makes it P0 priority. The cost of fixing this now is minimal compared to the cost of dealing with corrupted data later.*

Fix Plan
1. Add validation in `Student.addScore()`
2. Reject negative scores with `IllegalArgumentException`
3. Reject scores > 100 with `IllegalArgumentException`
4. Update unit tests
5. Estimated effort: 2 hours

---

### Issue #2: ZeroDivisionError when calculating average

| Field | Value |
|-------|-------|
| **Title** | ZeroDivisionError when calculating average for student with no scores |
| **Severity** | High |
| **Priority** | P1 |
| **Type** | Bug |
| **Status** | ✅ **Accepted - Priority 2** |

#### Severity Justification
**High** - Application crashes when:
- Viewing a student profile with no scores
- Generating reports for new students
- Running analytics on empty data sets

#### Priority Justification
**P1 (High)** - This needs to be fixed in this sprint because:
- It's a user-facing crash (bad user experience)
- Common edge case (new students have no scores)
- Fix is simple (guard clause)
- Blocks proper report generation

#### Trade-off Analysis
*Severity vs Priority: A High severity bug generally maps to P1 priority. While this doesn't corrupt data, the application crash creates a poor user experience. The fix is simple and low risk, so we prioritize it above UI polish issues.*

#### Fix Plan
1. Add null/empty check in `calculateAverage()`
2. Return 0.0 or `Optional.empty()` for empty scores
3. Update documentation to reflect behavior
4. Estimated effort: 1 hour

---

### Issue #3: Duplicate student IDs allowed in Gradebook

| Field | Value |
|-------|-------|
| **Title** | Duplicate student IDs allowed in Gradebook |
| **Severity** | High |
| **Priority** | P1 |
| **Type** | Bug |
| **Status** | ✅ **Accepted - Priority 3** |

#### Severity Justification
**High** - This causes:
- Data inconsistency (two students with same ID)
- Reports showing duplicate entries
- Inability to uniquely identify students
- Potential data loss when duplicates exist

#### Priority Justification
**P1 (High)** - Must be fixed because:
- Student ID is a primary key/unique identifier
- Data integrity is critical for any database system
- The fix is straightforward (add duplicate check)
- Prevents long-term data problems

#### Trade-off Analysis
*Severity vs Priority: High severity aligns with P1 priority. Even though duplicate IDs might be rare, the impact when it occurs is significant (data integrity breach). We prioritize this over cosmetic issues.*

#### Fix Plan
1. Add duplicate check in `Gradebook.addStudent()`
2. Use studentId as key in the Map
3. Throw `IllegalArgumentException` for duplicates
4. Update unit tests
5. Estimated effort: 1.5 hours

---

### Issue #4: Case-sensitive name comparison

| Field | Value |
|-------|-------|
| **Title** | Case-sensitive name comparison in Student lookups |
| **Severity** | Medium |
| **Priority** | P2 |
| **Type** | Enhancement |
| **Status** | ⏸️ **Deferred - Priority 4** |

#### Severity Justification
**Medium** - This affects:
- User experience when searching
- Efficiency of lookups (users must know exact case)
- Data entry flexibility

#### Priority Justification
**P2 (Medium)** - Deferred because:
- Workaround exists (use exact case)
- Doesn't corrupt data or crash
- More critical bugs need attention first
- Can be fixed in next sprint

#### Deferral Reason
*This is a usability improvement rather than a critical bug. Users can work around it by using the correct case. Given the more critical issues (data integrity and crashes), this can wait.*

#### Fix Plan (Future Sprint)
1. Implement case-insensitive search using `equalsIgnoreCase()`
2. Add regex support for partial matches
3. Update search UI to be more flexible
4. Estimated effort: 3 hours

#### Status: **status:wontfix** for this sprint

---

### Issue #5: Averages not rounded to 2 decimal places

| Field | Value |
|-------|-------|
| **Title** | Averages not rounded to 2 decimal places |
| **Severity** | Medium |
| **Priority** | P3 |
| **Type** | Bug |
| **Status** | ❌ **Won't Fix - Priority 5** |

#### Severity Justification
**Medium** - This causes:
- Poor display quality in reports
- Minor inconvenience for users
- Less professional appearance

#### Priority Justification
**P3 (Low)** - Won't fix because:
- No data corruption
- No application crashes
- Pure presentation issue
- Low business impact
- Other priorities are higher

#### Won't Fix Justification
**Low Impact, Out of Scope**  
After careful consideration, we've decided not to fix this issue for the v0.2 release because:
1. **Low Business Impact**: The raw values are still mathematically correct
2. **Workaround Exists**: Users can format the output in reports
3. **Limited Scope**: This is a display issue, not a functional issue
4. **Resource Constraints**: Limited development time available
5. **Future Enhancement**: Can be fixed when we implement proper report formatting

#### Rationale
*This issue represents a "nice to have" rather than a "must have". The raw double values work correctly for all calculations; they just don't look polished. Given our sprint capacity, we're focusing on fixing defects that affect functionality or data integrity.*

#### Status: **status:wontfix**

---

## 📈 Prioritization Rationale Summary

### The Decision Framework

We used the **Severity × Priority Matrix** to make decisions:

| Severity\Priority | P0 (Now) | P1 (Soon) | P2 (Later) | P3 (Maybe) |
|-------------------|----------|-----------|------------|------------|
| **Critical** | ✅ Fix Now | - | - | - |
| **High** | - | ✅ Fix Now | - | - |
| **Medium** | - | - | ⏸️ Defer | ❌ Won't Fix |
| **Low** | - | - | - | ❌ Won't Fix |

### Trade-off Analysis for Issue #1 vs Issue #5

**Issue #1 (Negative Scores) vs Issue #5 (Rounding)**

*Why #1 is P0 while #5 is Won't Fix:*

1. **Data Integrity vs Presentation**
   - #1 corrupts data → Business critical
   - #5 only affects display → Minimal impact

2. **Error Handling vs Polish**
   - #1 produces incorrect results → Can't be ignored
   - #5 produces correct but ugly results → Acceptable

3. **User Trust vs User Experience**
   - #1 undermines trust in the system → Must fix
   - #5 only affects aesthetics → Can defer

4. **Fix Effort vs Risk**
   - #1 is simple fix with low risk → Prioritize
   - #5 is simple fix but low impact → Low priority

### Trade-off Analysis for Issue #2 vs Issue #4

**Issue #2 (Division by Zero) vs Issue #4 (Case Sensitivity)**

*Why #2 is P1 while #4 is Deferred:*

1. **System Stability vs Convenience**
   - #2 causes application crashes → Can't ship
   - #4 only causes inconvenience → Can ship

2. **Edge Case Handling vs Feature Enhancement**
   - #2 affects all empty-score scenarios → Critical path
   - #4 affects search convenience → Nice to have

3. **User Frustration Level**
   - #2: User sees error → High frustration
   - #4: User tries again with correct case → Low frustration

4. **Fix Completeness**
   - #2: Fixed with simple guard clause → Quick win
   - #4: Requires search architecture changes → Larger effort

---

## 🏷️ Label Updates Applied

| Issue | Previous Labels | Added Labels | New Status |
|-------|-----------------|--------------|------------|
| #1 | type:bug, severity:critical, priority:P0 | - | ✅ Accepted |
| #2 | type:bug, severity:high, priority:P1 | - | ✅ Accepted |
| #3 | type:bug, severity:high, priority:P1 | - | ✅ Accepted |
| #4 | type:bug, severity:medium, priority:P2 | status:wontfix | ⏸️ Deferred |
| #5 | type:bug, severity:medium, priority:P3 | status:wontfix | ❌ Won't Fix |

---

## 📋 Sprint Commitment

### This Sprint (v0.2)

**Will Fix (3 issues):**
1. ✅ #1 - Negative scores accepted → **Critical data integrity fix**
2. ✅ #2 - ZeroDivisionError → **Application stability fix**
3. ✅ #3 - Duplicate IDs → **Data consistency fix**

**Total Estimated Effort:** 4.5 hours

### Future Sprint (v0.3)

**Deferred (1 issue):**
4. ⏸️ #4 - Case-sensitive search → **UX improvement**

**Not Planned:**
5. ❌ #5 - Rounding → **Won't fix in near term**

---

## ✅ Triage Meeting Sign-off

| Role | Name | Decision |
|------|------|----------|
| Product Owner | [Your Name] | ✅ Accepted triage decisions |
| QA Lead | [Your Name] | ✅ Agreed with prioritization |
| Developer | [Your Name] | ✅ Ready to implement fixes |

---

## 📎 Related Documents

- [Issue #1](https://github.com/YOUR_USERNAME/sqe-library-management/issues/1)
- [Issue #2](https://github.com/YOUR_USERNAME/sqe-library-management/issues/2)
- [Issue #3](https://github.com/YOUR_USERNAME/sqe-library-management/issues/3)
- [Issue #4](https://github.com/YOUR_USERNAME/sqe-library-management/issues/4)
- [Issue #5](https://github.com/YOUR_USERNAME/sqe-library-management/issues/5)

---

**Triage Completed:** ✅  
**Next Steps:** Begin fixing the 3 accepted issues (Task 3)