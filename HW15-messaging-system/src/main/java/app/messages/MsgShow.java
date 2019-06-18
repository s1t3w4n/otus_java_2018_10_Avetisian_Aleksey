package app.messages;

import app.DBService;
import app.MsgToDB;
import messageSystem.Address;

public class MsgShow extends MsgToDB {

    public MsgShow(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(DBService dbService) {
        dbService.getMS().sendMessage(new MsgShowAnswer(getTo(), getFrom(), dbService.showAll()));
    }
}
