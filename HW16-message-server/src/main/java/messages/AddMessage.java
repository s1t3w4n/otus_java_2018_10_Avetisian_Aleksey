package messages;

import app.ServiceDB;
import app.MessageToDB;
import messageSystem.Address;
import model.User;

public class AddMessage extends MessageToDB {

    private final User user;
    private final int id;

    public AddMessage(Address from, Address to, int id, User user) {
        super(AddMessage.class, from, to);
        this.user = user;
        this.id = id;
    }

    @Override
    public void exec(ServiceDB serviceDB) {
        serviceDB.add(user);
    }
}
