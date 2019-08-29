package messages;

import app.ServiceDB;
import app.MessageToDB;
import messageSystem.Address;

public class ShowMessage extends MessageToDB {

    private final int id;

    public ShowMessage(Address from, Address to, int id) {
        super(ShowMessage.class, from, to);
        this.id = id;
    }

    @Override
    public void exec(ServiceDB serviceDB) {
        serviceDB.sendAnswer(new AnswerShowMessage(getTo(), getFrom(), id, serviceDB.showAll()));
    }
}
