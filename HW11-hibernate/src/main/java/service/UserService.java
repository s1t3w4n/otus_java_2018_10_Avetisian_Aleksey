package service;


import model.User;

public interface UserService {

    void save(User user);

    User load(long id);
}
