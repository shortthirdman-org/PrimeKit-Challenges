package com.shortthirdman.primekit.common;

import java.time.LocalDate;

public record Customer(String id,
                       String name,
                       String email,
                       LocalDate registrationDate,
                       String tier) {
}
