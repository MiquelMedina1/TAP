package Proxy;

import Factory.DataFrame;

public class ObserverImpl extends Observer {
    /**
     * ObserverImpl Constructor
     * @param df dataframe
     */
    public ObserverImpl(DataFrame df){
        this.testImpl = df;
        this.testImpl.attach(this);
    }
}
