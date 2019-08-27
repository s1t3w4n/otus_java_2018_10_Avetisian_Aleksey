package app;

import messageSystem.Address;

public abstract class MessageToFE extends Message {
    protected MessageToFE(Class<?> clazz, Address from, Address to) {
        super(clazz, from, to);
    }

    @Override
    public void exec(Service service) {
        if (service instanceof FEService) {
            exec((FEService) service);
        }
    }

    public abstract void exec(FEService FEService);
}
