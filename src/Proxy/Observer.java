package Proxy;


import Factory.DataFrame;

public abstract class Observer {
    protected DataFrame testImpl;

    /**
     * update Method that updates a method
     * @param name method name
     */
    public void update(String name) {
        System.out.println("Method: " + name);
    }

}
