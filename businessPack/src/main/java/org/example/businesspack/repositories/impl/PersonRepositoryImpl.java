package org.example.businesspack.repositories.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.repositories.PersonRepository;
import org.example.businesspack.utils.Builder;
import org.example.businesspack.utils.StringQuery;

public class PersonRepositoryImpl extends PersonRepository {

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
    public void delete() throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_DELETE_PERSON)) {
            ps.execute();
        }
    }

    @Override
    public Long update(Person entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_PERSON)) {
            int count = 1;
            ps.setString(count++, entity.getName());
            ps.setString(count++, entity.getName());
            ps.setString(count++, entity.getRole().getName());

            ps.execute();

            return 1L;
        }
    }

    @Override
    public List<Person> get(PersonRole role) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_GET_PERSON_FOR_ROLE)) {
            ps.setString(1, role.getName());
            ResultSet rs = ps.executeQuery();

            List<Person> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(Builder.buildPerson(rs));
            }
            return entities;
        }
    }

    @Override
    public boolean checkEntity(Person entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_GET_PERSON_FOR_NAME)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getRole().getName());
            ResultSet rs = ps.executeQuery();
            return rs.next();        
        }
    }

}