package chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Chain {
    private static Logger logger = LoggerFactory.getLogger(Chain.class);
    private Chain next;

    public Chain getNext() {
        return next;
    }

    public void setNext(Chain next) {
        this.next = next;
        logger.info(this.getClass()+ " has set next chain " + next.getClass());
    }

    public static Logger getLogger() {
        return logger;
    }

    public void run(Object object) throws InvocationTargetException, IllegalAccessException {
        invoke(object);
        if (getNext() != null) {
            getNext().run(object);
        }
    }

    abstract public void fill(Method method);
    abstract protected void invoke(Object object) throws InvocationTargetException, IllegalAccessException;
}
