package com.bogolyandras.eventparser.compiler;

import com.bogolyandras.eventparser.parser.ParseException;
import com.bogolyandras.eventparser.parser.eventparser.EventDefinitionParser;
import com.bogolyandras.eventparser.tokenizer.IllegalSymbolException;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenKind;
import com.bogolyandras.eventparser.tokenizer.eventtokenizer.EventDefinitionTokenizer;
import org.junit.Test;

public class CompilerTest {

    private final static Compiler<EventDefinitionParser, EventDefinitionTokenKind> compiler
            = new Compiler<>(new EventDefinitionParser(), new EventDefinitionTokenizer());

    @Test
    public void testEventDefinitionCompiling1() {

        compiler.compile("1 day of week");

    }

    @Test
    public void testEventDefinitionCompiling2() {

        compiler.compile("20 day of month");

    }

    @Test
    public void testEventDefinitionCompiling3() {

        compiler.compile("3 day of year");

    }

    @Test
    public void testEventDefinitionCompiling4() {

        compiler.compile("monday at 11:30");

    }

    @Test
    public void testEventDefinitionCompiling5() {

        compiler.compile("wednesday at 13:30");

    }

    @Test(expected = IllegalSymbolException.class)
    public void testEventDefinitionCompiling6() {

        compiler.compile("asd at 13:30");

    }

    @Test(expected = IllegalSymbolException.class)
    public void testEventDefinitionCompiling7() {

        compiler.compile("wednesday at 13f30");

    }

    @Test(expected = ParseException.class)
    public void testEventDefinitionCompiling8() {

        compiler.compile("wednesday at 1330");

    }

    @Test(expected = ParseException.class)
    public void testEventDefinitionCompiling9() {

        compiler.compile("wednesday at 13:30 of");

    }

}
