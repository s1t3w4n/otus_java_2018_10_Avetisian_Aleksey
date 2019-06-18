package app.frontend;

import app.FrontendService;
import app.MessageSystemContext;
import app.messages.MsgAdd;
import app.messages.MsgLogin;
import app.messages.MsgShow;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontendServiceImpl implements FrontendService {

    private final Address address;
    private final MessageSystemContext context;

    private final Map<Long, User> users = new HashMap<>();
    private List<User> usersList;

    public FrontendServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    public User handleLoginRequest(String id, String password) {
        Message message = new MsgLogin(getAddress(), context.getDbAddress(), id, password);
        context.getMessageSystem().sendMessage(message);
        return users.get(Long.valueOf(id));
    }

    @Override
    public void login(User user) {
        users.put(user.getId(), user);
    }

    public List<User> handleShowRequest() {
        Message message = new MsgShow(getAddress(),context.getDbAddress());
        context.getMessageSystem().sendMessage(message);
        return usersList;
    }

    @Override
    public void show(List<User> users) {
        usersList = users;
    }

    @Override
    public void add(User user) {
        Message message = new MsgAdd(getAddress(),context.getDbAddress(), user);
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
