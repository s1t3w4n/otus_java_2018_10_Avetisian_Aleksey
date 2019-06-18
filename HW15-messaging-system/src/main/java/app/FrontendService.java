package app;


import messageSystem.Addressee;
import model.User;

import java.util.List;

/**
 * Created by tully.
 */
public interface FrontendService extends Addressee {
    void init();

    User handleLoginRequest(String id, String password);

    void login(User user);

    List<User> handleShowRequest();

    void show(List<User> users);

    void add(User user);

}

