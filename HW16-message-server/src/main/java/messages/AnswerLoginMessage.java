package messages;

import app.ServiceFE;
import app.MessageToFE;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;

import java.util.Objects;

public class AnswerLoginMessage extends MessageToFE {
    private final int id;
    private final User user;

    public AnswerLoginMessage(Address from, Address to, int id, User user) {
        super(AnswerLoginMessage.class, from, to);
        this.id = id;
        this.user = user;
    }

    @Override
    public void exec(ServiceFE frontendService) {
        if (Objects.nonNull(user)) {
            final Gson gson = new Gson();
            String message = gson.toJson(user);
            frontendService.sendResponse(id, message);
        } else {
            frontendService.sendResponse(id,"invalid");
        }
    }
}
