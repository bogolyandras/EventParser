package com.bogolyandras.eventparser;

import com.bogolyandras.eventparser.compiler.Compiler;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.event.RecurringEvent;
import com.bogolyandras.eventparser.parser.ParseException;
import com.bogolyandras.eventparser.parser.eventparser.EventDefinitionParser;
import com.bogolyandras.eventparser.tokenizer.IllegalSymbolException;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<String> exampleSentences = Arrays.asList(
            "1 day of week",
            "20 day of month",
            "3 day of year",
            "monday at 11:30",
            "wednesday at 13:30"
    );

    /**
     * A compiler that can take a string and can return a parse tree is initialized here.
     */
    private static final Compiler<EventDefinitionParser, EventDefinitionTokenKind> compiler
            = new Compiler<>(new EventDefinitionParser(), new EventDefinitionTokenizer());

    public static void main(String... arguments) {

        /*
         * Welcome message
         */
        System.out.println("Hey there! I am here to parse your event input and tell you if it is correct or not!");
        System.out.println("Try one of the examples: ");
        System.out.println();
        exampleSentences.forEach(System.out::println);
        System.out.println();
        System.out.println("Please type your input below.");

        try
        {
            //Read a line from the user
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            String sentence = br.readLine();

            //Compile the string into a parse tree
            final Tree<EventDefinitionTokenKind> parseTree = compiler.compile(sentence);

            final RecurringEvent recurringEvent = new RecurringEvent(parseTree);

            //Print the result
            System.out.println();
            System.out.println(recurringEvent);

        } catch (IllegalSymbolException | ParseException e) {

            //We may end up with a tokenization or parsing error.
            System.out.println(e.getMessage());

        } catch (Exception e) {

            //I do not expect to be at this branch.
            System.out.println("An unexpected problem occurred processing your sentence.");
            System.out.println(e.getMessage());

        }

    }

}
