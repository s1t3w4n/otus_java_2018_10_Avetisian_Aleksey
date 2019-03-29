package service;

import dao.DaoTemplate;
import dao.DaoTemplateImpl;
import model.Address;
import model.Phone;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateConfigurationUtil;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private final DaoTemplate<User> daoTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        this.daoTemplate = new DaoTemplateImpl<>(User.class, HibernateConfigurationUtil.builder()
                .setConfigResource("hibernate.cfg.xml")
                .addAnnotatedClasses(User.class, Phone.class, Address.class)
                .build());
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
        User user = daoTemplate.loadById(id);
        if (Objects.nonNull(user)) {
            logger.info("Loaded: " + user.toString());
        } else {
            logger.info("No such user with id: " + id);
        }
        return user;
    }

    @Override
    public List<User> loadAll() {
        List<User> users = daoTemplate.loadAll();
        if (Objects.nonNull(users)) {
            logger.info("Loaded: " + users.toString());
        } else {
            logger.info("Empty table");
        }
        return users;
    }
}
