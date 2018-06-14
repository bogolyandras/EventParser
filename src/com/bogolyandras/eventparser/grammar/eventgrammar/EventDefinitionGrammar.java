package com.bogolyandras.eventparser.grammar.eventgrammar;

import com.bogolyandras.eventparser.grammar.Grammar;
import com.bogolyandras.eventparser.grammar.GrammarRule;
import com.bogolyandras.eventparser.grammar.Symbol;
import com.bogolyandras.eventparser.token.eventtokenizer.EventDefinitionTokenKind;

import java.util.Arrays;

public class EventDefinitionGrammar extends Grammar<EventDefinitionTokenKind> {

    public EventDefinitionGrammar() {
        super(
                Arrays.asList(
                    new GrammarRule<>(
                            "EventWithDayOfSomething",
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Number),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.DayWordAfterNumber),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.OfPreposition),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.WeekMonthYear)
                            )
                    ),
                    new GrammarRule<>(
                            "EventWithWeekDayAtSomeTime",
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Weekday),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.AtPreposition),
                                    Symbol.fromTerminal(EventDefinitionTokenKind.HourAndMinute)
                            )
                    )
                )
        );
    }

}
