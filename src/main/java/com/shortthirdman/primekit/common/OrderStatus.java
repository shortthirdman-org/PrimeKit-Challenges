package com.shortthirdman.primekit.common;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PLACED("placed"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
