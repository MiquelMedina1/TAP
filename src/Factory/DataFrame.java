package Factory;

import Composite.AComponent;
import Proxy.Observer;
import Proxy.TestIf;
import Scala.Composite.Composite;
import Visitor.Visitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataFrame implements AComponent, Composite, TestIf {
    public HashMap<Integer, ArrayList<String>> structure;
    public String[] head;
    private final List<Observer> obsList = new ArrayList<>();

    public String[] getHead() {
        return head;
    }

    public DataFrame(){
        structure = new HashMap<>();
    }
    /**
     * At Method
     *
     * @param row row (int)
     * @param col column (String)
     * @return the value of a single item (row) and column label (name)
     */
    public String at(int row, String col) {
        int x = Arrays.asList(head).indexOf(col);
        return structure.get(x).get(row);

    }

    /**
     * Iat Method
     *
     * @param row row (int)
     * @param col column (int)
     * @return a single value for a row and column by integer position
     */
    public String iat(int row, int col) {

        return structure.get(col).get(row);
    }

    /**
     * Columns Method
     *
     * @return number of labels
     */
    public int columns() {
        return head.length;
    }

    /**
     * Size Method
     *
     * @return number of items
     */
    public int size() {
        return structure.get(0).size();
    }

    /**
     * addDad Method
     */
    @Override
    public void addDad(Composite directory) {}

    /**
     * accept Method (Scala)
     */
    @Override
    public void accept(Scala.Visitor.Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Sort Method
     *
     * @param col  col (String)
     * @param type order to follow
     * @return the values of a column in the DataFrame following a certain order
     */
    public Object[] sort(String col, Comparator<String> type) {

        ArrayList<String> l = structure.get(Arrays.asList(head).indexOf(col));
        l.sort(type);
        return l.toArray();
    }

    /**
     * Query Method
     *
     * @param col col (String)
     * @param condition condition to follow
     * @return all elements where a label value fulfills a certain condition
     */
    public Object[] query(String col, Predicate<String> condition) {

        ArrayList<String> l = structure.get(Arrays.asList(head).indexOf(col));
        ArrayList<String> l2 = (ArrayList<String>) l.stream().filter(condition).collect(Collectors.toList());
        return l2.toArray();
    }

    public List<String> getColumn(String col){
        return structure.get(Arrays.asList(head).indexOf(col));
    }

    /**
     * attach Method which adds an observer
     * @param observer observer
     */
    @Override
    public void attach(Observer observer) {
        obsList.add(observer);
    }

    /**
     * accept Method
     * @param v visitor
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * toList Method
     * @return null
     */
    @Override
    public List<AComponent> toList() {
        return null;
    }

    /**
     * set Parent Method
     * @param parent AComponent
     */
    @Override
    public void setParent(AComponent parent) {}

    /**
     * notifyAllObservers Method
     * @param name string
     */
    public void notifyAllObservers(String name) {
        for (Observer observer : obsList) {
            observer.update(name);
        }
    }

    /**
     * Name Getter
     * @return null
     */
    @Override
    public String getName() {return null;}
}
