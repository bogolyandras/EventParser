package com.bogolyandras.eventparser.parser;

import java.util.List;

/**
 * Class for holding grammar rules.
 * @param <T> Enum type of terminal symbols.
 */
public final class GrammarRule<T extends Enum<T>> {

    /**
     * Left hand side must be a non terminal Symbol
     */
    final String leftHandSide;
    final List<Symbol<T>> rightHandSide;

    public GrammarRule(String leftHandSide, List<Symbol<T>> rightHandSide) {

        if (leftHandSide == null) {
            throw new IllegalArgumentException("Must provide a left hand side symbol!");
        }

        if (rightHandSide == null) {
            throw new IllegalArgumentException("Must provide a right hand side symbol!");
        }

        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrammarRule<?> that = (GrammarRule<?>) o;

        if (!leftHandSide.equals(that.leftHandSide)) return false;
        return rightHandSide.equals(that.rightHandSide);
    }

    @Override
    public int hashCode() {
        int result = leftHandSide.hashCode();
        result = 31 * result + rightHandSide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GrammarRule{" +
                "leftHandSide='" + leftHandSide + '\'' +
                ", rightHandSide=" + rightHandSide +
                '}';
    }

}
