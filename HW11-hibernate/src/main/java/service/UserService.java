package service;


import model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User load(long id);

    List<User> loadAll();
}
