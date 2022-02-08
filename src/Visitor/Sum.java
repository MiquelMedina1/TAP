package Visitor;


import Factory.DataFrame;
import java.util.ArrayList;
import java.util.Arrays;

public class Sum extends Visitor {
    private int sum=0;
    private final String name;

    /**
     * Constructor
     * @param name (String)
     */
    public Sum(String name) {
        this.name = name;
    }

    /**
     * Method that operates with the composite "leaves"
     *
     * @param file "leaf" of the composite
     */
    @Override
    public void visit(DataFrame file) {
        String[] head= file.getHead();
        int col = Arrays.asList(head).indexOf(this.name);
        ArrayList<String> list = file.structure.get(col);
        for (String elem : list) {
            sum+=Integer.parseInt(elem);
        }
    }

    /**
     * Result getter
     *
     * @return result
     */
    public int getResults() {
        return this.sum;
    }
}
