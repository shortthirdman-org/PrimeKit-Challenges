package com.shortthirdman.primekit.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * @author ShortThirdMan
 */
public class FinancialPortfolio {

    private final Map<String, Double> holdings;
    private final StampedLock lock = new StampedLock();
    private static final boolean DEBUG = System.getProperty("DEBUG") != null || System.getenv("DEBUG") != null;

    public FinancialPortfolio() {
        holdings = new HashMap<>();
        // Initial holdings
        holdings.put("AAPL", 100.0);
        holdings.put("GOOGL", 50.0);
        holdings.put("MSFT", 75.0);
    }

    /**
     * @return the total amount of portfolio
     */
    public double getTotalPortfolioValue() {
        long stamp = lock.tryOptimisticRead();
        double total = calculateTotalValue();

        if (!lock.validate(stamp)) {
            // Optimistic read failed, acquire full read lock
            stamp = lock.readLock();
            try {
                total = calculateTotalValue();
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return total;
    }

    private double calculateTotalValue() {
        // Simulate current stock prices
        Map<String, Double> currentPrices = Map.of(
                "AAPL", 150.0,
                "GOOGL", 2800.0,
                "MSFT", 300.0
        );

        return holdings.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * currentPrices.getOrDefault(entry.getKey(), 0.0))
                .sum();
    }

    /**
     * @param symbol the stock ticker symbol
     * @param quantity the quantity to buy
     */
    public void buyStock(String symbol, double quantity) {
        long stamp = lock.writeLock();
        try {
            holdings.merge(symbol, quantity, Double::sum);
            if (DEBUG) {
                System.out.println("Bought " + quantity + " of " + symbol);
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /**
     * @param symbol the stock ticker symbol
     * @param quantity the quantity to buy
     */
    public void sellStock(String symbol, double quantity) {
        long stamp = lock.writeLock();
        try {
            double current = holdings.getOrDefault(symbol, 0.0);
            if (current >= quantity) {
                holdings.put(symbol, current - quantity);
                if (DEBUG) {
                    System.out.println("Sold " + quantity + " of " + symbol);
                }
            } else {
                System.out.println("Insufficient holdings of " + symbol);
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /**
     * @param updates the price updates of the stocks
     */
    public void updatePortfolio(Map<String, Double> updates) {
        long stamp = lock.writeLock();
        try {
            updates.forEach((symbol, quantity) ->
                    holdings.merge(symbol, quantity, Double::sum));
            if (DEBUG) {
                System.out.println("Portfolio updated: " + updates);
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
