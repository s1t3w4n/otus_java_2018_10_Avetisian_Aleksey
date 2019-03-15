import dbservise.DBUserServiceImpl;
import entitys.SchemaCreator;
import entitys.User;
import dbservise.DataSourceH2;
import executor.DaoTemplateImpl;

import java.sql.*;

public class MainHW10 {

    public static void main(String[] args) throws SQLException {
        final DataSourceH2 h2 = new DataSourceH2();
        SchemaCreator.createUserTable(h2);
        final DBUserServiceImpl userService = new DBUserServiceImpl(new DaoTemplateImpl<>(h2));

        User user = new User(new Long(999), "Jack", new Integer(12));
        userService.save(user);
        User user1 = userService.load(user.getID());
        User user2 = new User(new Long(999), "Bob", new Integer(12));
        userService.save(user2);
        User user3 = userService.load(user.getID());
    }

}
