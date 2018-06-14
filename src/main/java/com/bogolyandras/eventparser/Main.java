package com.bogolyandras.eventparser;

import com.bogolyandras.eventparser.parser.eventparser.EventDefinitionParser;
import com.bogolyandras.eventparser.compiler.Compiler;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenizer;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String... arguments) {

        final Compiler<EventDefinitionParser, EventDefinitionTokenKind> compiler
                = new Compiler<>(new EventDefinitionParser(), new EventDefinitionTokenizer());

        for (String exampleSentence : exampleSentences) {
            final Tree<EventDefinitionTokenKind> parse = compiler.compile(exampleSentence);
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
