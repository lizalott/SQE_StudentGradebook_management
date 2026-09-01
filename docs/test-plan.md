# Test Plan - GradeBook Module

**Project:** SQE Student Gradebook Management System  
**Module:** GradeBook  
**Version:** v0.2  
**Date:** 2026-08-29  
**Author:** Liza Lott  
**Document Status:** draft



## 1. Introduction

### 1.1 Purpose
This Test Plan defines the testing strategy, approach, resources, and schedule for validating the GradeBook module of the SQE Library Management System. The plan covers functional, negative, and boundary testing to ensure the module meets its requirements and operates reliably in production.

### 1.2 Scope
The GradeBook module is responsible for:
- Managing student records (add, find, remove)
- Recording and validating assessment scores
- Calculating statistics (average, highest, lowest)
- Preventing data integrity violations (duplicate IDs, invalid scores)

This testing effort covers all core functionality of the GradeBook module. Integration testing with other modules is out of scope for this phase.

### 1.3 Objectives
- Validate all functional requirements for the GradeBook module
- Ensure proper handling of edge cases and error conditions
- Verify data integrity constraints are enforced
- Achieve 95%+ test case pass rate
- Identify and document any defects found



## 2. Test Items

The following code artifacts will be tested:

| Artifact | Description | Location |
|----------|-------------|----------|
| **Student Class** | Student model with score management | `src/main/java/com/library/models/Student.java` |
| **GradeBook Class** | Service for managing student grades | `src/main/java/com/library/services/Gradebook.java` |
| **Student Tests** | Unit tests for Student class | `src/test/java/com/library/models/StudentTest.java` |
| **GradeBook Tests** | Unit tests for GradeBook class | `src/test/java/com/library/services/GradebookTest.java` |



## 3. Features to be Tested

### 3.1 Functional Features

| Feature ID | Feature Description | Priority |
|------------|---------------------|----------|
| F1 | Create student with name and ID | High |
| F2 | Add scores to student record | High |
| F3 | Validate scores (0-100 range) | Critical |
| F4 | Calculate average score | High |
| F5 | Find highest score | Medium |
| F6 | Find lowest score | Medium |
| F7 | Prevent duplicate student IDs | High |
| F8 | Case-insensitive student search | Medium |
| F9 | Remove student from gradebook | Medium |
| F10 | Calculate total score sum | Low |

### 3.2 Non-Functional Features

| Feature | Description |
|---------|-------------|
| Performance | Operations complete in < 100ms |
| Reliability | System handles invalid inputs gracefully |
| Maintainability | Code follows Java conventions |
| Testability | All public methods are testable |



## 4. Features Not to be Tested

### 4.1 Excluded Features

| Feature | Justification |
|---------|---------------|
| **Graphical User Interface (UI)** | The GradeBook is a library module, not an application. UI testing is out of scope. |
| **Database Integration** | In-memory storage is used for this phase. Database testing will be covered in a future sprint. |
| **Authentication/Authorization** | Not implemented in this phase; user management is out of scope. |
| **Performance/Load Testing** | The system handles small datasets (< 100 students). Performance testing is not required. |
| **Security Testing** | The module has no external API; security testing is out of scope. |
| **Mobile/Cross-platform Testing** | This is a Java library; platform compatibility is not applicable. |

### 4.2 Feature Exclusion Rationale

**UI Exclusion:** The GradeBook module is a core library component meant to be used by other parts of the system. UI implementation (if any) would be in a separate application layer and should be tested independently.

**Database Exclusion:** Currently using in-memory storage for simplicity. Database integration will be added in v0.3 and will require separate testing.

---

## 5. Approach

### 5.1 Testing Strategy

| Level | Description | Approach |
|-------|-------------|----------|
| **Unit Testing** | Test individual methods in isolation | JUnit 5 framework, Mockito for mocking |
| **Integration Testing** | Test interaction between classes | Integration tests with real objects |
| **Regression Testing** | Ensure fixes don't break existing functionality | Rerun all tests after changes |
| **Manual Testing** | Verify test cases manually | Execute using Java shell/IDE |

### 5.2 Testing Techniques

| Technique | Application |
|-----------|-------------|
| **Equivalence Partitioning** | Group valid/invalid score ranges (0-100) |
| **Boundary Value Analysis** | Test at boundaries (0, 100, -1, 101) |
| **Error Guessing** | Test common error scenarios (null, empty, duplicates) |
| **Exploratory Testing** | Explore unexpected behavior |

### 5.3 Test Environment

| Component | Specification |
|-----------|---------------|
| **Language** | Java 17 |
| **Build Tool** | Maven 3.8+ |
| **Testing Framework** | JUnit 5 (Jupiter) |
| **Assertion Library** | JUnit Assertions |
| **IDE** | IntelliJ IDEA / VS Code |
| **Version Control** | Git / GitHub |

---

## 6. Pass/Fail Criteria

### 6.1 Pass Criteria
The GradeBook module passes testing if ALL of the following conditions are met:

