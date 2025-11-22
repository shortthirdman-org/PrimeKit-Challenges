package com.shortthirdman.primekit.codesignal;

import java.util.ArrayList;
import java.util.List;

/**
 * You are moderating a newspaper page, and you have to align the text on the page properly.
 * The text is provided to you in the following format:
 * <br/>
 * <p><b>paragraphs</b> is an array of paragraphs, where each paragraph is represented as an array of words;</p>
 * <p><b>aligns</b> is an array representing the alignment of each paragraph from paragraphs - each element is either "LEFT" or "RIGHT";</p>
 * <p><b>width</b> represents the maximum number of characters each line of the output can include.</p>
 * <br/>
 * Your task is to produce a newspaper page according to the following specifications:
 * <br/>
 * <p>For each paragraph paragraphs[i], include all the words paragraphs[i][j] in order, separated by spaces;</p>
 *
 * <p>Include as many words as possible per each page line (the length of the line must be less than or equal to width),
 * and put the next word on a new line if it would exceed the limit;</p>
 *
 * <p>In the case of excess whitespace, words from paragraphs[i] should be aligned according to aligns[i] - if aligns[i] = "LEFT",
 * the line should have trailing spaces, if aligns[i] = RIGHT, it should have leading spaces;</p>
 *
 * <p>Include a border of * characters around all the edges of the result - these characters don't count toward the width,
 * they are just added to make output more pretty.</p>
 *
 * <p>It is guaranteed that it is possible to justify the given paragraphs to the newspaper.</p>
 * <br/>
 * Return the resulting newspaper page as an array of strings.
 *
 * @author ShortThirdMan
 */
public class NewspaperFormatter {

    /**
     * @param paragraphs the input array of paragraphs
     * @param aligns the alignment array
     * @param width the width
     * @return the array of formatted strings
     */
    public String[] formatPage(String[][] paragraphs, String[] aligns, int width) {
        if (width < 5 || width > 100) {
            throw new StringIndexOutOfBoundsException("width should be between 5 and 100");
        }

        List<String> result = new ArrayList<>();
        String border = "*".repeat(width + 2);
        result.add(border);

        for (int i = 0; i < paragraphs.length; i++) {
            String[] words = paragraphs[i];
            String align = aligns[i];
            List<String> lines = new ArrayList<>();
            StringBuilder lineBuilder = new StringBuilder();

            for (String word : words) {
                if (lineBuilder.isEmpty()) {
                    lineBuilder.append(word);
                } else if (lineBuilder.length() + 1 + word.length() <= width) {
                    lineBuilder.append(" ").append(word);
                } else {
                    lines.add(lineBuilder.toString());
                    lineBuilder = new StringBuilder(word);
                }
            }

            if (!lineBuilder.isEmpty()) {
                lines.add(lineBuilder.toString());
            }

            for (String line : lines) {
                int spaces = width - line.length();
                String formatted;
                if ("LEFT".equals(align)) {
                    formatted = line + " ".repeat(spaces);
                } else { // RIGHT
                    formatted = " ".repeat(spaces) + line;
                }
                result.add("*" + formatted + "*");
            }
        }

        result.add(border);
        return result.toArray(new String[0]);
    }
}
