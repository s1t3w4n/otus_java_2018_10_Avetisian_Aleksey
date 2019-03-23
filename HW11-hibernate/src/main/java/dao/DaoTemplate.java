package dao;

public interface DaoTemplate<T> {

    T loadById(long id);

    void insert(T element);

    void update(T element);
}
