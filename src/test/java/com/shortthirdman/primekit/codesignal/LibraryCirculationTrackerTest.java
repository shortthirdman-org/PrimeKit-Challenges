package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryCirculationTrackerTest {

    LibraryCirculationTracker app;

    @BeforeEach
    void setUp() {
        app = new LibraryCirculationTracker();
    }

    @Test
    public void testNativeInput() {
        List<List<String>> operations = Arrays.asList(
                Arrays.asList("acquisition", "fiction", "5", "100"),
                Arrays.asList("acquisition", "fiction", "3", "200"),
                Arrays.asList("checkout", "fiction", "6"),
                Arrays.asList("reclassify", "fiction", "1", "200", "300"),
                Arrays.asList("checkout", "fiction", "1")
        );

        List<Integer> result = app.processOperations(operations);
        System.out.println(result);  // Output: [800, 300]
    }

    @Test
    public void testSingleAcquisitionAndCheckout() {
        List<List<String>> ops = Arrays.asList(
                Arrays.asList("acquisition", "fiction", "2", "100"),
                Arrays.asList("checkout", "fiction", "1")
        );
        List<Integer> expected = List.of(100);
        assertEquals(expected, app.processOperations(ops));
    }

    @Test
    public void testMultiplePricesAndCheckoutOrder() {
        List<List<String>> ops = Arrays.asList(
                Arrays.asList("acquisition", "fiction", "3", "50"),
                Arrays.asList("acquisition", "fiction", "2", "100"),
                Arrays.asList("checkout", "fiction", "4")
        );
        // Should take three @50 and one @100 = 3*50 + 1*100 = 250
        List<Integer> expected = Arrays.asList(250);
        assertEquals(expected, app.processOperations(ops));
    }

    @Test
    public void testReclassifyIncreasesValue() {
        List<List<String>> ops = Arrays.asList(
                Arrays.asList("acquisition", "reference", "2", "60"),
                Arrays.asList("reclassify", "reference", "1", "60", "120"),
                Arrays.asList("checkout", "reference", "2")
        );
        // Inventory after reclassify: one @60, one @120; checkout 2 -> 60 + 120 = 180
        List<Integer> expected = Arrays.asList(180);
        assertEquals(expected, app.processOperations(ops));
    }

    @Test
    public void testComplexSequence() {
        List<List<String>> ops = Arrays.asList(
                Arrays.asList("acquisition", "fiction", "2", "100"),
                Arrays.asList("acquisition", "reference", "3", "60"),
                Arrays.asList("checkout", "fiction", "1"),
                Arrays.asList("checkout", "reference", "1"),
                Arrays.asList("reclassify", "reference", "1", "60", "100"),
                Arrays.asList("checkout", "reference", "1"),
                Arrays.asList("checkout", "reference", "1")
        );
        List<Integer> expected = Arrays.asList(100, 60, 60, 100);
        assertEquals(expected, app.processOperations(ops));
    }

    @Test
    public void testReclassifyMoreThanAvailableThrows() {
        List<List<String>> ops = Arrays.asList(
                Arrays.asList("acquisition", "fiction", "1", "100"),
                Arrays.asList("reclassify", "fiction", "2", "100", "200")
        );
        assertThrows(IllegalStateException.class, () -> {
            app.processOperations(ops);
        });
    }
}