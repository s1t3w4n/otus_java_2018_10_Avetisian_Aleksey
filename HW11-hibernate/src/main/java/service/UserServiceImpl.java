package service;

import dao.DaoTemplate;
import dao.DaoTemplateImpl;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    private final DaoTemplate daoTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        this.daoTemplate = new DaoTemplateImpl(User.class);
    }

    @Override
    public void save(User user) {
        if (Objects.isNull(daoTemplate.loadById(user.getId()))) {
            logger.info("Starting insert of user: " + user.toString());
            daoTemplate.insert(user);
        } else {
            logger.info("Starting update of user: " + user.toString());
            daoTemplate.update(user);
        }
    }

    @Override
    public User load(long id) {
        User user = (User) daoTemplate.loadById(id);
        if (Objects.nonNull(user)) {
            logger.info("Loaded: " + user.toString());
        } else {
            logger.info("No such user with id: " + id);
        }
        return user;
    }
}
