package Composite;

import Visitor.Visitor;

import java.util.*;
import java.util.function.Predicate;

public class Directory implements AComponent {
    private final String name;
    private final List<AComponent> children;
    private AComponent parent = null;

    /**
     * Directory Constructor
     *
     * @param name name of the directory
     */
    public Directory(String name) {
        this.name = name;
        children = new LinkedList<>();
    }

    /**
     * ToString Method
     *
     * @return string
     */
    public String toString() {
        String path = "/";
        if (parent != null)
            path = parent + "/";
        return path + name;
    }

    /**
     * ToList method
     *
     * @return list
     */
    public List<AComponent> toList() {
        List<AComponent> result = new LinkedList<>();
        result.add(this);
        for (AComponent child : children)
            result.addAll(child.toList());
        return result;
    }

    /**
     * Accept method that calls visit method
     *
     * @param v visitor
     */
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Add a child to a directory
     *
     * @param child child
     */
    public void addChild(AComponent child) {
        children.add(child);
        child.setParent(this);
    }

    /**
     * Children getter
     *
     * @return children
     */
    public List<AComponent> getChildren() {
        return children;
    }

    /**
     * Parent setter
     *
     * @param parent parent directory
     */
    public void setParent(AComponent parent) {
        this.parent = parent;

    }

    /**
     * Name getter
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String at(int i, String col) {
        String x = "";
        for (AComponent child : children) {
            x+=" "+child.at(i, col);
        }
        return x;
    }

    @Override
    public String iat(int i, int j) {
        String x = "";
        for (AComponent child : children) {
            x+=" "+child.iat(i, j);
        }
        return x;
    }

    @Override
    public int columns() {
        int col = 0;
        for (AComponent child : children) {
            col += child.columns();
        }
        return col;
    }

    @Override
    public int size() {
        int size = 0;
        for (AComponent child : children) {
            size += child.size();
        }
        return size;
    }

    /**
     * Sort method with composite
     *
     * @param s    Directory name
     * @param type Order to sort
     * @return return the values of a column in the DataFrame following a certain order in the composite tree
     */
    @Override
    public Object[] sort(String s, Comparator<String> type) {
        Object[] vec =null;
        for (AComponent child : children) {
            vec=(child.sort(s, type));
        }
        return vec;
    }

    /**
     * Query method with composite
     *
     * @param col       col (String)
     * @param condition condition to follow
     * @return return all elements where a label value fulfills a certain condition in the composite tree
     */
    @Override
    public Object[] query(String col, Predicate<String> condition) {
        Object[] vec =null;
        for (AComponent child : children) {
            vec=child.query(col, condition);
        }
        return vec;
    }

}
