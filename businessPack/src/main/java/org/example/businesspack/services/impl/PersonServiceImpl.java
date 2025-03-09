package org.example.businesspack.services.impl;

import static org.example.businesspack.bd.Tables.PERSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.example.businesspack.bd.Tables;
import org.example.businesspack.bd.tables.records.PersonRecord;
import org.example.businesspack.configs.InitializeDatabase;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.repositories.PersonRepository;
import org.example.businesspack.repositories.impl.PersonRepositoryImpl;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.utils.Builder;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.TableField;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import lombok.extern.slf4j.Slf4j;

import org.jooq.Record;

import static org.example.businesspack.logs.LogMessage.*;

@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository = new PersonRepositoryImpl();
    private final DSLContext dsl = InitializeDatabase.dslContext;

    private final static List<TableField<PersonRecord, ?>> columns = List.of(
            PERSON.NAME, PERSON.ROLE, PERSON.TAB);

    @Override
    public Integer save(PersonDto entity) {
        String methodName = "save()";
        log.info(START_METHOD.getMessage(), methodName);

        Record record = Builder.to(dsl, entity);
        return personRepository.save(dsl, columns, record);
    }

    @Override
    public List<PersonDto> get(Condition condition) {
        String methodName = "get()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), condition);

        var result = personRepository.get(dsl, condition);
        log.debug(RESULT_LIST_METHOD.getMessage(), result.stream().limit(5).toList());

        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    @Override
    public PersonDto getById(Integer id) {
        String methodName = "getById()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), id);

        var result = personRepository.getEntity(dsl, Tables.PERSON.ID.eq(id))
                .orElseThrow();
        log.debug(RESULT_METHOD.getMessage(), result);

        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    @Override
    public Integer update(PersonDto entity) {
        String methodName = "update()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), entity);

        Integer result;
        if (entity.getId() != null) {
            Integer usageCountCurrent = personRepository.getUsageCount(dsl, PERSON.ID.eq(entity.getId()));
            Map<Name, ?> setMap = Map.of(
                DSL.name("last_used"), LocalDateTime.now(),
                DSL.name("usage_count"), usageCountCurrent + 1
            );
            Condition condition = PERSON.ID.eq(entity.getId());

            result = personRepository.update(dsl, setMap, condition);
            log.debug("Since the object exists, we update it.");
        } else {
            Record record = Builder.to(dsl, entity);
            result = personRepository.save(dsl, columns, record);
            log.debug("Since the object does not exist, we create it.");
        }

        log.debug(RESULT_METHOD.getMessage(), result);
        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    // "UPDATE person " +
    // "SET last_used = current_date, usage_count = (SELECT usage_count FROM person WHERE id = ?) + 1 "
    // +
    // "WHERE id = ? " +
    // "RETURNING id;";

    @Override
    public void delete() {
        String methodName = "delete()";
        log.info(START_METHOD.getMessage(), methodName);

        try {
            Field<LocalDate> fieldFirstDateMonth =
                    DSL.field("date(CURRENT_DATE, 'start of month')", LocalDate.class);
            Condition conditionDelete =
                    DSL.currentLocalDate().eq(fieldFirstDateMonth).and(PERSON.USAGE_COUNT.le(1));

            personRepository.delete(dsl, conditionDelete);
            log.debug("Deleted old records");

            Map<Name, ?> setMap = Map.of(
                    DSL.name("usage_count"), 0,
                    DSL.name("last_used"), LocalDateTime.now());
            Condition conditionUpdate = DSL.currentLocalDate().eq(fieldFirstDateMonth);
            personRepository.update(dsl, setMap, conditionUpdate);
            log.debug("Updated parameter for records");
        } catch (DataAccessException ex) {
            log.error(ERROR_CONNECTION.getMessage(), ex.getMessage());
        }

        log.info(END_METHOD.getMessage(), methodName);
    }

    // "UPDATE person " +
    // "SET usage_count = 0, last_used = CURRENT_DATE " +
    // "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month')";

    // "DELETE FROM person " +
    // "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month') AND usage_count <=
    // 1;";

    @Override
    public void delete(PersonDto entity) {
        throw new UnsupportedOperationException("Not support operation");
    }

}
