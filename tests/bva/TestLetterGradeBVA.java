package com.library.bva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.library.utils.GradebookUtils;

@DisplayName("Letter Grade - Boundary Value Analysis Tests")
public class TestLetterGradeBVA {
    
    // ============================================
    // BOUNDARY 1: Domain Min (0)
    // Values: -1, 0, 1
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: Domain minimum boundary (0)")
    @CsvSource({
        "-1, INVALID",    // value-1: -1 (below min)
        "0, F",           // value: 0 (at min)
        "1, F"            // value+1: 1 (above min)
    })
    void testBoundary_DomainMin(double score, String expected) {
        if (expected.equals("INVALID")) {
            assertThrows(IllegalArgumentException.class, 
                () -> GradebookUtils.letterGrade(score));
        } else {
            assertEquals(expected, GradebookUtils.letterGrade(score));
        }
    }
    
    // ============================================
    // BOUNDARY 2: F/D Cut-off (60)
    // Values: 59, 60, 61
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: F/D cut-off boundary (60)")
    @CsvSource({
        "59, F",    // value-1: 59 (F)
        "60, D",    // value: 60 (D)
        "61, D"     // value+1: 61 (D)
    })
    void testBoundary_F_D_Cutoff(double score, String expected) {
        assertEquals(expected, GradebookUtils.letterGrade(score));
    }
    
    // ============================================
    // BOUNDARY 3: D/C Cut-off (70)
    // Values: 69, 70, 71
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: D/C cut-off boundary (70)")
    @CsvSource({
        "69, D",    // value-1: 69 (D)
        "70, C",    // value: 70 (C)
        "71, C"     // value+1: 71 (C)
    })
    void testBoundary_D_C_Cutoff(double score, String expected) {
        assertEquals(expected, GradebookUtils.letterGrade(score));
    }
    
    // ============================================
    // BOUNDARY 4: C/B Cut-off (80)
    // Values: 79, 80, 81
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: C/B cut-off boundary (80)")
    @CsvSource({
        "79, C",    // value-1: 79 (C)
        "80, B",    // value: 80 (B)
        "81, B"     // value+1: 81 (B)
    })
    void testBoundary_C_B_Cutoff(double score, String expected) {
        assertEquals(expected, GradebookUtils.letterGrade(score));
    }
    
    // ============================================
    // BOUNDARY 5: A/B Cut-off (90)
    // Values: 89, 90, 91
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: A/B cut-off boundary (90)")
    @CsvSource({
        "89, B",    // value-1: 89 (B)
        "90, A",    // value: 90 (A)
        "91, A"     // value+1: 91 (A)
    })
    void testBoundary_A_B_Cutoff(double score, String expected) {
        assertEquals(expected, GradebookUtils.letterGrade(score));
    }
    
    // ============================================
    // BOUNDARY 6: Domain Max (100)
    // Values: 99, 100, 101
    // ============================================
    @ParameterizedTest
    @DisplayName("BVA: Domain maximum boundary (100)")
    @CsvSource({
        "99, A",          // value-1: 99 (A)
        "100, A",         // value: 100 (A)
        "101, INVALID"    // value+1: 101 (invalid)
    })
    void testBoundary_DomainMax(double score, String expected) {
        if (expected.equals("INVALID")) {
            assertThrows(IllegalArgumentException.class, 
                () -> GradebookUtils.letterGrade(score));
        } else {
            assertEquals(expected, GradebookUtils.letterGrade(score));
        }
    }
}