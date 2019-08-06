package app.messages;

import app.Service;
import messageSystem.Address;
import service.FrontendService;

public abstract class MessageToFE extends Message {
    public MessageToFE(Class<?> clazz, Address from, Address to, int id) {
        super(clazz, from, to, id);
    }

    @Override
    public void exec(Service service) {
        if (service instanceof FrontendService) {
            exec((FrontendService) service);
        }
    }

    public abstract void exec(FrontendService FrontendService);
}
