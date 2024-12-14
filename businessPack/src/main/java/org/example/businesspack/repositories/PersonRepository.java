package org.example.businesspack.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.businesspack.entities.Person;
import org.example.businesspack.utils.StringQuery;

public class PersonRepository implements TableRepository<Person> {

    @Override
    public String getTableName() {
        return new Person().getTableName();
    }

    @Override
    public String getQueryGet() {
        return StringQuery.QUERY_GET_PERSON_FOR_ROLE;
    }

    @Override
    public Long save(Person entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_INSERT_PERSON)) {
            buildPs(entity, ps, 1);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        }
    }

    @Override
    public void delete(Person entity) throws SQLException {

    }

    @Override
    public Long update(Person entityUpdate, Person entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void buildPs(Person entity, PreparedStatement ps, int count) throws SQLException {
        ps.setString(count++, entity.getName());
        ps.setString(count, entity.getRole().getName());
    }

}

