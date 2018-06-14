package com.bogolyandras.eventparser.compiler;

import com.bogolyandras.eventparser.parser.RecursiveDescentParser;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.tokenizer.Token;
import com.bogolyandras.eventparser.tokenizer.Tokenizer;

import java.util.List;

public final class Compiler<T extends RecursiveDescentParser<U>, U extends Enum<U>> {

    private final T grammar;
    private final Tokenizer<U> tokenizer;

    public Compiler(T grammar, Tokenizer<U> tokenizer) {

        if (grammar == null) {
            throw new IllegalArgumentException("Must provide a grammar!");
        }

        if (tokenizer == null) {
            throw new IllegalArgumentException("Must provide a tokenizer!");
        }

        this.grammar = grammar;
        this.tokenizer = tokenizer;
    }

    public final Tree<U> compile(String sentence) {

        if (sentence == null) {
            throw new IllegalArgumentException("Sentence must contain text!");
        }

        final List<Token<U>> tokens = tokenizer.tokenize(sentence);

        return grammar.parse(tokens);

    }

}
