package chain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AfterChain extends Chain {
    private List<Method> methods = new ArrayList<>();
    @Override
    public void fill(Method method) {
        methods.add(method);
        getLogger().info("Method " + method.getName() + " with annotation @After added");
    }

    @Override
    protected void invoke(Object object) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.invoke(object);
            getLogger().info("Method " + method.getName() + " with annotation @After invoked");
        }
    }
}
