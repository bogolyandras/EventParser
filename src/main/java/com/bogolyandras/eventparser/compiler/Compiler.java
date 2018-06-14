package com.bogolyandras.eventparser.compiler;

import com.bogolyandras.eventparser.parser.ParseException;
import com.bogolyandras.eventparser.parser.RecursiveDescentParser;
import com.bogolyandras.eventparser.compiler.value.Tree;
import com.bogolyandras.eventparser.tokenizer.IllegalSymbolException;
import com.bogolyandras.eventparser.tokenizer.Token;
import com.bogolyandras.eventparser.tokenizer.Tokenizer;

import java.util.List;

/**
 * A compiler will hold the parser and the tokenizer together
 * @param <T> The type of parser
 * @param <U> The type of non-terminal symbols tied to the parser
 */
public final class Compiler<T extends RecursiveDescentParser<U>, U extends Enum<U>> {

    private final T parser;
    private final Tokenizer<U> tokenizer;

    public Compiler(T parser, Tokenizer<U> tokenizer) {

        if (parser == null) {
            throw new IllegalArgumentException("Must provide a grammar!");
        }

        if (tokenizer == null) {
            throw new IllegalArgumentException("Must provide a tokenizer!");
        }

        this.parser = parser;
        this.tokenizer = tokenizer;
    }

    /**
     * Compiles a sentence into a parse tree
     * @param sentence Any syntactically and gramatically correct sentence
     * @return A parse tree
     */
    public final Tree<U> compile(String sentence) throws IllegalSymbolException, ParseException {

        if (sentence == null) {
            throw new IllegalArgumentException("Sentence must contain text!");
        }

        //Do the tokenization process
        final List<Token<U>> tokens = tokenizer.tokenize(sentence);

        //Do the parsing process
        return parser.parse(tokens);

    }

}
