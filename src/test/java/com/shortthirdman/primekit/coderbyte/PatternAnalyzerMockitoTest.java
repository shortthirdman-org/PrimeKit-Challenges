package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.core.domain.ProductCategory;
import com.shortthirdman.primekit.core.strategy.LuxuryTaxCalculation;
import com.shortthirdman.primekit.core.strategy.PremiumTaxCalculation;
import com.shortthirdman.primekit.core.strategy.StandardTaxCalculation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatternAnalyzerMockitoTest {

    @Mock
    StandardTaxCalculation standardStrategy;

    @Mock
    PremiumTaxCalculation premiumStrategy;

    @Mock
    LuxuryTaxCalculation luxuryStrategy;

    @InjectMocks
    PatternAnalyzer patternAnalyzer;

    @Test
    void verifyStandardStrategyIsInvoked() {
        when(standardStrategy.calculateTax(100)).thenReturn(10.0);

        double result = patternAnalyzer.calculateTaxByStrategy(100, ProductCategory.STANDARD);

        assertEquals(10, result);
        verify(standardStrategy).calculateTax(100);
    }

    @Test
    void verifyPremiumStrategyIsInvoked() {
        when(premiumStrategy.calculateTax(200)).thenReturn(40.0);

        double result = patternAnalyzer.calculateTaxByStrategy(200, ProductCategory.PREMIUM);

        assertEquals(40, result);
        verify(premiumStrategy).calculateTax(200);
    }

    @Test
    void verifyLuxuryStrategyIsInvoked() {
        when(luxuryStrategy.calculateTax(300)).thenReturn(90.0);

        double result = patternAnalyzer.calculateTaxByStrategy(300, ProductCategory.LUXURY);

        assertEquals(90, result);
        verify(luxuryStrategy).calculateTax(300);
    }

    @Test
    void thrownByStrategyIsPropagated() {
        when(luxuryStrategy.calculateTax(anyDouble())).thenThrow(new IllegalArgumentException("OMG"));

        assertThrows(IllegalArgumentException.class,
                () -> patternAnalyzer.calculateTaxByStrategy(250, ProductCategory.LUXURY));
    }
}