package app;


import messageSystem.Addressee;
import model.User;

import java.util.List;

/**
 * Created by tully.
 */
public interface DBService extends Addressee {
    void init();

    User login(String id, String password);

    List<User> showAll();

    void add(User user);
}
