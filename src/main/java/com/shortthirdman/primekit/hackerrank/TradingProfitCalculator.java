package com.shortthirdman.primekit.hackerrank;

import java.util.*;

/**
 * A quantitative trading firm aims to develop a tool track the net profit/loss of the firm
 * at any point in time. THis tool processes a list of events , and each event fails into one of the
 * four categories:
 * <ul>
 *     <li>
 *         BUY stock quantity: Indicates the purchase of <em>quantity</em> shares of <em>stock</em> at the market price.
 *     </li>
 *     <li>
 *         SELL stock quantity: Indicates the sale of <em>quantity</em> shares of <em>stock</em> at the market price.
 *     </li>
 *     <li>
 *         CHANGE stock price: Indicates a change in the market price of <em>stock</em> by <em>price</em> amount,
 *         which can be positive or negative.
 *     </li>
 *     <li>
 *         QUERY: Requests the net profit/loss from the start of trading until the current time.
 *     </li>
 * </ul>
 * The tool should return a list of numbers corresponding to each QUERY event.
 *
 * @author ShortThirdMan
 */
public class TradingProfitCalculator {

    private static final Integer MAX_QUANTITY = 1000;
    private static final Integer MAX_PRICE_DELTA = 1000;

    public List<Long> getNetProfit(List<String> events) {
        Map<String, Integer> holdings = new HashMap<>();
        Map<String, Integer> prices = new HashMap<>();
        Map<String, Long> costBasis = new HashMap<>();
        List<Long> result = new ArrayList<>();
        long cash = 0;

        if (events.isEmpty() || events.size() > 100000) {
            throw new IllegalArgumentException("Number of events must be between 1 and 10^5.");
        }

        for (String event : events) {
            if (event.length() > 21) {
                throw new IllegalArgumentException("Event string exceeds 21 characters.");
            }

            String[] parts = event.split(" ");
            String type = parts[0];

            switch (type) {
                case "BUY": {
                    if (parts.length != 3) throw new IllegalArgumentException("Invalid BUY format.");
                    String stock = parts[1];
                    int qty = Integer.parseInt(parts[2]);
                    if (qty < 1 || qty >= MAX_QUANTITY) {
                        throw new IllegalArgumentException("BUY quantity out of bounds.");
                    }

                    int price = prices.getOrDefault(stock, 0);
                    holdings.put(stock, holdings.getOrDefault(stock, 0) + qty);
                    costBasis.put(stock, costBasis.getOrDefault(stock, 0L) + (long) qty * price);
                    break;
                }

                case "SELL": {
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Invalid SELL format.");
                    }

                    String stock = parts[1];
                    int qty = Integer.parseInt(parts[2]);
                    if (qty < 1 || qty >= MAX_QUANTITY) {
                        throw new IllegalArgumentException("SELL quantity out of bounds.");
                    }

                    int owned = holdings.getOrDefault(stock, 0);
                    if (qty > owned) {
                        throw new IllegalArgumentException("Not enough shares to sell.");
                    }

                    int price = prices.getOrDefault(stock, 0);
                    holdings.put(stock, owned - qty);
                    costBasis.put(stock, costBasis.get(stock) - (long) qty * price);
                    cash += (long) qty * price;
                    break;
                }

                case "CHANGE": {
                    if (parts.length != 3) throw new IllegalArgumentException("Invalid CHANGE format.");
                    String stock = parts[1];
                    int delta = Integer.parseInt(parts[2]);
                    if (Math.abs(delta) > MAX_PRICE_DELTA) {
                        throw new IllegalArgumentException("Price change exceeds allowed delta.");
                    }

                    prices.put(stock, prices.getOrDefault(stock, 0) + delta);
                    break;
                }

                case "QUERY": {
                    long totalValue = cash;
                    for (String stock : holdings.keySet()) {
                        int qty = holdings.get(stock);
                        int price = prices.getOrDefault(stock, 0);
                        totalValue += (long) qty * price;
                    }

                    long totalCost = 0;
                    for (long cost : costBasis.values()) {
                        totalCost += cost;
                    }

                    result.add(totalValue - totalCost);
                    break;
                }

                default:
                    throw new IllegalArgumentException("Unknown event type: " + type);
            }
        }

        return result;
    }
}
