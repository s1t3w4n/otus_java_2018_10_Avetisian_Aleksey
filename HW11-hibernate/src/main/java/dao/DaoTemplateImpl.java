package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class DaoTemplateImpl<T> implements DaoTemplate<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(DaoTemplateImpl.class);


    public DaoTemplateImpl(Class<T> clazz, Configuration configuration) {
        this.clazz = clazz;
        this.sessionFactory = configuration.buildSessionFactory();
        logger.info("Session factory built");
    }

    @Override
    public List<T> loadAll() {
        List<T> table;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                table = session.createCriteria(clazz).list();
                transaction.commit();
            } catch (Exception e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                    logger.error("Something went wrong: " + e.getMessage());
                }
                table = null;
            }
            return table;
        }
    }

    public T loadById(long id) {
        return makeTransaction(session -> session.get(clazz,id));
    }

    @Override
    public void insert(T element) {
        makeTransaction(session -> {session.save(element); return null;});
        logger.info("Inserted new element: " + element.toString());
    }

    @Override
    public void update(T element) {
        makeTransaction(session -> {session.update(element); return null;});
        logger.info("Updated element: " + element.toString());
    }

    private T makeTransaction(Function<Session, T> function) {
        T element;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                element = function.apply(session);
                transaction.commit();
            } catch (Exception e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                    logger.error("Something went wrong: " + e.getMessage());
                }
                element = null;
            }
        }
        return element;
    }
}
