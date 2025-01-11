package org.example.businesspack.services;

import java.util.List;

public interface Service<T> {

    Long save(T entity);
    List<T> get(String... parameter);
    void delete();
    void delete(T entity);
    Long update(T updateEntity);

}
