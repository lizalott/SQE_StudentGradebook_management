package com.library.bva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.library.utils.GradebookUtils;

@DisplayName("Name Validation - Boundary Value Analysis Tests")
public class TestValidateNameBVA {
    
    // ============================================
    // BOUNDARY 1: Domain Min (1 character)
    // Values: 0, 1, 2 characters
    // ============================================
    @Test
    @DisplayName("BVA: 0 characters (value-1) - should fail")
    void testBoundary_0Chars_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(""));
    }
    
    @Test
    @DisplayName("BVA: 1 character (value) - should pass")
    void testBoundary_1Char_Passes() {
        assertDoesNotThrow(() -> GradebookUtils.validateName("A"));
    }
    
    @Test
    @DisplayName("BVA: 2 characters (value+1) - should pass")
    void testBoundary_2Chars_Passes() {
        assertDoesNotThrow(() -> GradebookUtils.validateName("Al"));
    }
    
    // ============================================
    // BOUNDARY 2: Domain Max (50 characters)
    // Values: 49, 50, 51 characters
    // ============================================
    @Test
    @DisplayName("BVA: 49 characters (value-1) - should pass")
    void testBoundary_49Chars_Passes() {
        String name = "A".repeat(49);
        assertDoesNotThrow(() -> GradebookUtils.validateName(name));
    }
    
    @Test
    @DisplayName("BVA: 50 characters (value) - should pass")
    void testBoundary_50Chars_Passes() {
        String name = "A".repeat(50);
        assertDoesNotThrow(() -> GradebookUtils.validateName(name));
    }
    
    @Test
    @DisplayName("BVA: 51 characters (value+1) - should fail")
    void testBoundary_51Chars_ThrowsException() {
        String name = "A".repeat(51);
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(name));
    }
}