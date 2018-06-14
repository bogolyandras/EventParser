package com.bogolyandras.eventparser.parser;

import com.bogolyandras.eventparser.grammar.RecursiveDescentParser;
import com.bogolyandras.eventparser.parser.value.Tree;
import com.bogolyandras.eventparser.token.Token;
import com.bogolyandras.eventparser.token.Tokenizer;

import java.util.List;

public final class Parser<T extends RecursiveDescentParser<U>, U extends Enum<U>> {

    private final T grammar;
    private final Tokenizer<U> tokenizer;

    public Parser(T grammar, Tokenizer<U> tokenizer) {

        if (grammar == null) {
            throw new IllegalArgumentException("Must provide a grammar!");
        }

        if (tokenizer == null) {
            throw new IllegalArgumentException("Must provide a tokenizer!");
        }

        this.grammar = grammar;
        this.tokenizer = tokenizer;
    }

    public final Tree<U> parse(String sentence) {

        if (sentence == null) {
            throw new IllegalArgumentException("Sentence must contain text!");
        }

        final List<Token<U>> tokens = tokenizer.tokenize(sentence);

        return grammar.parse(tokens);

    }

}
