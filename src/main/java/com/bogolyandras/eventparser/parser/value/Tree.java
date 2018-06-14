package com.bogolyandras.eventparser.parser.value;

public class Tree<T extends Enum<T>> {

    public final Node<T> firstNode;

    public Tree(Node<T> firstNode) {
        if (firstNode == null) {
            throw new IllegalArgumentException("Must provide a node for the tree!");
        }

        this.firstNode = firstNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree<?> tree = (Tree<?>) o;

        return firstNode.equals(tree.firstNode);
    }

    @Override
    public int hashCode() {
        return firstNode.hashCode();
    }

    @Override
    public String toString() {
        return "Tree{{" + firstNode + "}}";
    }

}
