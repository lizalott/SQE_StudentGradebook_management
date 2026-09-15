package com.library.ep;

import com.library.utils.GradebookUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Name Validation Equivalence Partitioning Tests")
public class TestValidateName {
    
    // ============================================
    // CLASS: Valid name (letters, spaces, hyphens)
    // ============================================
    @Test
    @DisplayName("Valid name should pass validation")
    void testValidateName_ValidName_Passes() {
        assertDoesNotThrow(() -> GradebookUtils.validateName("John Doe-Smith"));
        assertDoesNotThrow(() -> GradebookUtils.validateName("Mary Johnson"));
        assertDoesNotThrow(() -> GradebookUtils.validateName("Jean-Luc Picard"));
    }
    
    // ============================================
    // CLASS: Empty/Null string (Invalid)
    // ============================================
    @Test
    @DisplayName("Empty name should throw exception")
    void testValidateName_Empty_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(""));
    }
    
    @Test
    @DisplayName("Null name should throw exception")
    void testValidateName_Null_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(null));
    }
    
    // ============================================
    // CLASS: Over 50 characters (Invalid)
    // ============================================
    @Test
    @DisplayName("Name over 50 characters should throw exception")
    void testValidateName_OverLength_ThrowsException() {
        String longName = "This is a very long name that exceeds fifty characters in length";
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(longName));
    }
    
    // ============================================
    // CLASS: Contains digits (Invalid)
    // ============================================
    @ParameterizedTest
    @DisplayName("Names with digits should throw exception")
    @ValueSource(strings = {"John123", "Jane Doe 2nd", "Student42"})
    void testValidateName_ContainsDigits_ThrowsException(String name) {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(name));
    }
    
    // ============================================
    // CLASS: Contains special characters (Invalid)
    // ============================================
    @ParameterizedTest
    @DisplayName("Names with special characters should throw exception")
    @ValueSource(strings = {"John@Doe", "Jane!Smith", "Student#1", "John.Doe"})
    void testValidateName_ContainsSpecialChars_ThrowsException(String name) {
        assertThrows(IllegalArgumentException.class, 
            () -> GradebookUtils.validateName(name));
    }
}