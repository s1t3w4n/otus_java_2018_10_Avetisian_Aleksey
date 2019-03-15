package executor;

public interface DaoTemplate<T> {

    void save(T objectData);

    <T> T load(long id, Class<T> clazz);
}