| Criteria | Threshold |
|----------|-----------|
| **Test Case Pass Rate** | ≥ 95% of planned test cases pass |
| **Critical Defects** | Zero critical defects remain open |
| **High Severity Defects** | Zero high severity defects remain open |
| **Code Coverage** | ≥ 90% line coverage |
| **Critical Functionality** | All high-priority features work correctly |

### 6.2 Fail Criteria
The GradeBook module fails testing if ANY of the following conditions occur:

| Criteria | Threshold |
|----------|-----------|
| **Critical Functionality** | Any critical feature fails |
| **Data Integrity** | Data validation allows invalid data |
| **Application Crash** | System crashes during testing |
| **Test Case Pass Rate** | < 85% of test cases pass |
| **Open Critical Defects** | Any critical defect remains unresolved |

### 6.3 Feature-Specific Pass/Fail

| Feature | Pass Condition |
|---------|----------------|
| **Score Validation** | Negative/null/Nan scores are rejected |
| **Average Calculation** | Correct average returned; no division by zero |
| **Duplicate Prevention** | Duplicate IDs are rejected |
| **Student Management** | CRUD operations work correctly |

---

## 7. Test Deliverables

| Deliverable | Description | Location |
|-------------|-------------|----------|
| **Test Plan** | This document | `docs/test-plan.md` |
| **Test Cases** | 12 test cases with specs | `docs/test-cases.md` |
| **Traceability Matrix** | Requirements to test cases mapping | `docs/rtm.md` |
| **Test Execution Results** | Pass/fail results for all tests | `docs/test-cases.md` |
| **Defect Reports** | Issues filed for failures | GitHub Issues |
| **Test Summary Report** | Summary of testing outcomes | `docs/test-summary.md` |

---

## 8. Environmental Needs

### 8.1 Hardware Requirements

| Component | Minimum Specification |
|-----------|----------------------|
| **Processor** | Intel Core i5 or equivalent |
| **RAM** | 8 GB |
| **Storage** | 50 GB free space |

### 8.2 Software Requirements

| Component | Specification |
|-----------|---------------|
| **Operating System** | Windows 10/11, macOS, Linux |
| **Java Development Kit** | JDK 17 or higher |
| **Build Tool** | Maven 3.8+ |
| **Testing Framework** | JUnit 5 |
| **Version Control** | Git 2.30+ |
| **Browser** | Chrome, Firefox, Edge (for GitHub) |

### 8.3 Tools

| Tool | Purpose |
|------|---------|
| **IDE** | Code development and debugging |
| **Maven** | Build and dependency management |
| **JUnit 5** | Unit testing |
| **Git** | Version control |
| **GitHub** | Repository hosting, issue tracking |

---

## 9. Schedule

### 9.1 Testing Timeline

| Phase | Activity | Duration | Start Date | End Date |
|-------|----------|----------|------------|----------|
| 1 | Test Planning | 1 hour | Day 1 | Day 1 |
| 2 | Test Case Design | 1.5 hours | Day 1 | Day 2 |
| 3 | Traceability Matrix | 0.5 hours | Day 2 | Day 2 |
| 4 | Test Execution | 1 hour | Day 2 | Day 3 |
| 5 | Defect Reporting | 0.5 hours | Day 3 | Day 3 |
| 6 | Summary/Reporting | 0.5 hours | Day 3 | Day 4 |

### 9.2 Milestones

| Milestone | Date | Deliverable |
|-----------|------|-------------|
| M1 | Day 1 | Test Plan Completed |
| M2 | Day 2 | Test Cases Completed |
| M3 | Day 2 | RTM Completed |
| M4 | Day 3 | Test Execution Completed |
| M5 | Day 4 | Final Report Completed |

---

## 10. Risks and Contingencies

### 10.1 Risks

| Risk ID | Risk Description | Probability | Impact | Mitigation |
|---------|------------------|-------------|--------|------------|
| R1 | Code changes break existing functionality | Medium | High | Run regression tests |
| R2 | Insufficient test coverage | High | High | Review RTM for gaps |
| R3 | Time constraints | Medium | Medium | Prioritize critical tests |
| R4 | Environment issues | Low | Medium | Use multiple environments |
| R5 | Defects found late | Medium | High | Test early, test often |

### 10.2 Contingencies

| Risk | Contingency Plan |
|------|------------------|
| R1 | Rerun all tests after each change; use CI pipeline |
| R2 | Use code coverage tools to identify gaps |
| R3 | Focus on high-priority test cases first |
| R4 | Have backup environment ready |
| R5 | Execute tests in parallel to save time |

---

## 11. Sign-off

| Role | Name | Signature | Date |
|------|------|-----------|------|
| **Test Lead** | Liza Lott | [Signature] | [Date] |
| **Developer** | Liza Lott | [Signature] | [Date] |
| **Product Owner** | Liza Lott | [Signature] | [Date] |

---

## 12. Revision History

| Version | Date | Author | Description |
|---------|------|--------|-------------|
| v1.0 | 2026-08-29 | Liza Lott | Initial version |