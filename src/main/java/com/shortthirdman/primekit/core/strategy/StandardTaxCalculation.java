package com.shortthirdman.primekit.core.strategy;

public final class StandardTaxCalculation implements TaxCalculationStrategy {

    @Override
    public double calculateTax(double amount) {
        return amount * 0.10; // 10% tax
    }
}
