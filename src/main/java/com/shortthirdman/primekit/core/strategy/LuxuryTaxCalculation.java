package com.shortthirdman.primekit.core.strategy;

public final class LuxuryTaxCalculation implements TaxCalculationStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.30; // 30% tax
    }
}
