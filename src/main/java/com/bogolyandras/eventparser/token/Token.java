package com.bogolyandras.eventparser.token;

public class Token<T extends Enum<T>> {

    public final String lexeme;
    public final T kind;

    public Token(String lexeme, T kind) {
        if (lexeme == null) {
            throw new IllegalArgumentException();
        }

        if (kind == null) {
            throw new IllegalArgumentException();
        }

        this.lexeme = lexeme;
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token<?> token = (Token<?>) o;

        if (!lexeme.equals(token.lexeme)) return false;
        return kind.equals(token.kind);
    }

    @Override
    public int hashCode() {
        int result = lexeme.hashCode();
        result = 31 * result + kind.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{" + "'" + lexeme + "'" + ", " + kind + "}";
    }

}
