package Visitor;

import Composite.Directory;
import Composite.AComponent;
import Factory.DataFrame;


public abstract class Visitor {

    public abstract void visit(DataFrame file);

    /**
     * Method that is visiting the files
     *
     * @param dir directory
     */
    public void visit(Directory dir) {
        for(AComponent elem: dir.getChildren() )
            elem.accept(this);
    }

}
