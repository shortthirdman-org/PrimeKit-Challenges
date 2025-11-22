package com.shortthirdman.primekit.common;

import java.time.LocalDate;
import java.util.List;

public record Order(String id,
                    Customer customer,
                    LocalDate orderDate,
                    List<OrderItem> items,
                    String status) {
}
