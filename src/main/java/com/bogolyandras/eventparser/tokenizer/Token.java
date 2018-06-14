package com.bogolyandras.eventparser.tokenizer;

/**
 * A token container class that can hold the lexeme and the specific terminal symbol.
 * @param <T> Enum class of the token symbols.
 */
public class Token<T extends Enum<T>> {

    public final String lexeme;
    public final T kind;

    public Token(String lexeme, T kind) {

        if (lexeme == null) {
            throw new IllegalArgumentException("Must provide a lexeme!");
        }

        if (kind == null) {
            throw new IllegalArgumentException("Must provide the kind of lexeme!");
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
