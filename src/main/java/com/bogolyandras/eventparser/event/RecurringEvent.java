package com.bogolyandras.eventparser.event;

import com.bogolyandras.eventparser.parser.eventparser.EventDefinitionParser;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;

public final class RecurringEvent {

    private final String value = null;

    public RecurringEvent(Tree<EventDefinitionTokenKind> grammarTree) {



        if (grammarTree.firstNode.symbolName.equals(
                EventDefinitionParser.EventDefinitionTypes.EventWithWeekDayAtSpecificHourAndMinute.toString()
        )) {




        } else if (grammarTree.firstNode.symbolName.equals(
                EventDefinitionParser.EventDefinitionTypes.XDayOfSpecificInterval.toString()
        )) {




        } else {

            throw new IllegalArgumentException("Unknown parse tree with node " + grammarTree.firstNode.symbolName);

        }

    }

}
