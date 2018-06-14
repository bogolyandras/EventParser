package com.bogolyandras.eventparser.parser.eventparser;

import com.bogolyandras.eventparser.parser.RecursiveDescentParser;
import com.bogolyandras.eventparser.parser.GrammarRule;
import com.bogolyandras.eventparser.parser.Symbol;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;

import java.util.Arrays;

public final class EventDefinitionParser extends RecursiveDescentParser<EventDefinitionTokenKind> {

    public EventDefinitionParser() {
        super(
                Arrays.asList(
                    new GrammarRule<>(
                            EventDefinitionTypes.XDayOfSpecificInterval.toString(),
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Number),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.DayWordAfterNumber),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.OfPreposition),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.WeekMonthYear)
                            )
                    ),
                    new GrammarRule<>(
                            EventDefinitionTypes.EventWithWeekDayAtSomeTime.toString(),
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Weekday),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.AtPreposition),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.HourAndMinute)
                            )
                    )
                )
        );
    }

    public enum EventDefinitionTypes {
        XDayOfSpecificInterval, EventWithWeekDayAtSomeTime
    }

}
