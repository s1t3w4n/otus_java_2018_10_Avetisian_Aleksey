package dao;

import java.util.List;

public interface DaoTemplate<T> {

    public List<T> loadAll();

    T loadById(long id);

    void insert(T element);

    void update(T element);
}
