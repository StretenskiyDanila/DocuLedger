package org.example.businesspack.services;

import java.util.List;

public interface DataWorkService<T> {

    Long save(T entity);
    List<T> get();
    void delete(T entity);
    Long update(T entity);

}
