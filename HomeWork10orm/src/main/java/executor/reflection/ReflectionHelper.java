package executor.reflection;

import entitys.annotations.ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ReflectionHelper {

    private static Logger logger = LoggerFactory.getLogger(ReflectionHelper.class);

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (Objects.isNull(args) || args.length == 0) {
                logger.info("Empty " + type.getSimpleName() + " created");
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = getFieldsTypes(type);
                logger.info("Filled " + type.getSimpleName() + " created");
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException e) {
            logger.info("SQLQueryiesExecuter:" + e);
        }

        return null;
    }

    private static Class<?>[] toClasses(Object[] args) {
        logger.info("Array of fields created");
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static Class<?>[] getFieldsTypes(Class clazz) {
        List<Class> names = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
                names.add(field.getType());
        }
        Class[] types = new Class[names.size()];
        return names.toArray(types);
    }

    public static List<String> getFieldsNames(Object objectData) {
        List<String> names = new ArrayList<>();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
                names.add(field.getName());
        }
        logger.info(names.toString());
        return names;
    }

    public static List<String> getFieldsValues(Object objectData) {
        List<String> values = new ArrayList<>();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
                try {
                    values.add(field.get(objectData).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        logger.info(values.toString());
        return values;
    }

    public static String getTableName(Object objectData) {
        logger.info("SchemaCreator name: " + objectData.getClass().getSimpleName());
        return objectData.getClass().getSimpleName();
    }

    public static String getIdValue(Object object) {
        Field field = findAnnotatedField(object.getClass());
        field.setAccessible(true);
        String value;
        try {
            value = field.get(object).toString();
        } catch (IllegalAccessException e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }

        logger.info("ID is: " + value);
        return value;
    }

    public static String getIdName(Class clazz) {
        Field field = findAnnotatedField(clazz);
        field.setAccessible(true);
        String name;
        name = field.getName();

        logger.info("ID field is: " + name);
        return name;
    }

    public static Field findAnnotatedField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ID.class)) {
                ID id = field.getAnnotation(ID.class);
                logger.info(field.getName() + " " + field.getType() + " annotated as ID"
                        + " Developer is: " + id.developer());
                return field;
            }
        }
        logger.info("There is no annotated class as ID");
        return null;
    }
}
