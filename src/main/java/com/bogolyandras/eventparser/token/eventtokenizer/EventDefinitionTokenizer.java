package com.bogolyandras.eventparser.token.eventtokenizer;

import com.bogolyandras.eventparser.token.Token;
import com.bogolyandras.eventparser.token.Tokenizer;

public class EventDefinitionTokenizer extends Tokenizer<EventDefinitionTokenKind> {

    @Override
    protected final Token<EventDefinitionTokenKind> parseString(String string) {

        if (string == null) {
            throw new IllegalArgumentException("Must provide a string for parsing!");
        }

        return new Token<>(string, categorize(string));

    }

    private EventDefinitionTokenKind categorize(String string) {

        if (string.matches("([0-9])([0-9])*")) {
            return EventDefinitionTokenKind.Number;
        } else if (string.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            return EventDefinitionTokenKind.HourAndMinute;
        } else if (string.matches("(monday|tuesday|wednesday|thursday|friday|saturday|sunday)")) {
            return EventDefinitionTokenKind.Weekday;
        } else if (string.matches("(day)")) {
            return EventDefinitionTokenKind.DayWordAfterNumber;
        } else if (string.matches("(week|month|year)")) {
            return EventDefinitionTokenKind.WeekMonthYear;
        } else if (string.matches("(of)")) {
            return EventDefinitionTokenKind.OfPreposition;
        } else if (string.matches("(at)")) {
            return EventDefinitionTokenKind.AtPreposition;
        } else {
            throw new IllegalArgumentException("Failed to parse word: '" + string + "'");
        }

    }

}
