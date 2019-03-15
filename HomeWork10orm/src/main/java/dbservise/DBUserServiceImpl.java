package dbservise;

import entitys.User;
import executor.DaoTemplate;


public class DBUserServiceImpl implements DBUserService {

    private final DaoTemplate<User> daoTemplate;

    public DBUserServiceImpl(DaoTemplate<User> daoTemplate) {
        this.daoTemplate = daoTemplate;
    }

    @Override
    public void save(User user) {
        daoTemplate.save(user);
    }

    @Override
    public User load(long id) {
        return daoTemplate.load(id,User.class);
    }
}
