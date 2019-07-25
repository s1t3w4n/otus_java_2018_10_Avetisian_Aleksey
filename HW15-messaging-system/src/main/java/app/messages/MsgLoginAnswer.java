package app.messages;


import app.FrontendService;
import app.MsgToFrontend;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;

import java.util.Objects;


public class MsgLoginAnswer extends MsgToFrontend {
    private final Integer id;
    private final User user;

    public MsgLoginAnswer(Address from, Address to, Integer id, User user) {
        super(from, to);
        this.id = id;
        this.user = user;
    }

    @Override
    public void exec(FrontendService frontendService) {
        if (Objects.nonNull(user)) {
            final Gson gson = new Gson();
            String message = gson.toJson(user);
            frontendService.sendResponse(id).sendResponseMessage(message);
        } else {
            frontendService.sendResponse(id).sendResponseMessage("invalid");
        }
    }
}
