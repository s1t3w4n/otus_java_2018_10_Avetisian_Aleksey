package service;

import app.DBService;
import app.Message;
import chanel.DataBaseServerSocketWorker;
import chanel.SocketMessageWorker;
import messageSystem.Address;
import model.User;

import java.io.IOException;
import java.util.List;

public class DataBaseService implements DBService {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    private final UserService userService;
    private final SocketMessageWorker worker;

    private final Address address;


    public DataBaseService(UserService userService, Address address) throws IOException {
        this.userService = userService;
        this.address = address;
        worker = new DataBaseServerSocketWorker(HOST,PORT);
        this.init(worker);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public User login(String id, String password) {
        return userService.load(Long.parseLong(id, 10));
    }

    @Override
    public List<User> showAll() {
        return userService.loadAll();
    }

    @Override
    public void add(User user) {
        userService.save(user);
    }

    @Override
    public void sendAnswer(Message message) {
        worker.send(message);
    }

}
