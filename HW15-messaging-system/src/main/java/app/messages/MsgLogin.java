package app.messages;


import app.DBService;
import app.MsgToDB;
import messageSystem.Address;
import model.User;


public class MsgLogin extends MsgToDB {
    private final int id;
    private final String login;
    private final String password;

    public MsgLogin(Address from, Address to, int id, String login, String password) {
        super(from, to);
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public void exec(DBService dbService) {
        User user = dbService.login(login,password);
        dbService.getMS().sendMessage(new MsgLoginAnswer(getTo(), getFrom(), id, user));
    }
}
