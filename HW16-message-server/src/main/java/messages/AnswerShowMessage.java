package messages;

import app.ServiceFE;
import app.MessageToFE;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;

import java.util.List;

public class AnswerShowMessage extends MessageToFE {

    private final List<User> users;
    private final int id;

    public AnswerShowMessage(Address from, Address to, int id, List<User> users) {
        super(AnswerShowMessage.class, from, to);
        this.users = users;
        this.id = id;
    }

    @Override
    public void exec(ServiceFE frontendService) {
        final Gson gson = new Gson();
        String message = gson.toJson(users);
        frontendService.sendResponse(id, message);
    }
}
