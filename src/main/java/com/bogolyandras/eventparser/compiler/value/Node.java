package com.bogolyandras.eventparser.compiler.value;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class that can have a parent and multiple children.
 * @param <T> Enum class of the terminal symbols.
 */
public final class Node<T extends Enum<T>> {

    public final String symbolName;
    public final String symbolValue;
    public final Node parent;
    public final List<Node<T>> children = new ArrayList<>();

    public Node(String symbolName, Node parent) {
        this(symbolName, null, parent);
    }

    public Node(String symbolName, String symbolValue, Node parent) {

        if (symbolName == null) {
            throw new IllegalArgumentException("Must provide a symbol name!");
        }

        this.symbolName = symbolName;
        this.symbolValue = symbolValue;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (!symbolName.equals(node.symbolName)) return false;
        if (symbolValue != null ? !symbolValue.equals(node.symbolValue) : node.symbolValue != null) return false;
        if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
        return children.equals(node.children);
    }

    @Override
    public int hashCode() {
        int result = symbolName.hashCode();
        result = 31 * result + (symbolValue != null ? symbolValue.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + children.hashCode();
        return result;
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
