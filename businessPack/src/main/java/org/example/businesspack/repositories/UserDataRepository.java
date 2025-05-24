package org.example.businesspack.repositories;

import org.example.businesspack.dto.UserDataDto;
import org.example.businesspack.dto.mapper.RecordUserDataMapper;
import org.jooq.Record;
import org.jooq.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.businesspack.bd.Tables.PERSON;
import static org.example.businesspack.bd.Tables.USER_DATA;

public class UserDataRepository {

    public Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record) {
        var fetch = dsl.insertInto(USER_DATA, columns)
                .values(record)
                .returning(USER_DATA.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getId();
    }

    public Optional<UserDataDto> get(DSLContext dsl, Condition condition) {
        return Optional.ofNullable(dsl.selectFrom(USER_DATA)
                .where(condition)
                .fetchOne(new RecordUserDataMapper()));
    }

    public Integer update(DSLContext dsl, Map<Name, ?> parameter, Condition condition) {
        var fetch = dsl.update(USER_DATA)
                .set(parameter)
                .where(condition)
                .returningResult(USER_DATA.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getValue(PERSON.ID);
    }

}
