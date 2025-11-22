package com.shortthirdman.primekit.hackerearth;

import com.shortthirdman.primekit.common.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Streams API and Collections Framework
 * @author ShortThirdMan
 * @since 1.1.1
 */
public class StreamsApiManager {

    private static final boolean DEBUG = System.getProperty("DEBUG") != null || System.getenv("DEBUG") != null;

    /**
     * @param products the list of products
     * @param category the category to find products for
     * @return the list of product names
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<String> findAllProductNamesByCategory(List<Product> products, String category) {
        List<String> filteredProductNames = null;

        if (products.isEmpty() || category.isEmpty()) {
            throw new IllegalArgumentException("No products or categories provided");
        }

        filteredProductNames = products.stream()
                .filter(product -> product.category().equals(category))
                .map(Product::name)
                .toList();

        if (DEBUG) {
            System.out.println(filteredProductNames);
        }

        return filteredProductNames;
    }


    /**
     * @param products
     * @param category
     * @param price
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Product findProductsByCategoryAndPrice(List<Product> products, String category, String price) {
        Optional<Product> filteredProduct = Optional.empty();

        if (products.isEmpty() || category.isEmpty() || price.isEmpty()) {
            throw new IllegalArgumentException("No products or categories or price provided");
        }

        filteredProduct = products.stream()
                .filter(product -> product.category().equals(category)) // Sportswear
                .filter(product -> product.price().compareTo(new BigDecimal(price)) < 0) // 20
                .min(Comparator.comparing(Product::price));

        if (DEBUG) {
            filteredProduct.ifPresent(product ->
                    System.out.println("Affordable product: " + product.name() + " - $" + product.price()));
        }

        return filteredProduct.orElseThrow(() -> new IllegalStateException("No product found for " + category + " and price " + price));
    }

    /**
     * @param orders
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<OrderSummary> createOrderSummaries(List<Order> orders) {
        List<OrderSummary> orderSummaries = null;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        orderSummaries = orders.stream()
                .map(order -> new OrderSummary(
                        order.id(),
                        order.items().stream().mapToInt(OrderItem::quantity).sum()
                ))
                .toList();

        if (DEBUG) {
            orderSummaries.forEach(System.out::println);
        }

        return orderSummaries;
    }

    /**
     * @param products
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<Product> showAllSortedProducts(List<Product> products) {
        List<Product> sortedProducts = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        sortedProducts = products.stream()
                .sorted(Comparator.comparing(Product::price))
                .collect(Collectors.toList());

        if (DEBUG) {
            sortedProducts.forEach(product ->
                    System.out.println(product.name() + " - $" + product.price()));
        }

        return sortedProducts;
    }

    /**
     * @param products
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<Product> paginateProducts(List<Product> products, int pageNumber, int pageSize) {
        List<Product> paginatedProducts = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        if (pageNumber < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number or page size is negative");
        }

        paginatedProducts = products.stream()
                .skip((long) pageSize * pageNumber)
                .limit(pageSize)
                .collect(Collectors.toList());

        if (DEBUG) {
            paginatedProducts.forEach(product -> System.out.println(product.name()));
        }

        return paginatedProducts;
    }

    /**
     * @param orders
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<Product> createOrderedProducts(List<Order> orders) {
        List<Product> orderedProducts = null;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        orderedProducts = orders.stream()
                .flatMap(order -> order.items().stream())
                .map(OrderItem::product)
                .distinct()
                .toList();

        if (DEBUG) {
            orderedProducts.forEach(product -> System.out.println(product.name()));
        }

        return orderedProducts;
    }

    /**
     * @param orders
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public BigDecimal calculateTotalRevenue(List<Order> orders) {
        BigDecimal totalRevenue = BigDecimal.ZERO;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        totalRevenue = orders.stream()
                .flatMap(order -> order.items().stream())
                .map(item -> item.product().price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (DEBUG) {
            System.out.println("Total Revenue: $" + totalRevenue);
        }

        return totalRevenue;
    }

    /**
     * @param products
     * @param categoryName
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public boolean checkProductsInCategory(List<Product> products, String categoryName) {
        boolean anyCategorised = false;

        if (products.isEmpty() || categoryName.isEmpty()) {
            throw new IllegalArgumentException("No products or category provided");
        }

        anyCategorised = products.stream()
                .anyMatch(product -> product.category().equals(categoryName)); // Electronics

        return anyCategorised;
    }

    /**
     * Check if there are products of the category
     * @param products the list of products
     * @param categoryName the category name to search
     * @return
     */
    public boolean checkProductsNotInCategory(List<Product> products, String categoryName) {
        boolean noneCategory = true;

        if (products.isEmpty() || categoryName.isEmpty()) {
            throw new IllegalArgumentException("No products or category provided");
        }

         noneCategory = products.stream()
                .noneMatch(product -> product.category().equals(categoryName));

        return noneCategory;
    }

