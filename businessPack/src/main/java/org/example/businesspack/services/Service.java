package org.example.businesspack.services;

import java.util.List;

public interface Service<T> {

    Long save(T entity);
    List<T> get();
    void delete(T entity);
    Long update(T currentEntity, T updateEntity);

}
