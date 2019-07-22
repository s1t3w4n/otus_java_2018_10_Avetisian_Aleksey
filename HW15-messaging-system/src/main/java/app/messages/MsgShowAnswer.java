package app.messages;

import app.FrontendService;
import app.MsgToFrontend;
import messageSystem.Address;
import model.User;

import java.util.List;

public class MsgShowAnswer extends MsgToFrontend {
    private final List<User> users;

    public MsgShowAnswer(Address from, Address to, List<User> users) {
        super(from, to);
        this.users = users;
    }

    @Override
    public void exec(FrontendService frontendService) {
        //frontendService.show(users);
    }
}
