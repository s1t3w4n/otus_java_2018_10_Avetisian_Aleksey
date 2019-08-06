package messages;

import app.messages.MessageToDB;
import messageSystem.Address;
import service.DataBaseService;

public class LoginMessage extends MessageToDB {

    private final String userID;
    private final String password;

    public LoginMessage(Address from, Address to, int id, String userID, String password ) {
        super(LoginMessage.class, from, to, id);
        this.userID = userID;
        this.password = password;
    }


    @Override
    public void exec(DataBaseService dataBaseService) {
        dataBaseService.sendAnswer(new AnswerLoginMessage
                (dataBaseService.getAddress(),
                dataBaseService.getFEAddress(),
                this.getId(),
                dataBaseService.login(userID, password)));
    }
}
