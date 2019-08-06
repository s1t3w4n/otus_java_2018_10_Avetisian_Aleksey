package app.messages;

import app.Service;
import messageSystem.Address;

public abstract class Message {

    public static final String CLASS_NAME_VARIABLE = "className";

    private final String className;
    private final int id;
    private final Address from;
    private final Address to;

    public Message(Class<?> clazz, Address from, Address to, int id) {
        this.className = clazz.getName();
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public int getId() {
        return id;
    }

    public abstract void exec(Service service);
}
