package com.shortthirdman.primekit.core.strategy;

public final class PremiumTaxCalculation implements TaxCalculationStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.20; // 20% tax
    }
}
