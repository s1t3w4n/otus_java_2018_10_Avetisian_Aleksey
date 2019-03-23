package utils;

import model.Address;
import model.Phone;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);

    public static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                Metadata metadata = new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Phone.class)
                        .addAnnotatedClass(Address.class)
                        .getMetadataBuilder()
                        .build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("Session factory created");
        return sessionFactory;
    }
}
