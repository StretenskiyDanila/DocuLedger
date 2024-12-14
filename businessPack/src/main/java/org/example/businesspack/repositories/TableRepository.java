package org.example.businesspack.repositories;

import org.example.businesspack.configs.DataAccessor;
import org.example.businesspack.entities.Table;
import org.example.businesspack.factory.EntityFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TableRepository<T extends Table> {

    DataAccessor da = DataAccessor.getDataAccessor();

    String getTableName();
    String getQueryGet();
    EntityFactory<T> getEntityFactory();

    Long save(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    Long update(T entityUpdate, T entity) throws SQLException;

    default List<T> getAll() throws SQLException {
        DataAccessor da = DataAccessor.getDataAccessor();
        String query = String.format(getQueryGet(), getTableName());
        try (PreparedStatement ps = da.getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            List<T> entities = new ArrayList<>();
            EntityFactory<T> entityFactory = getEntityFactory();
            while (rs.next()) {
                entities.add(entityFactory.create(rs));
            }
            return entities;
        }
    }

}
