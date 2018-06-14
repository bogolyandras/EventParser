package com.bogolyandras.eventparser.parser;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Enum<T>> {

    public final String symbolName;
    public final String symbolValue;
    public Node parent;
    public final List<Node> children = new ArrayList<>();

    public Node(String symbolName) {
        this(symbolName, null);
    }

    public Node(String symbolName, String symbolValue) {

        if (symbolName == null) {
            throw new IllegalArgumentException("Must provide a symbol name!");
        }

        this.symbolName = symbolName;
        this.symbolValue = symbolValue;

    }

    @Override
    public String toString() {
        if (symbolValue == null) {
            return "[Node:" + symbolName + "{" + children.toString() + "}]";
        } else {
            return "[Node:" + symbolName + "-" + symbolValue + "]";
        }
    }

}
