import executor.Executor;
import executor.ExecutorImpl;
import entitys.Table;
import entitys.User;
import dbservise.DataSourceH2;
import visitor.Service;
import visitor.UserService;
import visitor.types.TraversedArray;
import visitor.types.TraversedObject;
import visitor.types.TraversedPrimitive;
import visitor.types.TraversedString;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;

public class MainHW10 {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        new Table().createTable(dataSource);
        User user = new User(new Long(999), "Jack", new Integer(12));
        traverse(user, new UserService());
        Executor executor = new ExecutorImpl(dataSource);
        executor.save(user);
        User user1 = (User) executor.load(user.getID(), user.getClass());
        System.out.println(user1.toString());
        User user2 = new User(new Long(999), "Bob", new Integer(12));
        executor.save(user2);
        User user3 = (User) executor.load(user.getID(), user.getClass());
    }

    private static void traverse(Object object, Service service) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            if (field.getType().isPrimitive()) {
                new TraversedPrimitive(field).accept(service);
            } else if (field.getType().equals(String.class)) {
                new TraversedString(field).accept(service);
            } else if (field.getType().isArray()) {
                new TraversedArray(field, field).accept(service);
            } else {
                new TraversedObject(field).accept(service);
            }
        }
    }

}
