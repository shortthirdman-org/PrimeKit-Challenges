package com.shortthirdman.primekit.codesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a sequence of operations that represent activities in a library, develop a library book circulation tracker.
 * Each operation is one of three types: acquisition, checkout, or reclassify. Operations are provided in the following format:
 * <br/>
 * ["acquisition", "<book category>", "<quantity>", "<price>"] – the library acquires <quantity> books of <book category>,
 * each valued at <price> for insurance purposes.
 * <br/>
 * ["checkout", "<book category>", "<quantity>"] – patrons borrow <quantity> books of <book category>. If books of the specified category have
 * different insurance values, the least valuable ones should be checked out first. It is guaranteed that the library will always have enough books to fulfill all checkout requests.
 * <br/>
 * ["reclassify", "<book category>", "<quantity>", "<original price>", "<new price>"] – the library reclassifies <quantity> books of <book category>
 * to a more valuable edition. It is guaranteed that there are <quantity> books of the specified category with the <original price> value.
 *
 * @author ShortThirdMan
 */
public class LibraryCirculationTracker {

    public List<Integer> processOperations(List<List<String>> operations) {
        Map<String, TreeMap<Integer, Integer>> inventory = new HashMap<>();
        List<Integer> checkoutValues = new ArrayList<>();

        for (List<String> op : operations) {
            String type = op.get(0);

            switch (type) {
                case "acquisition": {
                    String category = op.get(1);
                    int quantity = Integer.parseInt(op.get(2));
                    int price = Integer.parseInt(op.get(3));

                    inventory.putIfAbsent(category, new TreeMap<>());
                    TreeMap<Integer, Integer> priceMap = inventory.get(category);
                    priceMap.put(price, priceMap.getOrDefault(price, 0) + quantity);
                    break;
                }

                case "checkout": {
                    String category = op.get(1);
                    int quantityToCheckout = Integer.parseInt(op.get(2));
                    int insuranceValue = 0;

                    TreeMap<Integer, Integer> priceMap = inventory.get(category);
                    Iterator<Map.Entry<Integer, Integer>> iterator = priceMap.entrySet().iterator();

                    while (quantityToCheckout > 0 && iterator.hasNext()) {
                        Map.Entry<Integer, Integer> entry = iterator.next();
                        int price = entry.getKey();
                        int availableQty = entry.getValue();

                        int usedQty = Math.min(quantityToCheckout, availableQty);
                        insuranceValue += usedQty * price;
                        quantityToCheckout -= usedQty;

                        if (usedQty == availableQty) {
                            iterator.remove();  // No books left at this price
                        } else {
                            priceMap.put(price, availableQty - usedQty);
                        }
                    }

                    checkoutValues.add(insuranceValue);
                    break;
                }

                case "reclassify": {
                    String category = op.get(1);
                    int quantity = Integer.parseInt(op.get(2));
                    int originalPrice = Integer.parseInt(op.get(3));
                    int newPrice = Integer.parseInt(op.get(4));

                    TreeMap<Integer, Integer> priceMap = inventory.get(category);
                    int currentQty = priceMap.getOrDefault(originalPrice, 0);

                    if (quantity > currentQty) {
                        throw new IllegalStateException("Trying to reclassify more books than available.");
                    }

                    // Subtract from original price
                    if (quantity == currentQty) {
                        priceMap.remove(originalPrice);
                    } else {
                        priceMap.put(originalPrice, currentQty - quantity);
                    }

                    // Add to new price
                    priceMap.put(newPrice, priceMap.getOrDefault(newPrice, 0) + quantity);
                    break;
                }
            }
        }

        return checkoutValues;
    }
}
