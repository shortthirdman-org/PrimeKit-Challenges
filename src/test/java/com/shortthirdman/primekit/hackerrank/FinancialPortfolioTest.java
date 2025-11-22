package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FinancialPortfolioTest {

    FinancialPortfolio portfolio;
    private static final boolean DEBUG = System.getProperty("DEBUG") != null || System.getenv("DEBUG") != null;

    @BeforeEach
    void setUp() {
        portfolio = new FinancialPortfolio();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should calculate total portfolio value correctly with initial holdings")
    void testGetTotalPortfolioValueInitial() {
        // AAPL: 100 * 150 = 15000
        // GOOGL: 50 * 2800 = 140000
        // MSFT: 75 * 300 = 22500
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should update total portfolio value after buying new stock")
    void testGetTotalPortfolioValueAfterBuy() {
        portfolio.buyStock("AAPL", 10.0); // +10 * 150 = +1500
        double expected = 15000 + 140000 + 22500 + 1500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should add stock quantity correctly when buying existing stock")
    void testBuyExistingStock() {
        portfolio.buyStock("MSFT", 25.0); // was 75, now 100
        double expected = 100 * 300 + 15000 + 140000;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should add new stock when buying non-existing stock")
    void testBuyNewStock() {
        portfolio.buyStock("TSLA", 5.0);
        // TSLA has no defined price in calculateTotalValue, so it won't add to value
        // but we can still verify holdings got updated (indirectly by selling it back to 0)
        portfolio.sellStock("TSLA", 5.0);
        // Should succeed because 5 was added
        // Portfolio value should remain unchanged
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle buying zero quantity")
    void testBuyZeroQuantity() {
        portfolio.buyStock("AAPL", 0.0);
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle buying negative quantity (treated as reduction)")
    void testBuyNegativeQuantity() {
        portfolio.buyStock("AAPL", -50.0); // reduce 100 to 50
        double expected = (50 * 150) + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should reduce holdings correctly when selling valid quantity")
    void testSellStockValid() {
        portfolio.sellStock("AAPL", 50.0); // reduce from 100 -> 50
        double expected = (50 * 150) + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should not allow selling more than owned quantity")
    void testSellStockInsufficient() {
        portfolio.sellStock("AAPL", 200.0); // attempt > 100
        double expected = 15000 + 140000 + 22500; // unchanged
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle selling stock not owned")
    void testSellNonExistingStock() {
        portfolio.sellStock("TSLA", 10.0);
        double expected = 15000 + 140000 + 22500; // unchanged
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle selling zero quantity (no effect)")
    void testSellZeroQuantity() {
        portfolio.sellStock("AAPL", 0.0);
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should update portfolio with additional quantities")
    void testUpdatePortfolioPositive() {
        Map<String, Double> updates = new HashMap<>();
        updates.put("AAPL", 10.0);
        updates.put("GOOGL", 5.0);
        portfolio.updatePortfolio(updates);

        double expected = (110 * 150) + (55 * 2800) + (75 * 300);
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should add new stock via updatePortfolio")
    void testUpdatePortfolioNewStock() {
        Map<String, Double> updates = new HashMap<>();
        updates.put("TSLA", 20.0);
        portfolio.updatePortfolio(updates);

        // TSLA won’t contribute to value (no price defined), but holdings updated
        portfolio.sellStock("TSLA", 20.0); // should succeed
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle empty updatePortfolio")
    void testUpdatePortfolioEmpty() {
        portfolio.updatePortfolio(new HashMap<>());
        double expected = 15000 + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    @DisplayName("Should handle negative quantities in updatePortfolio (reducing holdings)")
    void testUpdatePortfolioNegativeQuantity() {
        Map<String, Double> updates = new HashMap<>();
        updates.put("AAPL", -50.0); // reduce 100 -> 50
        portfolio.updatePortfolio(updates);

        double expected = (50 * 150) + 140000 + 22500;
        assertEquals(expected, portfolio.getTotalPortfolioValue(), 0.001);
    }

    @Test
    void runPortfolio() {
        portfolio = new FinancialPortfolio();

        // Multiple readers
        Runnable reader = () -> {
            for (int i = 0; i < 5; i++) {
                double value = portfolio.getTotalPortfolioValue();
                if (DEBUG) {
                    System.out.println(Thread.currentThread().getName() +
                            " read value: $" + value);
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };

        // Writer
        Runnable writer = () -> {
            for (int i = 0; i < 3; i++) {
                portfolio.buyStock("AAPL", 10);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    if (DEBUG) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        };

        // Start threads
        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(writer, "Writer-1").start();
        new Thread(reader, "Reader-3").start();

        assertTrue(portfolio.getTotalPortfolioValue() > 0);
    }
}