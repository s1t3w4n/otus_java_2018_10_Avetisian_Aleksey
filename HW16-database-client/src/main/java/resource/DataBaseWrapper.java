package resource;

import messageSystem.Address;
import model.User;
import service.DataBaseService;
import service.UserService;
import service.UserServiceImpl;

import java.io.IOException;

public class DataBaseWrapper {
    private final UserService userService;
    private final DataBaseService service;

    public DataBaseWrapper(int front, int db) throws IOException {
        Address frontAddress = new Address(Integer.toString(front));
        Address dbAddress = new Address(Integer.toString(db));

        userService = addDBService();
        service = new DataBaseService(userService, dbAddress, frontAddress);
    }

    public void start() {
        service.init();
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }
}
