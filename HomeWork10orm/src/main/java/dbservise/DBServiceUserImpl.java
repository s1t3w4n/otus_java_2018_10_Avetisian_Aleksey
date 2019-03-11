package dbservise;

import executor.Executor;
import executor.ExecutorImpl;
import executor.reflection.MyReflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class DBServiceUserImpl<T> implements DBServiceUser<T> {

    private final DataSource dataSource;

    private static Logger logger = LoggerFactory.getLogger(DBServiceUserImpl.class);

    public DBServiceUserImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        logger.info(this.getClass().toString() + " created");
    }

    @Override
    public void save(T objectData) {
        if (checkExistenceInDB(objectData)) {
            update(objectData);
        } else {
            insert(objectData);
            checkExistenceInDB(objectData);
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        /*try (Connection connection = dataSource.getConnection()) {
            Executor<T> executor = new ExecutorImpl<>(connection);
            String sql = String.format
                    ("select * from %s where %s = ?",
                            clazz.getSimpleName(),
                            MyReflection.findAnnotatedField(clazz).getName());

            Optional<T> object = executor.selectRecord(sql, id, resultSet -> {
                try {
                    List<Object> fields = new ArrayList<>();
                    while (resultSet.next()) {

                        fields.add(resultSet.getObject(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            })

        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        return null;
    }

    private void insert(T objectData) {
        try (Connection connection = dataSource.getConnection()) {

            Executor<T> executor = new ExecutorImpl<>(connection);

            String sql = insertSQL(objectData);

            logger.info("sql insert is: \"" + sql + "\"");

            executor.insertRecord(sql, MyReflection.getFieldsValues(objectData));
            connection.commit();
            logger.info("New user committed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String insertSQL(T objectData) {

        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(MyReflection.getTableName(objectData));
        sql.append(" (");
        List<String> names = MyReflection.getFildsNames(objectData);
        for (int i = 0; i < names.size(); i++) {
            sql.append(names.get(i));
            if (i == names.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }
        sql.append(" values (");
        for (int i = 0; i < names.size(); i++) {
            sql.append("?");
            if (i == names.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }

        return sql.toString();
    }

    private void update(T objectData) {
        logger.info("User updated committed.");
    }

    private boolean checkExistenceInDB(T objectData) {
        try {
            long id = MyReflection.findAnnotatedField(objectData.getClass()).getLong(objectData);
            try (Connection connection = dataSource.getConnection()) {
                Executor<Boolean> executor = new ExecutorImpl<>(connection);
                Optional<Boolean> isIn = executor.selectRecord("select id from user where id = ?", id, resultSet -> {
                    boolean existence = false;
                    try {
                        existence = resultSet.next();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (existence) {
                            logger.info("Object is exist in data base");
                        } else {
                            logger.info("Object isn't exist in data base");
                        }
                        return existence;
                });
                return isIn.get();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.info("Wrong element class");
        return false;
    }
}
