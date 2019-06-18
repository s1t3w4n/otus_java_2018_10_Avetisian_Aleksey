package app.messages;


import app.FrontendService;
import app.MsgToFrontend;
import messageSystem.Address;
import model.User;


public class MsgLoginAnswer extends MsgToFrontend {
    private final User user;

    public MsgLoginAnswer(Address from, Address to, User user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.login(user);
    }
}
