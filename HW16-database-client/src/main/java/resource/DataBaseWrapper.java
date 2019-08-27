package resource;

import messageSystem.Address;
import model.User;
import service.DataBaseService;
import service.UserService;
import service.UserServiceImpl;

import java.io.IOException;

public class DataBaseWrapper {
    //private final Service service;

    public DataBaseWrapper(int db) throws IOException {
        Address dbAddress = new Address(Integer.toString(db));

        UserService userService = addDBService();
        new DataBaseService(userService, dbAddress);
    }

    private UserService addDBService() {
        UserService service = new UserServiceImpl();
        User admin = new User("Admin", "admin", 33, 1);
        service.save(admin);
        return service;
    }
}
