package app;

import messageSystem.Address;

public abstract class Message {
    public static final String CLASS_NAME_VARIABLE = "className";

    private final String className;
    private final Address from;
    private final Address to;

    protected Message(Class<?> clazz, Address from, Address to) {
        this.className = clazz.getName();
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public abstract void exec(Service service);

}
