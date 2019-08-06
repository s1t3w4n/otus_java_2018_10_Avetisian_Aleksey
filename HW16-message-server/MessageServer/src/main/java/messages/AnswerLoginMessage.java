package messages;

import app.messages.MessageToFE;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;
import service.FrontendService;

import java.util.Objects;

public class AnswerLoginMessage extends MessageToFE {

    private final User user;

    public AnswerLoginMessage(Address from, Address to, int id, User user) {
        super(AnswerLoginMessage.class, from, to, id);
        this.user = user;
    }

    @Override
    public void exec(FrontendService frontendService) {
        if (Objects.nonNull(user)) {
            final Gson gson = new Gson();
            String message = gson.toJson(user);
            frontendService.sendResponse(getId(), message);
        } else {
            frontendService.sendResponse(getId(),"invalid");
        }
    }
}
