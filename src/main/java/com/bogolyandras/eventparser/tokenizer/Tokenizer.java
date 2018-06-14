package com.bogolyandras.eventparser.tokenizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizer class that split sentences at whitespaces and tokenizes them.
 * @param <T> Enum class of terminal symbols.
 */
public abstract class Tokenizer<T extends Enum<T>> {

    public final List<Token<T>> tokenize(String sentence) {

        if (sentence == null) {
            throw new IllegalArgumentException("Sentence must contain text!");
        }

        if (sentence.equals("")) {
            return Collections.emptyList();
        }

        return Arrays.stream(
                    sentence.split(" ")
                )
                .map(this::parseString)
                .collect(Collectors.toList());

    }

    protected abstract Token<T> parseString(String string) throws IllegalSymbolException;

}
