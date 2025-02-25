package org.example.businesspack.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.businesspack.dto.PersonDto;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;

public interface PersonRepository {

    public abstract Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record);

    public abstract void delete(DSLContext dsl, Condition condition);

    public abstract Integer update(DSLContext dsl, Map<Name, ?> entity, Condition condition);

    public abstract List<PersonDto> get(DSLContext dsl, Condition condition);

    public abstract Optional<PersonDto> getEntity(DSLContext dsl, Condition condition);

    public Integer getUsageCount(DSLContext dsl, Condition condition);

}
