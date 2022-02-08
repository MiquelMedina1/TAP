package Proxy;

import Factory.DataFrame;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler extends Observer implements InvocationHandler {
    /**
     * TestInvocationHandler Constructor
     * @param impl dataframe
     */
    public TestInvocationHandler(DataFrame impl) {
        this.testImpl = impl;
    }

    /**
     * Create proxy
     * @param proxy proxy
     * @param method name of the method
     * @param args Column (label)
     * @return the method invocation
     * @throws Throwable error
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        testImpl.notifyAllObservers(methodName);
        return method.invoke(testImpl, args);
    }

}
