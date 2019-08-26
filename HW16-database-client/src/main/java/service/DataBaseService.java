package service;

import chanel.DataBaseServerSocketWorker;
import messageSystem.Address;
import model.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataBaseService {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    private final UserService userService;
    private final DataBaseServerSocketWorker worker;

    private final Address address;
    private final Address FEAddress;


    public DataBaseService(UserService userService, Address address, Address dbAddress) throws IOException {
        this.userService = userService;
        this.address = address;
        FEAddress = dbAddress;
        worker = new DataBaseServerSocketWorker(HOST,PORT);
    }

    public Address getAddress() {
        return address;
    }

    public Address getFEAddress() {
        return FEAddress;
    }

    public User login(String id, String password) {
        return userService.load(Long.parseLong(id, 10));
    }

    public List<User> showAll() {
        return userService.loadAll();
    }

    public void add(User user) {
        userService.save(user);
    }

    /*public void sendAnswer(Message message) {
        worker.send(message);
    }*/

    public void init() {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Message msg = worker.take();
                    System.out.println("Message received: " + msg.toString());
                    msg.exec(this);
                }
            } catch (InterruptedException e) {
            }
        });*/
    }
}
