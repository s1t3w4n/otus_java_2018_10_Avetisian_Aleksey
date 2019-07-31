package app.messages;

import app.DBService;
import app.MsgToDB;
import messageSystem.Address;

public class MsgShow extends MsgToDB {

    private final int id;

    public MsgShow(Address from, Address to, int id) {
        super(from, to);
        this.id = id;
    }

    @Override
    public void exec(DBService dbService) {
        dbService.getMS().sendMessage(new MsgShowAnswer(getTo(), getFrom(), id, dbService.showAll()));
    }
}
