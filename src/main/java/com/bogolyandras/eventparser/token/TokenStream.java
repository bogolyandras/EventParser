package com.bogolyandras.eventparser.token;

import java.util.List;

public final class TokenStream<T extends Enum<T>> {

    private final List<Token<T>> tokens;
    public int currentPosition = 0;

    public TokenStream(List<Token<T>> tokens) {
        this.tokens = tokens;
    }

    public Token<T> next() {
        if (!hasNext()) {
            throw new IllegalAccessError();
        } else {
            return tokens.get(currentPosition++);
        }
    }

    public boolean hasNext() {
        return tokens.size() > 0;
    }

}
