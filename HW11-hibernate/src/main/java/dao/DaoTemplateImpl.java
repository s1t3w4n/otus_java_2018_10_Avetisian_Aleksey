package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateSessionFactoryUtil;

public class DaoTemplateImpl<T> implements DaoTemplate<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(DaoTemplateImpl.class);

    public DaoTemplateImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }


    @Override
    public T loadById(long id) {
        T element;
        logger.info("Getting element " + clazz.getSimpleName() + " with id: " + id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            element = session.get(clazz, id);
            session.getTransaction().commit();
        }
        return element;
    }

    @Override
    public void insert(T element) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(element);
            session.getTransaction().commit();
        }
        logger.info("Inserted new element: " + element.toString());
    }

    @Override
    public void update(T element) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
        }
        logger.info("Updated element: " + element.toString());
    }

}
