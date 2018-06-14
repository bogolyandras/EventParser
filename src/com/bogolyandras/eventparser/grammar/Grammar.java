package com.bogolyandras.eventparser.grammar;

import com.bogolyandras.eventparser.parser.Node;
import com.bogolyandras.eventparser.token.Token;

import java.util.List;

public abstract class Grammar<T extends Enum<T>> {

    private final List<GrammarRule<T>> grammarRules;

    public Grammar(List<GrammarRule<T>> grammarRules) {

        if (grammarRules == null) {
            throw new IllegalArgumentException("Must provide grammar rules!");
        }

        this.grammarRules = grammarRules;
    }

    public Node parse(final List<Token<T>> tokens) {

        final int currentPosition = 0;

        for (GrammarRule<T> grammarRule : grammarRules) {
            final Node<T> match = match(grammarRule, new TokenStream<>(tokens));
            if (match != null) {
                return match;
            }
        }

        return null;

    }

    private Node<T> match(GrammarRule<T> grammarRule, final TokenStream<T> tokenStream) {

        final Node<T> node = new Node<>(grammarRule.leftHandSide);

        for (Symbol<T> symbol : grammarRule.rightHandSide) {

            if (symbol.isNonTerminalSymbol()) {

                boolean matchFound = false;
                for (GrammarRule<T> anotherGrammarRule : grammarRules) {
                    if (anotherGrammarRule.leftHandSide.equals(symbol.getNonTerminalSymbol())) {
                        final int currentPosition = tokenStream.currentPosition;
                        final Node<T> match = match(anotherGrammarRule, tokenStream);
                        if (match == null) {
                            //Rollback
                            tokenStream.currentPosition = currentPosition;
                        } else {
                            node.children.add(match);
                            matchFound = true;
                            break;
                        }
                    }
                }

                if (!matchFound) {
                    //Cancel match for this rule
                    return null;
                }

            } else if (symbol.isTerminalSymbol()) {

                final Token<T> next;
                if (tokenStream.hasNext()) {
                    next = tokenStream.next();
                } else {
                    return null;
                }


                if (next.kind.equals(symbol.getTerminalSymbol())) {
                    final Node terminalNode = new Node(symbol.getTerminalSymbol().toString(), next.lexeme);
                    terminalNode.parent = node;
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
