package com.bogolyandras.eventparser.token.eventtokenizer;

public enum EventDefinitionTokenKind {

    /**
     * Integer number, for example: 0 or 12
     */
    Number,

    /**
     * Hour and minute in the format of 12:32
     */
    HourAndMinute,

    /**
     * Weekday, like monday, tuesday, wednesday, thursday, friday, saturday, sunday
     */
    Weekday,

    /**
     * the 'day' word
     */
    DayWordAfterNumber,

    /**
     * The word 'week', 'month' or 'year'
     */
    WeekMonthYear,

    /**
     * The word 'of'
     */
    OfPreposition,

    /**
     * The word 'at'
     */
    AtPreposition

}
