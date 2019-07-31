package app.messages;

import app.FrontendService;
import app.MsgToFrontend;
import com.google.gson.Gson;
import messageSystem.Address;
import model.User;

import java.util.List;

public class MsgShowAnswer extends MsgToFrontend {
    private final List<User> users;
    private final int id;

    public MsgShowAnswer(Address from, Address to, int id, List<User> users) {
        super(from, to);
        this.users = users;
        this.id = id;
    }

    @Override
    public void exec(FrontendService frontendService) {
        final Gson gson = new Gson();
        String message = gson.toJson(users);
        frontendService.sendResponse(id, message);
    }
}
