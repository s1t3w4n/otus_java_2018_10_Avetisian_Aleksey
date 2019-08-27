package messages;

import app.DBService;
import app.MessageToDB;
import messageSystem.Address;
import model.User;

public class LoginMessage extends MessageToDB {

    private final int id;
    private final String login;
    private final String password;


    public LoginMessage(Address from, Address to, int id, String login, String password) {
        super(LoginMessage.class, from, to);
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public void exec(DBService dbService) {
        User user = dbService.login(login,password);
        dbService.sendAnswer(new AnswerLoginMessage(getTo(), getFrom(), id, user));
    }
}
