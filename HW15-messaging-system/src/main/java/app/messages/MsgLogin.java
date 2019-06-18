package app.messages;


import app.DBService;
import app.MsgToDB;
import messageSystem.Address;
import model.User;


public class MsgLogin extends MsgToDB {
    private final String id;
    private final String password;

    public MsgLogin(Address from, Address to, String id, String password) {
        super(from, to);
        this.id = id;
        this.password = password;
    }

    @Override
    public void exec(DBService dbService) {
        User user = dbService.login(id,password);
        dbService.getMS().sendMessage(new MsgLoginAnswer(getTo(), getFrom(), user));
    }
}
