package dbservise;

public interface DBServiceUser<T> {

    void save(T objectData);

    <T> T load(long id, Class<T> clazz);
}
