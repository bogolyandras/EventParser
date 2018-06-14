package com.bogolyandras.eventparser.token.eventtokenizer;

import com.bogolyandras.eventparser.token.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventTokenizerTest {

    private final EventDefinitionTokenizer eventDefinitionTokenizer = new EventDefinitionTokenizer();

    @Test(expected = IllegalArgumentException.class)
    public void testNullStringInput() {
        eventDefinitionTokenizer.parseString(null);
    }

    @Test
    public void testEventParsing1() {
        validateFirstPattern("1 day of week");
    }

    @Test
    public void testEventParsing2() {
        validateFirstPattern("20 day of month");
    }

    @Test
    public void testEventParsing3() {
        validateFirstPattern("3 day of year");
    }

    @Test
    public void testEventParsing4() {
        validateSecondPattern("monday at 11:30");
    }

    @Test
    public void testEventParsing5() {
        validateSecondPattern("wednesday at 13:30");
    }

    private void validateFirstPattern(String pattern) {
        final List<Token<EventDefinitionTokenKind>> tokens = eventDefinitionTokenizer.tokenize(pattern);
        assertEquals(4, tokens.size());
        assertEquals(EventDefinitionTokenKind.Number, tokens.get(0).kind);
        assertEquals(EventDefinitionTokenKind.DayWordAfterNumber, tokens.get(1).kind);
        assertEquals(EventDefinitionTokenKind.OfPreposition, tokens.get(2).kind);
        assertEquals(EventDefinitionTokenKind.WeekMonthYear, tokens.get(3).kind);
    }

    private void validateSecondPattern(String pattern) {
        final List<Token<EventDefinitionTokenKind>> tokens = eventDefinitionTokenizer.tokenize(pattern);
        assertEquals(3, tokens.size());
        assertEquals(EventDefinitionTokenKind.Weekday, tokens.get(0).kind);
        assertEquals(EventDefinitionTokenKind.AtPreposition, tokens.get(1).kind);
        assertEquals(EventDefinitionTokenKind.HourAndMinute, tokens.get(2).kind);
    }

}
