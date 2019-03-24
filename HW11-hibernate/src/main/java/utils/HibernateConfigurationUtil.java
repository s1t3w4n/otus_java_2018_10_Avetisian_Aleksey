package utils;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class HibernateConfigurationUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateConfigurationUtil.class);


    public static HibernateConfigurationBuilder builder() {
        return new HibernateConfigurationBuilder();
    }

    public static class HibernateConfigurationBuilder {

        private Configuration configuration;
        private String resource;
        private Class[] classes;

        public HibernateConfigurationBuilder setConfigResource(String resource) {
            this.resource = resource;
            LOGGER.info("Set resource: " + resource);
            return this;
        }

        public HibernateConfigurationBuilder addAnnotatedClasses(Class... classes) {
            this.classes = classes;
            StringBuilder stringBuilder = new StringBuilder();
            for (Class clazz : classes) {
                stringBuilder.append(clazz.getSimpleName());
                stringBuilder.append(";");
            }
            LOGGER.info("Set annotated classes : " + stringBuilder);
            return this;
        }

        public Configuration build() {
            try {
                if (Objects.nonNull(resource)) {
                    configuration = new Configuration().configure(resource);
                } else {
                    configuration = new Configuration().configure();
                }

                if (Objects.nonNull(classes)) {
                    for (Class clazz : classes) {
                        configuration.addAnnotatedClass(clazz);
                    }
                }
                LOGGER.info("Configuration built");

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return configuration;
        }
    }


    /*public static SessionFactory getSessionFactory() {
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
    }*/
}
