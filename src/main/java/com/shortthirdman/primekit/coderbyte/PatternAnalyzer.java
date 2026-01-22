package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.core.domain.ProductCategory;
import com.shortthirdman.primekit.core.funcinterface.TaxCalculator;
import com.shortthirdman.primekit.core.strategy.LuxuryTaxCalculation;
import com.shortthirdman.primekit.core.strategy.PremiumTaxCalculation;
import com.shortthirdman.primekit.core.strategy.StandardTaxCalculation;
import com.shortthirdman.primekit.core.strategy.TaxCalculationStrategy;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * Calculates tax by category and strategy
 * @since 1.1.1
 * @author ShortThirdMan
 */
public class PatternAnalyzer {

    private static final Boolean DEBUG = System.getenv("DEBUG") != null || System.getProperty("DEBUG") != null;

    public double calculateTaxByStrategy(double baseAmount, ProductCategory category) {
        if (Objects.isNull(category)) {
            throw new IllegalArgumentException("Category is null");
        }

        TaxCalculationStrategy strategy = switch (category) {
            case STANDARD -> new StandardTaxCalculation();
            case PREMIUM -> new PremiumTaxCalculation();
            case LUXURY -> new LuxuryTaxCalculation();
            default -> throw new IllegalArgumentException("Unsupported product category");
        };

        double taxAmount = strategy.calculateTax(baseAmount);
        if (DEBUG) {
            System.out.println("[CalculateTaxByStrategy] Tax for $" + baseAmount + " (" + category + "): $" + taxAmount);
        }

        return taxAmount;
    }

    public double calculateTaxByCategory(double baseAmount, ProductCategory category) {
        if (Objects.isNull(category)) {
            throw new IllegalArgumentException("Category is null");
        }

        TaxCalculator calculator = switch (category) {
            case STANDARD -> amount1 -> amount1 * 0.10; // 10% tax
            case PREMIUM -> amount1 -> amount1 * 0.20; // 20% tax
            case LUXURY -> amount1 -> amount1 * 0.30; // 30% tax
            default -> throw new IllegalArgumentException("Unsupported product category");
        };

        double taxAmount = calculator.calculate(baseAmount);
        if (DEBUG) {
            System.out.println("[CalculateTaxByCategory] Tax for $" + baseAmount + " (" + category + "): $" + taxAmount);
        }

        return taxAmount;
    }
}
