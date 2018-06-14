package com.bogolyandras.eventparser.grammar;

import com.bogolyandras.eventparser.parser.value.Node;
import com.bogolyandras.eventparser.parser.value.Tree;
import com.bogolyandras.eventparser.token.Token;
import com.bogolyandras.eventparser.token.TokenIterator;

import java.util.List;

/**
 * A recursive descent parser that can take in specific grammar rules.
 * For now, it cannot deal with recursive non-terminal symbols.
 * @param <T> The enum class for terminal symbols
 */
public abstract class RecursiveDescentParser<T extends Enum<T>> {

    private final List<GrammarRule<T>> grammarRules;

    public RecursiveDescentParser(List<GrammarRule<T>> grammarRules) {

        if (grammarRules == null) {
            throw new IllegalArgumentException("Must provide grammar rules!");
        }

        this.grammarRules = grammarRules;
    }

    public Tree<T> parse(final List<Token<T>> tokens) {

        for (GrammarRule<T> grammarRule : grammarRules) {
            final TokenIterator<T> tokenIterator = new TokenIterator<>(tokens);
            final Node<T> match = match(grammarRule, tokenIterator);
            if (match != null && !tokenIterator.hasNext()) {
                return new Tree<>(match);
            }
        }

        return null;

    }

    private Node<T> match(GrammarRule<T> grammarRule, final TokenIterator<T> tokenIterator) {

        final Node<T> node = new Node<>(grammarRule.leftHandSide, null);

        for (Symbol<T> symbol : grammarRule.rightHandSide) {

            if (symbol.isNonTerminalSymbol()) {

                boolean matchFound = false;
                for (GrammarRule<T> anotherGrammarRule : grammarRules) {
                    if (anotherGrammarRule.leftHandSide.equals(symbol.getNonTerminalSymbol())) {
                        final TokenIterator<T> backupTokenIterator = tokenIterator.copy();
                        final Node<T> match = match(anotherGrammarRule, tokenIterator);
                        if (match != null) {
                            node.children.add(match);
                            matchFound = true;
                            break;
                        } else {
                            //Rollback
                            tokenIterator.rollback(backupTokenIterator);
                        }
                    }
                }

                if (!matchFound) {
                    //Cancel match for this rule
                    return null;
                }

            } else if (symbol.isTerminalSymbol()) {

                final Token<T> next;
                if (tokenIterator.hasNext()) {
                    next = tokenIterator.next();
                } else {
                    return null;
                }


                if (next.kind.equals(symbol.getTerminalSymbol())) {
                    final Node<T> terminalNode = new Node<>(symbol.getTerminalSymbol().toString(), next.lexeme, node);
                    node.children.add(terminalNode);
                } else {
                    //Cancel match for this rule
                    return null;
                }

            } else {
                throw new IllegalStateException("Unknown type of symbol "  + symbol);
            }

        }

        return node;

    }

}
