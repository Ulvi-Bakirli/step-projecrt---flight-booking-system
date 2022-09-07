package DAO;

import java.util.List;
import java.util.Optional;

public interface DAO <T> {

    List<T> selectAll();

    Optional<T> select(int id);

    boolean delete(int id);

    void insert(T t);

}
