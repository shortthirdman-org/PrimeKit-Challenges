package com.shortthirdman.primekit.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tweet {

    private int id;
    private int xCoord;
    private int yCoord;

    public Tweet(int id, int xCoord, int yCoord) {
        this.id = id;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
}
