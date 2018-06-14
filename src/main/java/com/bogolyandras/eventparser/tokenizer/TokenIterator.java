package com.bogolyandras.eventparser.tokenizer;

import java.util.List;

/**
 * An iterator class that helps with constructing parse trees.
 * @param <T> Enum class of terminal symbols.
 */
public final class TokenIterator<T extends Enum<T>> {

    private final List<Token<T>> tokens;
    private int currentPosition;

    public TokenIterator(List<Token<T>> tokens) {
        this(tokens, 0);
    }

    private TokenIterator(List<Token<T>> tokens, int currentPosition) {
        this.tokens = tokens;
        this.currentPosition = currentPosition;
    }

    public Token<T> next() {
        if (!hasNext()) {
            throw new IllegalAccessError();
        } else {
            return tokens.get(currentPosition++);
        }
    }

    public boolean hasNext() {
        return tokens.size() > currentPosition;
    }

    public TokenIterator<T> copy() {
        return new TokenIterator<>(tokens, currentPosition);
    }

    public void rollback(TokenIterator<T> tokenIterator) {

        if (tokenIterator.tokens != this.tokens) {
            throw new IllegalArgumentException("Cannot roll back to different token iterator!");
        }

        this.currentPosition = tokenIterator.currentPosition;
    }

}
