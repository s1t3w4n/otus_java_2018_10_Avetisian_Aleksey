package executor.reflection;

import entitys.annotations.ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MyReflection {
    private static Logger logger = LoggerFactory.getLogger(MyReflection.class);

    public static List<String> getFildsNames(Object objectData) {
        List<String> names = new ArrayList<>();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
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
            if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
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
        logger.info("Table name: " + objectData.getClass().getSimpleName());
        return objectData.getClass().getSimpleName();
    }

    public static Field findAnnotatedField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ID.class)) {
                logger.info(field.getName() + " " + field.getType() + " annotated as ID");
                return field;
            }
        }
        logger.info("There is no annotated class as ID");
        return null;
    }
}
