package dbservise;

import entitys.User;

public interface DBUserService {

    void save(User user);

    User load(long id);
}
