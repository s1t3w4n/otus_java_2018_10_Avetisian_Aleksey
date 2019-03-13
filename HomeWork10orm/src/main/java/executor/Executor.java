package executor;

public interface Executor<T> {

    void save(T objectData);

    <T> T load(long id, Class<T> clazz);
}
