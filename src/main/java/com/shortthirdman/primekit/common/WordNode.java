package com.shortthirdman.primekit.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordNode {

    private String word;
    private int numSteps;
}
