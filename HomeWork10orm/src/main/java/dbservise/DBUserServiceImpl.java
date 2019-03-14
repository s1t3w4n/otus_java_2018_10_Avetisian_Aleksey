package dbservise;

import entitys.User;
import executor.Executor;
import executor.ExecutorImpl;

import javax.sql.DataSource;

public class DBUserServiceImpl implements DBUserService {

    DataSource dataSource = new DataSourceH2();

    public DBUserServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        Executor<User> executor = new ExecutorImpl<>(dataSource);
        executor.save(user);
    }

    @Override
    public User load(long id) {
        Executor<User> executor = new ExecutorImpl<>(dataSource);

        return executor.load(id,User.class);
    }
}
