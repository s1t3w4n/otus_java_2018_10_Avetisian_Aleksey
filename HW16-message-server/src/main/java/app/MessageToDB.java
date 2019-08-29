package app;

import messageSystem.Address;

public abstract class MessageToDB extends Message {

    protected MessageToDB(Class<?> klass, Address from, Address to) {
        super(klass, from, to);
    }

    @Override
    public void exec(Service service) {
        if (service instanceof ServiceDB) {
            exec((ServiceDB) service);
        }
    }

    public abstract void exec(ServiceDB serviceDB);
}
