package com.bogolyandras.eventparser.parser.eventparser;

import com.bogolyandras.eventparser.parser.RecursiveDescentParser;
import com.bogolyandras.eventparser.parser.GrammarRule;
import com.bogolyandras.eventparser.parser.Symbol;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;

import java.util.Arrays;

/**
 * A grammar definition corresponding to the examples below:
 *
 *             "1 day of week",
 *             "20 day of month",
 *             "3 day of year",
 *             "monday at 11:30",
 *             "wednesday at 13:30"
 *
 */
public final class EventDefinitionParser extends RecursiveDescentParser<EventDefinitionTokenKind> {

    public EventDefinitionParser() {
        super(
                Arrays.asList(

                        //Rule 1
                    new GrammarRule<>(
                            EventDefinitionTypes.XDayOfSpecificInterval.toString(),                   //LHS
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Number),             //RHS
                                    Symbol.fromTerminal(EventDefinitionTokenKind.DayWordAfterNumber), //RHS
                                    Symbol.fromTerminal(EventDefinitionTokenKind.OfPreposition),      //RHS
                                    Symbol.fromTerminal(EventDefinitionTokenKind.WeekMonthYear)       //RHS
                            )
                    ),

                        //Rule 2
                    new GrammarRule<>(
                            EventDefinitionTypes.EventWithWeekDayAtSpecificHourAndMinute.toString(),//LHS
                            Arrays.asList(
                                    Symbol.fromTerminal(EventDefinitionTokenKind.Weekday),         //RHS
                                    Symbol.fromTerminal(EventDefinitionTokenKind.AtPreposition),   //RHS
                                    Symbol.fromTerminal(EventDefinitionTokenKind.HourAndMinute)    //RHS
                            )
                    )
                )
        );
    }

    /**
     * Definition of non-terminal symbols
     */
    public enum EventDefinitionTypes {
        XDayOfSpecificInterval, EventWithWeekDayAtSpecificHourAndMinute
    }

}
