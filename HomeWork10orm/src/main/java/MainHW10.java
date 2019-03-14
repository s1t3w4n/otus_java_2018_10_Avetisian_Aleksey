import dbservise.DBUserService;
import dbservise.DBUserServiceImpl;
import entitys.Table;
import entitys.User;
import dbservise.DataSourceH2;

import javax.sql.DataSource;
import java.sql.*;

public class MainHW10 {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        new Table().createTable(dataSource);
        User user = new User(new Long(999), "Jack", new Integer(12));

        DBUserService userService = new DBUserServiceImpl(dataSource);
        userService.save(user);
        User user1 = userService.load(user.getID());
        User user2 = new User(new Long(999), "Bob", new Integer(12));
        userService.save(user2);
        User user3 = userService.load(user.getID());
    }

}
