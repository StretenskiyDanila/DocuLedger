package org.example.businesspack.repositories;

import org.example.businesspack.configs.DataAccessor;
import org.example.businesspack.entities.Table;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class TableRepository<T extends Table> {

    protected final DataAccessor da = DataAccessor.getDataAccessor();

    public abstract Long save(T entity) throws SQLException;
    public abstract void delete(T entity) throws SQLException;
    public abstract Long update(T entity) throws SQLException;
    public abstract List<T> get() throws SQLException;

    protected abstract void buildPs(T entity, PreparedStatement ps, int count) throws SQLException;

}
