package chain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestChain extends Chain {
    private Method method;
    public TestChain(Method method) {
        this.method = method;
    }
    @Override
    public void fill(Method method) {
    }

    @Override
    protected void invoke(Object object) throws InvocationTargetException, IllegalAccessException {
        method.invoke(object);
    }
}
