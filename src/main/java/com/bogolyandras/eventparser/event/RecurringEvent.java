package com.bogolyandras.eventparser.event;

import com.bogolyandras.eventparser.compiler.value.Node;
import com.bogolyandras.eventparser.parser.eventparser.EventDefinitionParser;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;

import java.util.List;

/**
 * A class that takes a parse tree as an input and stores a natural language
 * representation of it.
 */
public final class RecurringEvent {

    private final String value;

    public RecurringEvent(Tree<EventDefinitionTokenKind> grammarTree) {

        if (grammarTree.firstNode.symbolName.equals(
                EventDefinitionParser.EventDefinitionTypes.EventWithWeekDayAtSpecificHourAndMinute.toString()
        )) {

            //Example_   monday at 11:30
            final List<Node<EventDefinitionTokenKind>> children = grammarTree.firstNode.children;
            this.value = "This event will take place at every " + children.get(0).symbolValue + " " + children.get(1).symbolValue + " " + children.get(2).symbolValue + ".";

        } else if (grammarTree.firstNode.symbolName.equals(
                EventDefinitionParser.EventDefinitionTypes.XDayOfSpecificInterval.toString()
        )) {

            //Example_   1 day of week
            final List<Node<EventDefinitionTokenKind>> children = grammarTree.firstNode.children;

            final String dayNum = children.get(0).symbolValue;
            final char endOfDayNum = dayNum.charAt(dayNum.length() - 1);
            final String ordinalIndicator;
            switch (endOfDayNum) {
                case '1':
                    ordinalIndicator = "st";
                    break;
                case '2':
                    ordinalIndicator = "nd";
                    break;
                case '3':
                    ordinalIndicator = "rd";
                    break;
                default:
                    ordinalIndicator = "th";
            }

            this.value = "This event will take place at " + dayNum + ordinalIndicator + " of every " + children.get(3).symbolValue + ".";

        } else {

            throw new IllegalArgumentException("Unknown parse tree with node " + grammarTree.firstNode.symbolName);

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurringEvent that = (RecurringEvent) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

}
