package app.messages;


import app.FrontendService;
import app.MsgToFrontend;
import messageSystem.Address;
import model.User;


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
        frontendService.sendResponse(id).sendResponseMessage(user.getName());
    }
}
