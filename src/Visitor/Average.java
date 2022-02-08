package Visitor;


import Factory.DataFrame;

import java.util.ArrayList;
import java.util.Arrays;

public class Average extends Visitor {
    private int result=0;
    private int cont=0;
    private final String name;

    /**
     * Constructor
     * @param name (String)
     */
    public Average(String name) {
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
            cont+=Integer.parseInt(elem);
            result++;
        }
    }

    /**
     * Result getter
     *
     * @return result
     */
    public int getResults() {
        return this.cont/this.result;
    }

}
