package org.example.businesspack.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.businesspack.entities.Person;

public abstract class PersonRepository extends TableRepository<Person> {

    @Override
    public void delete(Person entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract void delete() throws SQLException;
    public abstract void update() throws SQLException;

    protected void buildPs(Person entity, PreparedStatement ps, int count) throws SQLException {
        ps.setString(count++, entity.getName());
        ps.setString(count++, entity.getRole().getName());
        ps.setString(count, entity.getTab());
    }



}
