package com.bogolyandras.eventparser;

import com.bogolyandras.eventparser.grammar.eventgrammar.EventDefinitionGrammar;
import com.bogolyandras.eventparser.parser.Parser;
import com.bogolyandras.eventparser.parser.value.Tree;
import com.bogolyandras.eventparser.token.eventtokenizer.EventDefinitionTokenKind;
import com.bogolyandras.eventparser.token.eventtokenizer.EventDefinitionTokenizer;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String... arguments) {

        final Parser<EventDefinitionGrammar, EventDefinitionTokenKind> parser
                = new Parser<>(new EventDefinitionGrammar(), new EventDefinitionTokenizer());

        for (String exampleSentence : exampleSentences) {
            final Tree<EventDefinitionTokenKind> parse = parser.parse(exampleSentence);
            System.out.println(parse);
        }

    }

    private static final List<String> exampleSentences = Arrays.asList(
            "1 day of week",
            "20 day of month",
            "3 day of year",
            "monday at 11:30",
            "wednesday at 13:30"
    );

}
