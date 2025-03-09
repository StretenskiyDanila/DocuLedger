package org.example.businesspack.repositories.impl;

import static org.example.businesspack.bd.Tables.PERSON;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sun.jdi.InvocationException;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.mapper.RecordPersonMapper;
import org.example.businesspack.repositories.PersonRepository;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;

public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record) {
        var fetch = dsl.insertInto(PERSON, columns)
                .values(record)
                .returning(PERSON.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getId();
    }
    // "INSERT INTO person(name, role, tab) " +
    // "VALUES (?, ?, ?) " +
    // "RETURNING id;";
    // try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_INSERT_PERSON)) {
    //     buildPs(entity, ps, 1);

    //     return getIdExecute(ps);
    // }

    @Override
    public void delete(DSLContext dsl, Condition condition) throws DataAccessException {
        dsl.deleteFrom(PERSON)
                .where(condition)
                .execute();
    }
    // "DELETE FROM person " +
    // "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month') AND usage_count <= 1;"
    // try (PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_DELETE_PERSON)) {
    //     ps.execute();
    // }

    @Override
    public List<PersonDto> get(DSLContext dsl, Condition condition) {
        return dsl.selectFrom(PERSON)
                .where(condition)
                .fetch()
                .map(new RecordPersonMapper());
    }
    //"SELECT * FROM person WHERE role = ? AND tab = ?;"
    // try (PreparedStatement ps = ConnectionUtils.getConnection()
    //         .prepareStatement(StringQuery.QUERY_GET_PERSON_FOR_ROLE)) {
    //     ps.setString(1, param[0]);
    //     ps.setString(2, param[1]);
    //     ResultSet rs = ps.executeQuery();

    //     List<PersonDto> entities = new ArrayList<>();
    //     while (rs.next()) {
    //         entities.add(Builder.buildPerson(rs));
    //     }
    //     return entities;
    // }

    @Override
    public Optional<PersonDto> getEntity(DSLContext dsl, Condition condition) {
        return Optional.ofNullable(dsl.selectFrom(PERSON)
                .where(condition)
                .fetchOne(new RecordPersonMapper()));
    }

    @Override
    public Integer getUsageCount(DSLContext dsl, Condition condition) {
        return dsl.select(PERSON.USAGE_COUNT)
                .from(PERSON)
                .where(condition)
                .fetchOne()
                .getValue(PERSON.USAGE_COUNT);
    }

    @Override
    public Integer update(DSLContext dsl, Map<Name, ?> parameter, Condition condition) {
        var fetch = dsl.update(PERSON)
                .set(parameter)
                .where(condition)
                .returningResult(PERSON.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getValue(PERSON.ID);
    }

    // "UPDATE person " +
    // "SET last_used = current_date, usage_count = (SELECT usage_count FROM person
    // WHERE id = ?) + 1 "
    // +
    // "WHERE id = ? " +
    // "RETURNING id;";
    // try (PreparedStatement ps =
    // ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_PERSON))
    // {
    // int count = 1;
    // ps.setLong(count++, entity.getId());
    // ps.setLong(count++, entity.getId());

    // return getIdExecute(ps);
    // }

    // "UPDATE person " +
    // "SET usage_count = 0, last_used = CURRENT_DATE " +
    // "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month')";
    // try (PreparedStatement ps = ConnectionUtils.getConnection()
    // .prepareStatement(StringQuery.QUERY_UPDATE_MONTH_PERSON)) {
    // ps.execute();
    // }
}