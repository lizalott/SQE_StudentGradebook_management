# Unit Testing Notes - Lab 7

**Project:** SQE Library Management System  
**Date:** 2026-09-22  
**Author:** Liza Lott

---

## Fixture Scopes in JUnit 5

### `@BeforeEach` (Function Scope)

**When to use:** Each test needs a fresh instance of the object.

**Example:**
```java
@BeforeEach
void setUp() {
    student = new Student("John Doe", "STU-001");
}