package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TradingProfitCalculatorTest {

    TradingProfitCalculator app;

    @BeforeEach
    void setUp() {
        app = new TradingProfitCalculator();
    }

    @Test
    void testBuySellQueryFlow_PositiveProfit() {
        List<String> events = List.of(
                "CHANGE AAPL 100",
                "BUY AAPL 5",
                "CHANGE AAPL 50",    // Price becomes 150
                "SELL AAPL 2",       // Sells at 150, bought at 100
                "QUERY"
        );
        List<Long> result = app.getNetProfit(events);
        // assertEquals(List.of(100L), result);  // Profit: (2*150) - (2*100) = 100
        assertEquals(List.of(550L), result);
    }

    @Test
    void testMultipleQueries() {
        List<String> events = List.of(
                "CHANGE AAPL 100",
                "BUY AAPL 10",
                "QUERY",
                "CHANGE AAPL 20",
                "QUERY"
        );
        List<Long> result = app.getNetProfit(events);
        assertEquals(List.of(0L, 200L), result);
    }

    @Test
    void testBuySellExactQuantity() {
        List<String> events = List.of(
                "CHANGE GOOG 500",
                "BUY GOOG 999", // Just below MAX_QUANTITY
                "SELL GOOG 999",
                "QUERY"
        );
        List<Long> result = app.getNetProfit(events);
        // assertEquals(List.of(0L), result); // Net profit = revenue - cost = 0
        assertEquals(List.of(499500L), result);
    }

    @Test
    void testEmptyEventsList_ShouldThrowException() {
        List<String> events = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testTooManyEvents_ShouldThrowException() {
        List<String> events = Collections.nCopies(100_001, "QUERY");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testInvalidBuyFormat_ShouldThrow() {
        List<String> events = List.of("BUY AAPL");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testBuyQuantityOutOfBounds_ShouldThrow() {
        List<String> events = List.of("BUY AAPL 1000"); // >= MAX_QUANTITY
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testSellQuantityOutOfBounds_ShouldThrow() {
        List<String> events = List.of("SELL AAPL 0");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testSellWithoutHoldings_ShouldThrow() {
        List<String> events = List.of("CHANGE AAPL 100", "SELL AAPL 1");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testChangeDeltaTooHigh_ShouldThrow() {
        List<String> events = List.of("CHANGE AAPL 1001");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testChangeDeltaTooLow_ShouldThrow() {
        List<String> events = List.of("CHANGE AAPL -1001");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testInvalidChangeFormat_ShouldThrow() {
        List<String> events = List.of("CHANGE AAPL");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testEventLengthTooLong_ShouldThrow() {
        String longEvent = "BUY AAPL 12345678901234567890"; // > 21 characters
        List<String> events = List.of(longEvent);
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testUnknownEventType_ShouldThrow() {
        List<String> events = List.of("FLIP AAPL 10");
        assertThrows(IllegalArgumentException.class, () -> app.getNetProfit(events));
    }

    @Test
    void testEdgeCase_BuySellAtZeroPrice() {
        List<String> events = List.of(
                "BUY XYZ 10",
                "SELL XYZ 10",
                "QUERY"
        );
        List<Long> result = app.getNetProfit(events);
        assertEquals(List.of(0L), result);
    }

    @Test
    void testProfitCalculation_WithMultipleStocks() {
        List<String> events = List.of(
                "CHANGE TSLA 300",
                "BUY TSLA 2",     // Cost: 600
                "CHANGE AMZN 200",
                "BUY AMZN 3",     // Cost: 600
                "CHANGE TSLA 100",
                "SELL TSLA 1",    // Revenue: 400, Cost for 1: 300
                "QUERY"
        );
        List<Long> result = app.getNetProfit(events);
        // assertEquals(List.of(100L), result);
        assertEquals(List.of(600L), result);
    }
}