package com.shortthirdman.primekit.common;

import lombok.Getter;

@Getter
public enum CustomerTier {

    STANDARD("standard"),
    PREMIUM("premium"),
    ELITE("elite");

    private final String tier;

    CustomerTier(String tier) {
        this.tier = tier;
    }
}
