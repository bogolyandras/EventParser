package com.bogolyandras.eventparser.grammar;

public class Symbol<T extends Enum<T>> {

    private final T terminalSymbol;
    private final String nonTerminalSymbol;

    private Symbol(T terminalSymbol, String nonTerminalSymbol) {

        if (!Boolean.logicalXor(terminalSymbol == null, nonTerminalSymbol == null)) {
            throw new IllegalArgumentException("Only one type of symbol can be specified!");
        }

        this.terminalSymbol = terminalSymbol;
        this.nonTerminalSymbol = nonTerminalSymbol;
    }

    public static <T extends Enum<T>> Symbol<T> fromTerminal(T terminalSymbol) {
        return new Symbol<>(terminalSymbol, null);
    }

    public static <T extends Enum<T>> Symbol<T> fromNonTerminal(String nonTerminalSymbol, Class<T> tClass) {
        return new Symbol<>(null, nonTerminalSymbol);
    }

    public boolean isTerminalSymbol() {
        return terminalSymbol != null;
    }

    public T getTerminalSymbol() {
        if (terminalSymbol == null) {
            throw new IllegalStateException();
        }
        return terminalSymbol;
    }

    public boolean isNonTerminalSymbol() {
        return nonTerminalSymbol != null;
    }

    public String getNonTerminalSymbol() {
        if (nonTerminalSymbol == null) {
            throw new IllegalStateException();
        }
        return nonTerminalSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol<?> symbol = (Symbol<?>) o;

        if (terminalSymbol != null ? !terminalSymbol.equals(symbol.terminalSymbol) : symbol.terminalSymbol != null)
            return false;
        return nonTerminalSymbol != null ? nonTerminalSymbol.equals(symbol.nonTerminalSymbol) : symbol.nonTerminalSymbol == null;
    }

    @Override
    public int hashCode() {
        int result = terminalSymbol != null ? terminalSymbol.hashCode() : 0;
        result = 31 * result + (nonTerminalSymbol != null ? nonTerminalSymbol.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (terminalSymbol != null) {
            return "{Terminal " + terminalSymbol + "}";
        } else {
            return "{NonTerminal " + nonTerminalSymbol + "}";
        }
    }

}
