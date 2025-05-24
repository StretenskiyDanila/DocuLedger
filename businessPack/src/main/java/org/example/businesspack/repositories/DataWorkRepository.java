package org.example.businesspack.repositories;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.mapper.RecordDataWorkMapper;
import org.jooq.Record;
import org.jooq.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.businesspack.bd.Tables.DATA_WORK;

public class DataWorkRepository {

    public Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record) {
        return dsl
                .insertInto(DATA_WORK, columns)
                .values(record)
                .returning(DATA_WORK.ID)
                .fetchOne()
                .getId();
    }
    
    public void delete(DSLContext dsl, Condition condition) {
        dsl.deleteFrom(DATA_WORK)
                .where(condition)
                .execute();
    }
    
    public Integer update(DSLContext dsl, Map<Name, ?> parameter, Condition condition) {
        var fetch = dsl.update(DATA_WORK)
                .set(parameter)
                .where(condition)
                .returningResult(DATA_WORK.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getValue(DATA_WORK.ID);
    }

    public List<DataWorkDto> get(DSLContext dsl, Condition condition) {
        return dsl.selectFrom(DATA_WORK)
                .where(condition)
                .fetch()
                .map(new RecordDataWorkMapper());
    }

    public Optional<DataWorkDto> getEntity(DSLContext dsl, Condition condition) {
        return Optional.ofNullable(dsl.selectFrom(DATA_WORK)
                .where(condition)
                .fetchOne()
                .into(DataWorkDto.class));
    }

}