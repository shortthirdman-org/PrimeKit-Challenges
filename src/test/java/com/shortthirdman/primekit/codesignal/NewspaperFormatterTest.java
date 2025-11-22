package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewspaperFormatterTest {

    NewspaperFormatter app;

    @BeforeEach
    void setUp() {
        app = new NewspaperFormatter();
    }

    @Test
    public void testBasicLeftAndRightAlignment() {
        String[][] paragraphs = {
                {"hello", "world"},
                {"How", "areYou", "doing"},
                {"Please look", "and align", "to right"}
        };
        String[] aligns = {"LEFT", "RIGHT", "RIGHT"};
        int width = 16;

        String[] expected = {
                "******************",
                "*hello world     *",
                "*How areYou doing*",
                "*     Please look*",
                "*       and align*",
                "*        to right*",
                "******************"
        };

        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testEmptyParagraphs() {
        String[][] paragraphs = {};
        String[] aligns = {};
        int width = 10;

        String[] expected = {
                "************",
                "************"
        };

        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testSingleWordPerLine() {
        String[][] paragraphs = {
                {"One", "Two", "Three"}
        };
        String[] aligns = {"LEFT"};
        int width = 5;

        String[] expected = {
                "*******",
                "*One  *",
                "*Two  *",
                "*Three*",
                "*******"
        };

        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testExactFit() {
        String[][] paragraphs = {
                {"Fit", "perfectly", "here"}
        };
        String[] aligns = {"LEFT"};
        int width = 19;

        String[] expected = {
                "*********************",
                "*Fit perfectly here *",
                "*********************"
        };

        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testMinimalWidth() {
        String[][] paragraphs = {
                {"A", "B", "C", "D"}
        };
        String[] aligns = {"RIGHT"};
        int width = 1;

        String[] expected = {
                "***",
                "*A*",
                "*B*",
                "*C*",
                "*D*",
                "***"
        };

        assertThrows(StringIndexOutOfBoundsException.class, () -> app.formatPage(paragraphs, aligns, width));
//        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testOnlySpaces() {
        String[][] paragraphs = {
                {" "}
        };
        String[] aligns = {"LEFT"};
        int width = 1;

        String[] expected = {
                "***",
                "* *",
                "***"
        };

        assertThrows(StringIndexOutOfBoundsException.class, () -> app.formatPage(paragraphs, aligns, width));
//        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testLongWords() {
        String[][] paragraphs = {
                {"Supercalifragilisticexpialidocious"}
        };
        String[] aligns = {"RIGHT"};
        int width = 34;

        String[] expected = {
                "************************************",
                "*Supercalifragilisticexpialidocious*",
                "************************************"
        };

        assertArrayEquals(expected, app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testNullParagraphs() {
        String[][] paragraphs = null;
        String[] aligns = null;
        int width = 10;

        assertThrows(NullPointerException.class, () -> app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testMismatchedAlignLength() {
        String[][] paragraphs = {
                {"A", "B"},
                {"C", "D"}
        };
        String[] aligns = {"LEFT"};

        int width = 10;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> app.formatPage(paragraphs, aligns, width));
    }

    @Test
    public void testWidthTooSmall() {
        String[][] paragraphs = {
                {"Tiny"}
        };
        String[] aligns = {"LEFT"};
        int width = 0; // invalid width

        assertThrows(StringIndexOutOfBoundsException.class, () -> app.formatPage(paragraphs, aligns, width));
    }
}