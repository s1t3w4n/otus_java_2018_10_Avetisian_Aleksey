package executor;

import executor.dbexecutor.SQLQueriesExecutor;
import executor.dbexecutor.SQLQueriesExecutorImpl;
import executor.reflection.ReflectionHelper;
import executor.reflection.ReflectionSQLBulder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class DaoTemplateImpl<T> implements DaoTemplate<T> {

    private final DataSource dataSource;
    private final ReflectionSQLBulder sqlBulder;
    private String insert;
    private String update;
    private String select;

    private static Logger logger = LoggerFactory.getLogger(DaoTemplateImpl.class);

    public DaoTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        logger.info(this.getClass().toString() + " created");
        sqlBulder = new ReflectionSQLBulder();
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
            SQLQueriesExecutor<T> executor = new SQLQueriesExecutorImpl<>(connection);
            if (Objects.isNull(select)) {
                select = sqlBulder.selectSQL(clazz);
            }
            logger.info("select query is: \"" + select + "\"");


            Optional<T> object = executor.selectRecord(select, id, resultSet -> {
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

            SQLQueriesExecutor<T> executor = new SQLQueriesExecutorImpl<>(connection);

            if (Objects.isNull(insert)) {
                insert = sqlBulder.insertSQL(objectData);
            }

            logger.info("sql insert is: \"" + insert + "\"");

            executor.insertRecord(insert, ReflectionHelper.getFieldsValues(objectData));
            connection.commit();
            logger.info("New user committed.");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }





    private void update(T objectData) {
        try (Connection connection = dataSource.getConnection()) {

            SQLQueriesExecutor<T> executor = new SQLQueriesExecutorImpl<>(connection);

            if (Objects.isNull(update)) {
                update = sqlBulder.updateSQL(objectData);
            }

            logger.info("update insert is: \"" + update + "\"");

            List<String> values = ReflectionHelper.getFieldsValues(objectData);
            values.add(ReflectionHelper.getIdValue(objectData));
            logger.info(values.toString());

            executor.insertRecord(update, values);
            connection.commit();
            logger.info("User updated committed.");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

    }


    private boolean checkExistenceInDB(T objectData) {
        try {
            long id = ReflectionHelper.findAnnotatedField(objectData.getClass()).getLong(objectData);
            try (Connection connection = dataSource.getConnection()) {
                SQLQueriesExecutor<Boolean> SQLQueriesExecutor = new SQLQueriesExecutorImpl<>(connection);

                if (Objects.isNull(select)) {
                    select = sqlBulder.selectSQL(objectData.getClass());
                }
                logger.info("select query is: \"" + select + "\"");

                Optional<Boolean> isIn = SQLQueriesExecutor.selectRecord
                        (select, id, resultSet -> {
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
