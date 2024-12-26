package org.example.businesspack.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.enums.PersonRole;

public abstract class PersonRepository extends TableRepository<Person> {

    @Override
    public List<Person> get() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Person entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract List<Person> get(PersonRole role) throws SQLException;
    public abstract void delete() throws SQLException;
    public abstract void update() throws SQLException;

    protected void buildPs(Person entity, PreparedStatement ps, int count) throws SQLException {
        ps.setString(count++, entity.getName());
        ps.setString(count, entity.getRole().getName());
    }



}