    /**
     * @param products
     * @param priceThreshold
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public boolean checkProductsInPrice(List<Product> products, String priceThreshold) {
        boolean inPriceThreshold = false;

        if  (products == null || products.isEmpty() || priceThreshold.isEmpty()) {
            throw new IllegalArgumentException("No products or threshold price provided");
        }

        inPriceThreshold = products.stream()
                .allMatch(product -> product.price().compareTo(new BigDecimal(priceThreshold)) > 0); // 100

        Optional<Product> anyHighPriced = products.stream()
                .filter(product -> product.price().compareTo(new BigDecimal(priceThreshold)) > 0)
                .findAny();

        if (DEBUG) {
            anyHighPriced.ifPresent(product -> System.out.println("Product in price threshold: " + product));
        }

        return inPriceThreshold;
    }

    /**
     * @param products
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public String listAllProductCategories(List<Product> products) {
        String categories = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        categories = products.stream()
                .map(Product::category)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        if (DEBUG) {
            System.out.println("Categories: " + categories);
        }

        return categories;
    }

    /**
     * Count products by categories
     * @param products the list of products
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<String, Long> countProductsByCategory(List<Product> products) {
        Map<String, Long> countByCategory = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        countByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.counting()));

        if (DEBUG) {
            System.out.println("CountByCategory: " + countByCategory);
        }

        return countByCategory;
    }

    /**
     * Calculate average price by categories
     * @param products the list of products
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<String, Double> calculateAveragePriceByCategory(List<Product> products) {
        Map<String, Double> avgPriceByCategory = null;
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.averagingDouble(p -> p.price().doubleValue())
                ));

        if (DEBUG) {
            avgPriceByCategory.forEach((category, avgPrice) ->
                    System.out.println(category + ": $" + String.format("%.2f", avgPrice)));
        }

        return avgPriceByCategory;
    }

    /**
     * Analyze orders by customer tier and order status, showing order count and total items.
     * @param orders the list of orders
     * @return the order statistics based customer tier and order status
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<String, Map<String, OrderStats>> analyzeOrders(List<Order> orders) {
        Map<String, Map<String, OrderStats>> orderAnalysisByTierAndStatus = null;

        if  (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        orderAnalysisByTierAndStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.customer().tier(),
                        Collectors.groupingBy(
                                Order::status,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        ordersList -> new OrderStats(
                                                ordersList.size(),
                                                ordersList.stream()
                                                        .flatMap(order -> order.items().stream())
                                                        .mapToInt(OrderItem::quantity)
                                                        .sum()
                                        )
                                )
                        )
                ));

        if (DEBUG) {
            orderAnalysisByTierAndStatus.forEach((tier, statusMap) -> {
                System.out.println("Customer Tier: " + tier);
                statusMap.forEach((status, stats) -> {
                    System.out.println("  Status: " + status);
                    System.out.println("    Order Count: " + stats.orderCount());
                    System.out.println("    Total Items: " + stats.totalItems());
                });
            });
        }

        return orderAnalysisByTierAndStatus;
    }

    /**
     * Analyze orders by customer tier
     * @param orders the list of orders
     * @return
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<String, TierAnalysis> analyzeOrdersByTier(List<Order> orders) {
        Map<String, TierAnalysis> tierAnalysis = null;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        tierAnalysis = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.customer().tier(),
                        Collectors.reducing(
                                new TierAnalysis(0, BigDecimal.ZERO, 0), // identity
                                order -> {
                                    BigDecimal orderTotal = order.items().stream()
                                            .map(item -> item.product().price()
                                                    .multiply(BigDecimal.valueOf(item.quantity())))
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                                    long itemCount = order.items().stream()
                                            .mapToLong(OrderItem::quantity)
                                            .sum();

                                    return new TierAnalysis(1, orderTotal, itemCount);
                                },
                                TierAnalysis::combine
                        )
                ));

        if (DEBUG) {
            tierAnalysis.forEach((tier, analysis) -> {
                System.out.println("Tier: " + tier);
                System.out.println("  Orders: " + analysis.orderCount());
                System.out.println("  Total Revenue: $" + analysis.totalRevenue());
                System.out.println("  Total Items: " + analysis.totalItems());
                System.out.printf("  Avg. Order Value: $%.2f%n", analysis.getAverageOrderValue());
            });
        }
        return tierAnalysis;
    }

    /**
     * Find products filtered by category and tier
     * @param orders the list of orders
     * @param category the product category
     * @param tier the customer tier
     * @return list of filtered {@link Product}
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public List<Product> getProductsByCategoryAndTier(List<Order> orders, String category, String tier) {
        List<Product> filteredProducts = null;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        if (category == null || category.isEmpty() || tier == null || tier.isEmpty()) {
            throw new IllegalArgumentException("Product category or customer tier must not be null or empty");
        }

        filteredProducts = orders.parallelStream()
                .filter(order -> order.customer().tier().equals(tier)) //"premium"
                .flatMap(order -> order.items().stream())
                .map(OrderItem::product)
                .filter(product -> product.category().equals(category)) //"Electronics"
                .distinct()
                .toList();

        if (DEBUG) {
            System.out.println("Products (" + tier + " - " + category + "): ");
            filteredProducts.forEach(product -> {
                System.out.println("  Category: " + product.category());
                System.out.println("  Price: $" + product.price());
                System.out.println("  Name: " + product.name());
            });
        }

        return filteredProducts;
    }

    /**
     * Calculate both the total revenue and count of products sold
     * @param orders the list of orders
     * @return {@link SalesStatistics}
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public SalesStatistics calculateSalesStatistics(List<Order> orders) {
        SalesStatistics salesStats = null;

        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("No orders provided");
        }

        salesStats = orders.stream()
                .flatMap(order -> order.items().stream())
                .collect(Collectors.teeing(
                        Collectors.mapping(
                                item -> item.product().price().multiply(BigDecimal.valueOf(item.quantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        ),
                        Collectors.summingLong(OrderItem::quantity),
                        SalesStatistics::new
                ));

        if (DEBUG) {
            System.out.println("Total Revenue: $" + salesStats.totalRevenue());
            System.out.println("Total Products Sold: " + salesStats.totalProductsSold());
        }

        return salesStats;
    }

    /**
     * Find products by category
     * @param products the list of products
     * @return a map of category and list of {@link Product}
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<String, List<Product>> listProductsByCategory(List<Product> products) {
        Map<String, List<Product>> productsByCategory = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category));

        if (DEBUG) {
            productsByCategory.forEach((category, prods) -> {
                System.out.println(category + ":");
                prods.forEach(p -> System.out.println("  - " + p.name()));
            });
        }

        return productsByCategory;
    }

    /**
     * Divide products into "expensive" and "affordable" categories.
     * @param products the list of products
     * @param price the threshold price for partition
     * @return map of expensive and affordable products by price
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<Boolean, List<Product>> partitionProductsByPrice(List<Product> products, String price) {
        Map<Boolean, List<Product>> pricePartition = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        if (price == null || price.isEmpty()) {
            throw new IllegalArgumentException("Price must not be null or empty");
        }

        pricePartition = products.stream()
                .collect(Collectors.partitioningBy(
                        product -> product.price().compareTo(new BigDecimal(price)) > 0 //"100"
                ));

        if (DEBUG) {
            System.out.println("Expensive products:");
            pricePartition.get(true).forEach(p -> System.out.println("- " + p.name() + " ($" + p.price() + ")"));

            System.out.println("\nAffordable products:");
            pricePartition.get(false).forEach(p -> System.out.println("- " + p.name() + " ($" + p.price() + ")"));
        }

        return pricePartition;
    }

    /**
     * Collect products by price, merging names if prices are the same.
     * @param products the list of products
     * @return the map of prices and matching products
     * @throws IllegalArgumentException in case of malformed or missing arguments
     */
    public Map<BigDecimal, String> collectProductsByPrice(List<Product> products) {
        Map<BigDecimal, String> priceToNames = null;

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("No products provided");
        }

        priceToNames = products.stream()
                .collect(Collectors.toMap(
                        Product::price,
                        Product::name,
                        (name1, name2) -> name1 + ", " + name2
                ));

        if (DEBUG) {
            System.out.println("Product names by Price");
            priceToNames.forEach((price, name) -> System.out.println("  - " + name));
        }

        return priceToNames;
    }
}
