package org.example.businesspack.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.businesspack.entities.Person;

public class PersonFactory implements EntityFactory<Person> {

    @Override
    public Person create(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .name(resultSet.getString("name"))
                .build();
    }

}
