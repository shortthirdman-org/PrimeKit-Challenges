package com.shortthirdman.primekit.common;

import java.math.BigDecimal;

public record TierAnalysis(int orderCount, BigDecimal totalRevenue, long totalItems) {

    public double getAverageOrderValue() {
        return orderCount == 0 ? 0 : totalRevenue.doubleValue() / orderCount;
    }

    public TierAnalysis combine(TierAnalysis other) {
        return new TierAnalysis(
                this.orderCount + other.orderCount,
                this.totalRevenue.add(other.totalRevenue),
                this.totalItems + other.totalItems
        );
    }

    public TierAnalysis combine(TierAnalysis a, TierAnalysis b) {
        return new TierAnalysis(
                a.orderCount + b.orderCount,
                a.totalRevenue.add(b.totalRevenue),
                a.totalItems + b.totalItems
        );
    }
}
