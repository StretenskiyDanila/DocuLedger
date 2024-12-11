package org.example.businesspack.repositories;

import org.example.businesspack.configs.DataAccessor;
import org.example.businesspack.entities.Table;
import org.example.businesspack.utils.StringQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TableRepository<T extends Table> {

    String getTableName();
    Long save(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    Long update(T entityUpdate, T entity) throws SQLException;

    default List<T> getAll() throws SQLException {
        DataAccessor da = DataAccessor.getDataAccessor();
        String query = String.format(StringQuery.QUERY_GET_ALL, getTableName());
        try (PreparedStatement ps = da.getConnection().prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            List<T> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add((T) Converter.makeEntity(rs, getTableName()));
            }
            return entities;
        }
    }

}
