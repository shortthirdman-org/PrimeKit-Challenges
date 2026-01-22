package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.core.domain.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PatternAnalyzerTest {

    private static PatternAnalyzer analyzer;

    @BeforeAll
    static void setUp() {
        analyzer = new PatternAnalyzer();
    }

    // ==============================================================
    //  calculateTaxByStrategy() POSITIVE TESTS
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByStrategy - STANDARD tax should be 10%")
    void testStrategyStandardTax() {
        double tax = analyzer.calculateTaxByStrategy(100.00, ProductCategory.STANDARD);
        Assertions.assertEquals(10.00, tax, 0.001);
    }

    @Test
    @DisplayName("calculateTaxByStrategy - PREMIUM tax should be 20%")
    void testStrategyPremiumTax() {
        double tax = analyzer.calculateTaxByStrategy(200.00, ProductCategory.PREMIUM);
        Assertions.assertEquals(40.00, tax, 0.001);
    }

    @Test
    @DisplayName("calculateTaxByStrategy - LUXURY tax should be 30%")
    void testStrategyLuxuryTax() {
        double tax = analyzer.calculateTaxByStrategy(300.00, ProductCategory.LUXURY);
        Assertions.assertEquals(90.00, tax, 0.001);
    }

    // ==============================================================
    //  calculateTaxByCategory() POSITIVE TESTS
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByCategory - STANDARD tax should be 10%")
    void testCategoryStandardTax() {
        double tax = analyzer.calculateTaxByCategory(100.00, ProductCategory.STANDARD);
        Assertions.assertEquals(10.00, tax, 0.001);
    }

    @Test
    @DisplayName("calculateTaxByCategory - PREMIUM tax should be 20%")
    void testCategoryPremiumTax() {
        double tax = analyzer.calculateTaxByCategory(200.00, ProductCategory.PREMIUM);
        Assertions.assertEquals(40.00, tax, 0.001);
    }

    @Test
    @DisplayName("calculateTaxByCategory - LUXURY tax should be 30%")
    void testCategoryLuxuryTax() {
        double tax = analyzer.calculateTaxByCategory(300.00, ProductCategory.LUXURY);
        Assertions.assertEquals(90.00, tax, 0.001);
    }



    // ==============================================================
    //  NEGATIVE INPUT TESTS (invalid numbers)
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByStrategy - negative amount allowed but calculation valid")
    void testStrategyNegativeAmount() {
        double tax = analyzer.calculateTaxByStrategy(-100.00, ProductCategory.STANDARD);
        Assertions.assertEquals(-10.00, tax, 0.001);
    }

    @Test
    @DisplayName("calculateTaxByCategory - negative amount allowed but calculation valid")
    void testCategoryNegativeAmount() {
        double tax = analyzer.calculateTaxByCategory(-100.00, ProductCategory.LUXURY);
        Assertions.assertEquals(-30.00, tax, 0.001);
    }

    // ==============================================================
    //  EDGE CASES
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByStrategy - zero amount should return zero")
    void testStrategyZeroAmount() {
        double tax = analyzer.calculateTaxByStrategy(0.0, ProductCategory.PREMIUM);
        Assertions.assertEquals(0.0, tax);
    }

    @Test
    @DisplayName("calculateTaxByCategory - zero amount should return zero")
    void testCategoryZeroAmount() {
        double tax = analyzer.calculateTaxByCategory(0.0, ProductCategory.LUXURY);
        Assertions.assertEquals(0.0, tax);
    }

    @Test
    @DisplayName("calculateTaxByStrategy - very large amount precision test")
    void testStrategyLargeAmount() {
        double tax = analyzer.calculateTaxByStrategy(1_000_000.0, ProductCategory.LUXURY);
        Assertions.assertEquals(300_000.0, tax);
    }

    @Test
    @DisplayName("calculateTaxByCategory - very large amount precision test")
    void testCategoryLargeAmount() {
        double tax = analyzer.calculateTaxByCategory(1_000_000.0, ProductCategory.STANDARD);
        Assertions.assertEquals(100_000.0, tax);
    }

    // ==============================================================
    //  NULL CATEGORY (EXCEPTION TESTS)
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByStrategy - null category throws IllegalArgumentException")
    void testStrategyNullCategory() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> analyzer.calculateTaxByStrategy(50.0, null));
    }

    @Test
    @DisplayName("calculateTaxByCategory - null category throws IllegalArgumentException")
    void testCategoryNullCategory() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> analyzer.calculateTaxByCategory(50.0, null));
    }

    // ==============================================================
    //  UNSUPPORTED CATEGORY (just in case enum grows)
    // ==============================================================

    @Test
    @DisplayName("calculateTaxByStrategy - unsupported category throws IllegalArgumentException")
    void testStrategyUnsupportedCategory() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> analyzer.calculateTaxByStrategy(100.0, ProductCategory.valueOf("UNKNOWN")));
    }

    @Test
    @DisplayName("calculateTaxByCategory - unsupported category throws IllegalArgumentException")
    void testCategoryUnsupportedCategory() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> analyzer.calculateTaxByCategory(100.0, ProductCategory.valueOf("UNKNOWN")));
    }
}