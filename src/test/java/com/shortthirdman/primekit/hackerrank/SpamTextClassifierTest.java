package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpamTextClassifierTest {

    SpamTextClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new SpamTextClassifier();
    }

    @Test
    void testSpamWhenTwoSpamWordsPresent() {
        List<String> texts = List.of("buy cheap now");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testNotSpamWhenOnlyOneSpamWord() {
        List<String> texts = List.of("buy now");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testNotSpamWhenNoSpamWordsPresent() {
        List<String> texts = List.of("hello world");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testSpamWhenExactlyTwoSpamWords() {
        List<String> texts = List.of("buy cheap");
        List<String> spamWords = List.of("buy", "cheap", "offer");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testSpamWhenMoreThanTwoSpamWords() {
        List<String> texts = List.of("buy cheap offer now");
        List<String> spamWords = List.of("buy", "cheap", "offer");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       CASE INSENSITIVITY
       ------------------------------------------------ */

    @Test
    void testUpperCaseTextSpamWords() {
        List<String> texts = List.of("BUY CHEAP");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testMixedCaseText() {
        List<String> texts = List.of("Buy Cheap");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testMixedCaseSpamWords() {
        List<String> texts = List.of("buy cheap");
        List<String> spamWords = List.of("BUY", "CHEAP");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       MULTIPLE TEXT INPUTS
       ------------------------------------------------ */

    @Test
    void testMultipleTextsMixedResults() {
        List<String> texts = List.of(
                "buy cheap",
                "hello world",
                "cheap buy offer"
        );

        List<String> spamWords = List.of("buy", "cheap", "offer");

        List<String> expected = List.of("spam", "not_spam", "spam");

        assertEquals(expected,
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       DUPLICATE WORDS
       ------------------------------------------------ */

    @Test
    void testDuplicateSpamWordInText() {
        List<String> texts = List.of("buy buy");
        List<String> spamWords = List.of("buy");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testDuplicateSpamWordsInSpamList() {
        List<String> texts = List.of("buy cheap");
        List<String> spamWords = List.of("buy", "buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       EMPTY INPUT TESTS
       ------------------------------------------------ */

    @Test
    void testEmptyTextsList() {
        List<String> texts = List.of();
        List<String> spamWords = List.of("buy");

        assertEquals(List.of(),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testEmptySpamWordsList() {
        List<String> texts = List.of("buy cheap");
        List<String> spamWords = List.of();

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testEmptyStringText() {
        List<String> texts = List.of("");
        List<String> spamWords = List.of("buy");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       WHITESPACE EDGE CASES
       ------------------------------------------------ */

    @Test
    void testMultipleSpacesBetweenWords() {
        List<String> texts = List.of("buy   cheap");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testLeadingSpaces() {
        List<String> texts = List.of("   buy cheap");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testTrailingSpaces() {
        List<String> texts = List.of("buy cheap   ");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testWhitespaceOnlyText() {
        List<String> texts = List.of("     ");
        List<String> spamWords = List.of("buy");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       TOKENIZATION LIMITATIONS
       ------------------------------------------------ */

    @Test
    void testTabSeparatedWords() {
        List<String> texts = List.of("buy\tcheap");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    @Test
    void testCommaSeparatedWords() {
        List<String> texts = List.of("buy, cheap");
        List<String> spamWords = List.of("buy", "cheap");

        assertEquals(List.of("not_spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       UNICODE TEXT
       ------------------------------------------------ */

    @Test
    void testUnicodeSpamWords() {
        List<String> texts = List.of("купить дешево");
        List<String> spamWords = List.of("купить", "дешево");

        assertEquals(List.of("spam"),
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       PARALLEL STREAM CONSISTENCY
       ------------------------------------------------ */

    @Test
    void testParallelStreamProducesSameResults() {
        List<String> texts = List.of(
                "buy cheap",
                "hello world",
                "cheap buy offer"
        );

        List<String> spamWords = List.of("buy", "cheap", "offer");

        List<String> sequential =
                classifier.classifyTexts(texts, spamWords);

        List<String> parallel =
                classifier.classifyTextStream(texts, spamWords);

        assertEquals(sequential, parallel);
    }

    /* ------------------------------------------------
       EXCEPTION TESTS
       ------------------------------------------------ */

    @Test
    void testNullTextsThrowsException() {
        List<String> spamWords = List.of("buy");

        assertThrows(NullPointerException.class, () ->
                classifier.classifyTexts(null, spamWords));
    }

    @Test
    void testNullSpamWordsThrowsException() {
        List<String> texts = List.of("buy cheap");

        assertThrows(NullPointerException.class, () ->
                classifier.classifyTexts(texts, null));
    }

    @Test
    void testTextContainingNullThrowsException() {
        List<String> texts = Arrays.asList("buy cheap", null);
        List<String> spamWords = List.of("buy");

        assertThrows(NullPointerException.class, () ->
                classifier.classifyTexts(texts, spamWords));
    }

    /* ------------------------------------------------
       STREAM VERSION EXCEPTION TESTS
       ------------------------------------------------ */

    @Test
    void testParallelMethodNullTextsThrowsException() {
        List<String> spamWords = List.of("buy");

        assertThrows(NullPointerException.class, () ->
                classifier.classifyTextStream(null, spamWords));
    }

    @Test
    void testParallelMethodNullSpamWordsThrowsException() {
        List<String> texts = List.of("buy cheap");

        assertThrows(NullPointerException.class, () ->
                classifier.classifyTextStream(texts, null));
    }
}