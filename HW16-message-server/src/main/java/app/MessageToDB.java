package app;

import messageSystem.Address;

public abstract class MessageToDB extends Message {

    protected MessageToDB(Class<?> klass, Address from, Address to) {
        super(klass, from, to);
    }

    @Override
    public void exec(Service service) {
        if (service instanceof DBService) {
            exec((DBService) service);
        }
    }

    public abstract void exec(DBService dbService);
}
