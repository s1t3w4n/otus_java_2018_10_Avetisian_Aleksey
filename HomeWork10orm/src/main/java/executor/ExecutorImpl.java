package executor;

import executor.dbexecutor.DBExecutor;
import executor.dbexecutor.DBExecutorImpl;
import executor.reflection.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ExecutorImpl<T> implements Executor<T> {

    private final DataSource dataSource;

    private static Logger logger = LoggerFactory.getLogger(ExecutorImpl.class);

    public ExecutorImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        logger.info(this.getClass().toString() + " created");
    }

    @Override
    public void save(T objectData) {
        if (checkExistenceInDB(objectData)) {
            update(objectData);
        } else {
            insert(objectData);
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        try (Connection connection = dataSource.getConnection()) {
            DBExecutor<T> executor = new DBExecutorImpl<>(connection);
            String sql = String.format
                    ("select * from %s where %s = ?",
                            clazz.getSimpleName(),
                            ReflectionHelper.findAnnotatedField(clazz).getName());

            Optional<T> object = executor.selectRecord(sql, id, resultSet -> {
                try {
                    Object[] fields = new Object[clazz.getDeclaredFields().length];
                    if (resultSet.next()) {
                        for (int i = 0; i < fields.length; i++) {
                            fields[i] = resultSet.getObject(i + 1);
                        }
                        return (T) ReflectionHelper.instantiate(clazz, fields);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            if (object.isPresent()) {
                logger.info(object.get().toString());
                return object.get();
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.info("Exception" + e);
            throw new RuntimeException(e);
        }
    }


    private void insert(T objectData) {
        try (Connection connection = dataSource.getConnection()) {

            DBExecutor<T> executor = new DBExecutorImpl<>(connection);

            String sql = insertSQL(objectData);

            logger.info("sql insert is: \"" + sql + "\"");

            executor.insertRecord(sql, ReflectionHelper.getFieldsValues(objectData));
            connection.commit();
            logger.info("New user committed.");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    private String insertSQL(T objectData) {

        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(ReflectionHelper.getTableName(objectData));
        sql.append(" (");
        List<String> names = ReflectionHelper.getFildsNames(objectData);
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
        try (Connection connection = dataSource.getConnection()){

            DBExecutor<T> executor = new DBExecutorImpl<>(connection);

            String sql = updateSQL(objectData);

            logger.info("sql insert is: \"" + sql + "\"");

            List<String> values = ReflectionHelper.getFieldsValues(objectData);
            values.add(ReflectionHelper.getId(objectData));

            executor.insertRecord(sql,values);
            connection.commit();
            logger.info("User updated committed.");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

    }

    private String updateSQL(T objectData) {
        StringBuilder sql = new StringBuilder("update ");

        sql.append(ReflectionHelper.getTableName(objectData));
        sql.append(" set (");
        List<String> names = ReflectionHelper.getFildsNames(objectData);
        for (int i = 0; i < names.size(); i++) {
            sql.append(names.get(i));
            if (i == names.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }
        sql.append(" = (");
        for (int i = 0; i < names.size(); i++) {
            sql.append("?");
            if (i == names.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }
        sql.append(" where ID = ?");

        return sql.toString();
    }


    private boolean checkExistenceInDB(T objectData) {
        try {
            long id = ReflectionHelper.findAnnotatedField(objectData.getClass()).getLong(objectData);
            try (Connection connection = dataSource.getConnection()) {
                DBExecutor<Boolean> DBExecutor = new DBExecutorImpl<>(connection);
                Optional<Boolean> isIn = DBExecutor.selectRecord("select id from user where id = ?", id, resultSet -> {
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
