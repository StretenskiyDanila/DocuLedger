package org.example.businesspack.repositories;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.mapper.RecordPersonMapper;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.businesspack.bd.Tables.PERSON;

public class PersonRepository {

    public Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record) {
        var fetch = dsl.insertInto(PERSON, columns)
                .values(record)
                .returning(PERSON.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getId();
    }

    public void delete(DSLContext dsl, Condition condition) throws DataAccessException {
        dsl.deleteFrom(PERSON)
                .where(condition)
                .execute();
    }

    public List<PersonDto> get(DSLContext dsl, Condition condition) {
        return dsl.selectFrom(PERSON)
                .where(condition)
                .fetch()
                .map(new RecordPersonMapper());
    }

    public Optional<PersonDto> getEntity(DSLContext dsl, Condition condition) {
        return Optional.ofNullable(dsl.selectFrom(PERSON)
                .where(condition)
                .fetchOne(new RecordPersonMapper()));
    }

    public Integer getUsageCount(DSLContext dsl, Condition condition) {
        return dsl.select(PERSON.USAGE_COUNT)
                .from(PERSON)
                .where(condition)
                .fetchOne()
                .getValue(PERSON.USAGE_COUNT);
    }

    public Integer update(DSLContext dsl, Map<Name, ?> parameter, Condition condition) {
        var fetch = dsl.update(PERSON)
                .set(parameter)
                .where(condition)
                .returningResult(PERSON.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getValue(PERSON.ID);
    }

}
