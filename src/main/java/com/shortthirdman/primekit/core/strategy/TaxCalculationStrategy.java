package com.shortthirdman.primekit.core.strategy;

public sealed interface TaxCalculationStrategy permits StandardTaxCalculation, PremiumTaxCalculation, LuxuryTaxCalculation {

    double calculateTax(double amount);
}
