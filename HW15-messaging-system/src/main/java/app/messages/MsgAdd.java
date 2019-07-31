package app.messages;

import app.DBService;
import app.MsgToDB;
import messageSystem.Address;
import model.User;

public class MsgAdd extends MsgToDB {
    private final User user;
    private final int id;
    public MsgAdd(Address from, Address to, int id, User user) {
        super(from, to);
        this.user = user;
        this.id = id;
    }

    @Override
    public void exec(DBService dbService) {
        dbService.add(user);
    }
}
