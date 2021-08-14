package jian.he.services;

import java.util.Set;
public interface CrudService<T, ID>{

    T findById(ID id);
    T save(T object);

    void delete(T object);

    void deleteById(ID id);

    Set<T> findAll();

}
