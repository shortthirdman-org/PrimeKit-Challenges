package com.shortthirdman.primekit.common;

import lombok.Data;

@Data
public class Worker {

    private int quality;
    private int wage;
    private double ratio;

    public Worker(int quality, int wage, double ratio) {
        this.quality = quality;
        this.wage = wage;
        this.ratio = ratio;
    }

    public Worker(int quality, int wage) {
        this.quality = quality;
        this.wage = wage;
        this.ratio = ((double) wage) / quality;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Worker{");
        sb.append("quality=").append(quality);
        sb.append(", wage=").append(wage);
        sb.append(", ratio=").append(ratio);
        sb.append('}');
        return sb.toString();
    }
}
