package org.example.businesspack.repositories.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.businesspack.entities.Person;
import org.example.businesspack.repositories.PersonRepository;
import org.example.businesspack.utils.Builder;
import org.example.businesspack.utils.ConnectionUtils;
import org.example.businesspack.utils.StringQuery;

public class PersonRepositoryImpl extends PersonRepository {

    @Override
    public Long save(Person entity) throws SQLException {
        try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_INSERT_PERSON)) {
            buildPs(entity, ps, 1);
            
            return getIdExecute(ps);
        }
    }

    @Override
    public void delete() throws SQLException {
        try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_DELETE_PERSON)) {
            ps.execute();
        }
    }

    @Override
    public Long update(Person entity) throws SQLException {
        try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_PERSON)) {
            int count = 1;
            ps.setLong(count++, entity.getId());
            ps.setLong(count++, entity.getId());

            return getIdExecute(ps);
        }
    }

    @Override
    public List<Person> get(String... param) throws SQLException {
        try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_GET_PERSON_FOR_ROLE)) {
            ps.setString(1, param[0]);
            ps.setString(2, param[1]);
            ResultSet rs = ps.executeQuery();

            List<Person> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(Builder.buildPerson(rs));
            }
            return entities;
        }
    }

    @Override
    public void update() throws SQLException {
        try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_MONTH_PERSON)) {
            ps.execute();
        }
    }

}