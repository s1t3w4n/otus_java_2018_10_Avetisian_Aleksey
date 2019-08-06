package app.messages;

import app.Service;
import messageSystem.Address;
import service.DataBaseService;

public abstract class MessageToDB extends Message {

    public MessageToDB(Class<?> clazz, Address from, Address to, int id) {
        super(clazz, from, to, id);
    }

    @Override
    public void exec(Service service) {
        if (service instanceof DataBaseService) {
            exec((DataBaseService) service);
        }
    }

    public abstract void exec(DataBaseService FrontendService);
}
