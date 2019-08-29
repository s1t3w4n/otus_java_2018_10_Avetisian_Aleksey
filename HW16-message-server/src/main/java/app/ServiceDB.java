package app;

import model.User;

import java.util.List;

public interface ServiceDB extends Service {

    User login(String id, String password);
    List<User> showAll();
    void add(User user);
    void sendAnswer(Message message);

}
