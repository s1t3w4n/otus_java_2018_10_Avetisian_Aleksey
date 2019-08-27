package messages;

import app.FEService;
import app.MessageToFE;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;

import java.util.Objects;

public class AnswerLoginMessage extends MessageToFE {
    private final int id;
    private final User user;

    protected AnswerLoginMessage(Address from, Address to, int id, User user) {
        super(AnswerLoginMessage.class, from, to);
        this.id = id;
        this.user = user;
    }

    @Override
    public void exec(FEService FEService) {
        if (Objects.nonNull(user)) {
            final Gson gson = new Gson();
            String message = gson.toJson(user);
            FEService.sendResponse(id, message);
        } else {
            FEService.sendResponse(id,"invalid");
        }
    }
}
