package app.messages;

import app.DBService;
import app.MsgToDB;
import messageSystem.Address;
import model.User;

public class MsgAdd extends MsgToDB {
    private final User user;
    private final Integer id;
    public MsgAdd(Address from, Address to, Integer id, User user) {
        super(from, to);
        this.user = user;
        this.id = id;
    }

    @Override
    public void exec(DBService dbService) {
        dbService.add(user);
    }
}
