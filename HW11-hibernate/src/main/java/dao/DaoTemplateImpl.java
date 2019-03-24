package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class DaoTemplateImpl<T> implements DaoTemplate<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(DaoTemplateImpl.class);


    public DaoTemplateImpl(Class<T> clazz, Configuration configuration) {
        this.clazz = clazz;
        this.sessionFactory = configuration.buildSessionFactory();
    }


    @Override
    public T loadById(long id) {
        T element;
        logger.info("Getting element " + clazz.getSimpleName() + " with id: " + id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                element = session.get(clazz, id);
                transaction.commit();
            } catch (Exception e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                logger.error("Something went wrong in load: " + e.getMessage());
                element = null;
            }
        }
        return element;
    }

    @Override
    public void insert(T element) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(element);
                transaction.commit();
            } catch (Exception e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                logger.error("Something went wrong in insert: " + e.getMessage());
            }
        }
        logger.info("Inserted new element: " + element.toString());
    }

    @Override
    public void update(T element) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(element);
                transaction.commit();
            } catch (Exception e) {
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
                logger.error("Something went wrong in update: " + e.getMessage());
            }
        }
        logger.info("Updated element: " + element.toString());
    }

}
