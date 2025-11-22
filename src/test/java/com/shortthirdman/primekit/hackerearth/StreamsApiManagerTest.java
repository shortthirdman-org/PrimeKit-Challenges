package com.shortthirdman.primekit.hackerearth;

import com.shortthirdman.primekit.common.Customer;
import com.shortthirdman.primekit.common.Order;
import com.shortthirdman.primekit.common.OrderItem;
import com.shortthirdman.primekit.common.OrderStats;
import com.shortthirdman.primekit.common.OrderSummary;
import com.shortthirdman.primekit.common.Product;
import com.shortthirdman.primekit.common.SalesStatistics;
import com.shortthirdman.primekit.common.TierAnalysis;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamsApiManagerTest {

    protected List<Product> products;
    protected List<Customer> customers;
    protected List<Order> orders;
    StreamsApiManager manager;

    @BeforeEach
    void setUp() {
        manager = new StreamsApiManager();
        products = List.of(
                new Product("P1", "iPhone 14", "Electronics", new BigDecimal("999.99")),
                new Product("P2", "MacBook Pro", "Electronics", new BigDecimal("1999.99")),
                new Product("P3", "Coffee Maker", "Appliances", new BigDecimal("89.99")),
                new Product("P4", "Running Shoes", "Sportswear", new BigDecimal("129.99")),
                new Product("P5", "Yoga Mat", "Sportswear", new BigDecimal("25.99")),
                new Product("P6", "Water Bottle", "Sportswear", new BigDecimal("12.99")),
                new Product("P7", "Wireless Earbuds", "Electronics", new BigDecimal("159.99")),
                new Product("P8", "Smart Watch", "Electronics", new BigDecimal("349.99")),
                new Product("P9", "Blender", "Appliances", new BigDecimal("79.99")),
                new Product("P10", "Desk Lamp", "Home", new BigDecimal("34.99"))
        );

        customers = List.of(
                new Customer("C1", "John Smith", "john@example.com", LocalDate.of(2020, 1, 15), "elite"),
                new Customer("C2", "Emma Johnson", "emma@example.com", LocalDate.of(2021, 3, 20), "standard"),
                new Customer("C3", "Michael Brown", "michael@example.com", LocalDate.of(2019, 7, 5), "premium"),
                new Customer("C4", "Olivia Wilson", "olivia@example.com", LocalDate.of(2022, 2, 10), "standard"),
                new Customer("C5", "William Davis", "william@example.com", LocalDate.of(2020, 11, 25), "elite")
        );

        orders = List.of(
                new Order("O1", customers.get(0), LocalDate.of(2023, 3, 15),
                        List.of(
                                new OrderItem(products.get(0), 1),
                                new OrderItem(products.get(7), 1)
                        ),
                        "delivered"),
                new Order("O2", customers.get(2), LocalDate.of(2023, 4, 2),
                        List.of(
                                new OrderItem(products.get(1), 1)
                        ),
                        "delivered"),
                new Order("O3", customers.get(1), LocalDate.of(2023, 4, 15),
                        List.of(
                                new OrderItem(products.get(2), 1),
                                new OrderItem(products.get(9), 2)
                        ),
                        "shipped"),
                new Order("O4", customers.get(0), LocalDate.of(2023, 5, 1),
                        List.of(
                                new OrderItem(products.get(3), 1),
                                new OrderItem(products.get(4), 1),
                                new OrderItem(products.get(5), 2)
                        ),
                        "placed"),
                new Order("O5", customers.get(4), LocalDate.of(2023, 5, 5),
                        List.of(
                                new OrderItem(products.get(6), 1)
                        ),
                        "canceled"),
                new Order("O6", customers.get(3), LocalDate.of(2023, 5, 10),
                        List.of(
                                new OrderItem(products.get(8), 1),
                                new OrderItem(products.get(9), 1)
                        ),
                        "placed"),
                new Order("O7", customers.get(2), LocalDate.of(2023, 5, 15),
                        List.of(
                                new OrderItem(products.get(0), 1),
                                new OrderItem(products.get(1), 1)
                        ),
                        "placed"),
                new Order("O8", customers.get(0), LocalDate.of(2023, 5, 20),
                        List.of(
                                new OrderItem(products.get(7), 1)
                        ),
                        "placed")
        );
    }

    @AfterEach
    void tearDown() {
        products = null;
        customers = null;
        orders = null;
    }

    // =======================
    // 1. findAllProductNamesByCategory
    // =======================
    @Test
    void testFindAllProductNamesByCategory() {
        // Positive
        List<String> electronics = manager.findAllProductNamesByCategory(products, "Electronics");
        assertEquals(List.of("iPhone 14", "MacBook Pro", "Wireless Earbuds", "Smart Watch"), electronics);

        // No match
        List<String> none = manager.findAllProductNamesByCategory(products, "NonExistentCategory");
        assertTrue(none.isEmpty());

        // Exceptions
        assertThrows(IllegalArgumentException.class, () -> manager.findAllProductNamesByCategory(products, ""));
        assertThrows(IllegalArgumentException.class, () -> manager.findAllProductNamesByCategory(List.of(), "Electronics"));
    }

    // =======================
    // 2. findProductsByCategoryAndPrice
    // =======================
    @Test
    void testFindProductsByCategoryAndPrice() {
        // Positive
        Product affordable = manager.findProductsByCategoryAndPrice(products, "Sportswear", "50");
        assertEquals("Water Bottle", affordable.name());

        // No match
        assertThrows(IllegalStateException.class, () ->
                manager.findProductsByCategoryAndPrice(products, "Electronics", "10"));

        // Invalid args
        assertThrows(IllegalArgumentException.class, () ->
                manager.findProductsByCategoryAndPrice(List.of(), "", ""));
    }

    // =======================
    // 3. createOrderSummaries
    // =======================
    @Test
    void testCreateOrderSummaries() {
        // Positive
        List<OrderSummary> summaries = manager.createOrderSummaries(orders);
        assertEquals(8, summaries.size());
        assertEquals(2, summaries.getFirst().itemCount());

        // Exception
        assertThrows(IllegalArgumentException.class, () -> manager.createOrderSummaries(List.of()));
    }

    // =======================
    // 4. showAllSortedProducts
    // =======================
    @Test
    void testShowAllSortedProducts() {
        List<Product> sorted = manager.showAllSortedProducts(products);
        assertEquals("Water Bottle", sorted.get(0).name());
        assertEquals("MacBook Pro", sorted.get(sorted.size() - 1).name());

        assertThrows(IllegalArgumentException.class, () -> manager.showAllSortedProducts(List.of()));
    }

    // =======================
    // 5. paginateProducts
    // =======================
    @Test
    void testPaginateProducts() {
        // Positive
        List<Product> page1 = manager.paginateProducts(products, 1, 3);
        assertEquals(3, page1.size());

        // Page too far
        List<Product> emptyPage = manager.paginateProducts(products, 10, 5);
        assertTrue(emptyPage.isEmpty());

        // Invalid args
        assertThrows(IllegalArgumentException.class, () -> manager.paginateProducts(products, 0, 3));
    }

    // =======================
    // 6. createOrderedProducts
    // =======================
    @Test
    void testCreateOrderedProducts() {
        List<Product> ordered = manager.createOrderedProducts(orders);
        assertEquals(10, ordered.size());

        assertThrows(IllegalArgumentException.class, () -> manager.createOrderedProducts(List.of()));
    }

    // =======================
    // 7. calculateTotalRevenue
    // =======================
    @Test
    void testCalculateTotalRevenue() {
        BigDecimal revenue = manager.calculateTotalRevenue(orders);
        BigDecimal expected = BigDecimal.ZERO;
        for (Order o : orders) {
            for (OrderItem i : o.items()) {
                expected = expected.add(i.product().price().multiply(BigDecimal.valueOf(i.quantity())));
            }
        }
        assertEquals(expected, revenue);

        assertThrows(IllegalArgumentException.class, () -> manager.calculateTotalRevenue(List.of()));
    }

    // =======================
    // 8. checkProductsInCategory
    // =======================
    @Test
    void testCheckProductsInCategory() {
        assertTrue(manager.checkProductsInCategory(products, "Home"));
        assertFalse(manager.checkProductsInCategory(products, "NonExistent"));
        assertThrows(IllegalArgumentException.class, () -> manager.checkProductsInCategory(List.of(), "Electronics"));
    }

    // =======================
    // 9. checkProductsInPrice
    // =======================
    @Test
    void testCheckProductsInPrice() {
        assertTrue(manager.checkProductsInPrice(products, "10"));
        assertFalse(manager.checkProductsInPrice(products, "500"));
        assertThrows(IllegalArgumentException.class, () -> manager.checkProductsInPrice(List.of(), "100"));
    }

    // =======================
    // 10. listAllProductCategories
    // =======================
    @Test
    void testListAllProductCategories() {
        assertEquals("Appliances, Electronics, Home, Sportswear", manager.listAllProductCategories(products));
        assertThrows(IllegalArgumentException.class, () -> manager.listAllProductCategories(List.of()));
    }

    // =======================
    // 11. countProductsByCategory
    // =======================
    @Test
    void testCountProductsByCategory() {
        Map<String, Long> counts = manager.countProductsByCategory(products);
        assertEquals(4, counts.get("Electronics"));
        assertEquals(2, counts.get("Appliances"));
        assertEquals(3, counts.get("Sportswear"));
        assertEquals(1, counts.get("Home"));

        assertThrows(IllegalArgumentException.class, () -> manager.countProductsByCategory(List.of()));
    }

    // =======================
    // 12. calculateAveragePriceByCategory
    // =======================
    @Test
    void testCalculateAveragePriceByCategory() {
        Map<String, Double> avg = manager.calculateAveragePriceByCategory(products);
        assertEquals((999.99 + 1999.99 + 159.99 + 349.99) / 4, avg.get("Electronics"));
        assertEquals((89.99 + 79.99) / 2, avg.get("Appliances"));

        assertThrows(IllegalArgumentException.class, () -> manager.calculateAveragePriceByCategory(null));
    }

    // =======================
    // 13. analyzeOrders
    // =======================
    @Test
    void testAnalyzeOrders() {
        Map<String, Map<String, OrderStats>> result = manager.analyzeOrders(orders);
        assertEquals(3, result.get("elite").size());
        assertTrue(result.get("standard").containsKey("shipped"));
        assertTrue(result.get("premium").containsKey("delivered"));

        assertThrows(IllegalArgumentException.class, () -> manager.analyzeOrders(List.of()));
    }

    // =======================
    // 14. analyzeOrdersByTier
    // =======================
    @Test
    void testAnalyzeOrdersByTier() {
        Map<String, TierAnalysis> result = manager.analyzeOrdersByTier(orders);
        result.forEach((k, v) -> System.out.println(k + ": " + v));
        assertEquals(2, result.get("premium").orderCount());

        TierAnalysis eliteAnalysis = result.get("elite");
        assertNotNull(eliteAnalysis);
        assertEquals(4, eliteAnalysis.orderCount());

        assertThrows(IllegalArgumentException.class, () -> manager.analyzeOrdersByTier(null));
    }

    // =======================
    // 15. getProductsByCategoryAndTier
    // =======================
    @Test
    void testGetProductsByCategoryAndTier() {
        List<Product> result = manager.getProductsByCategoryAndTier(orders, "Electronics", "elite");
        assertEquals(3, result.size());

        var actual = List.of(products.get(0), products.get(6), products.get(7));
        assertEquals(new HashSet<>(actual), new HashSet<>(result));

        assertThrows(IllegalArgumentException.class, () -> manager.getProductsByCategoryAndTier(orders, "", "elite"));
    }

    // =======================
    // 16. calculateSalesStatistics
    // =======================
    @Test
    void testCalculateSalesStatistics() {
        SalesStatistics stats = manager.calculateSalesStatistics(orders);
        BigDecimal expectedRevenue = BigDecimal.ZERO;
        long expectedCount = 0;
        for (Order o : orders) {
            for (OrderItem i : o.items()) {
                expectedRevenue = expectedRevenue.add(i.product().price().multiply(BigDecimal.valueOf(i.quantity())));
                expectedCount += i.quantity();
            }
        }
        assertEquals(expectedRevenue, stats.totalRevenue());
        assertEquals(expectedCount, stats.totalProductsSold());

        assertThrows(IllegalArgumentException.class, () -> manager.calculateSalesStatistics(List.of()));
    }

    // =======================
    // 17. listProductsByCategory
    // =======================
    @Test
    void testListProductsByCategory() {
        Map<String, List<Product>> result = manager.listProductsByCategory(products);
        assertEquals(4, result.size());
        assertEquals(4, result.get("Electronics").size());

        assertThrows(IllegalArgumentException.class, () -> manager.listProductsByCategory(null));
    }

    // =======================
    // 18. partitionProductsByPrice
    // =======================
    @Test
    void testPartitionProductsByPrice() {
        Map<Boolean, List<Product>> result = manager.partitionProductsByPrice(products, "100");
        assertFalse(result.get(true).isEmpty());
        assertFalse(result.get(false).isEmpty());

        assertThrows(IllegalArgumentException.class, () -> manager.partitionProductsByPrice(products, ""));
    }

    // =======================
    // 19. collectProductsByPrice
    // =======================
    @Test
    void testCollectProductsByPrice() {
        Map<BigDecimal, String> result = manager.collectProductsByPrice(products);
        assertEquals("iPhone 14", result.get(new BigDecimal("999.99")));
        assertEquals("Smart Watch", result.get(new BigDecimal("349.99")));

        // Merge products with same price
        Product duplicate = new Product("P11", "DuplicateItem", "Electronics", new BigDecimal("349.99"));
        Map<BigDecimal, String> merged = manager.collectProductsByPrice(new ArrayList<>(products) {{ add(duplicate); }});

        assertTrue(merged.entrySet().stream()
                .filter(e -> e.getKey().compareTo(new BigDecimal("349.99")) == 0)
                .anyMatch(e -> e.getValue().contains("Smart Watch") && e.getValue().contains("DuplicateItem")));

        assertThrows(IllegalArgumentException.class, () -> manager.collectProductsByPrice(List.of()));
    }
}