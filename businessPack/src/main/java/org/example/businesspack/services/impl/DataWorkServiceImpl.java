package org.example.businesspack.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.businesspack.bd.tables.records.DataWorkRecord;
import org.example.businesspack.configs.InitializeDatabase;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.metrics.JavaFxMetrics;
import org.example.businesspack.repositories.DataWorkRepository;
import org.example.businesspack.services.DataWorkService;
import org.example.businesspack.utils.Builder;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Map;

import static org.example.businesspack.bd.Tables.DATA_WORK;
import static org.example.businesspack.logs.LogMessage.*;

@RequiredArgsConstructor
@Slf4j
public class DataWorkServiceImpl implements DataWorkService {

    private final DataWorkRepository accountRepository = new DataWorkRepository();
    private final DSLContext dsl = InitializeDatabase.dslContext;
    private final JavaFxMetrics metrics = new JavaFxMetrics();

    private final static List<TableField<DataWorkRecord, ?>> columns = List.of(
            DATA_WORK.NAME, DATA_WORK.UNIT_MEAS, DATA_WORK.COUNT, DATA_WORK.PRICE,
            DATA_WORK.VAT, DATA_WORK.SUMMA, DATA_WORK.GROUP, DATA_WORK.TAB);

    @Override
    public Integer save(DataWorkDto entity) {
        String methodName = "save()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), entity);

        Record record = Builder.to(dsl, entity);
        Integer result = accountRepository.save(dsl, columns, record);
        log.debug(RESULT_METHOD.getMessage(), result);

        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    @Override
    public List<DataWorkDto> get(Condition condition) {
        String methodName = "get()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), condition);

        var result = accountRepository.get(dsl, condition);
        log.debug(RESULT_LIST_METHOD.getMessage(), result.stream().limit(5).toList());

        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    @Override
    public void delete(DataWorkDto entity) {
        String methodName = "delete()";
        long start = System.currentTimeMillis();
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), entity);

        accountRepository.delete(dsl, DATA_WORK.ID.eq(entity.getIdParameter()));

        metrics.trackOperation("document-element.delete.time", System.currentTimeMillis() - start);
        metrics.pushMetrics();
        log.info(END_METHOD.getMessage(), methodName);
    }

    @Override
    public void deleteAllByTab(String tabName) {
        String methodName = "deleteAllByTab()";
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), tabName);

        accountRepository.delete(dsl, DATA_WORK.TAB.eq(tabName));
        log.info(END_METHOD.getMessage(), methodName);
    }

    @Override
    public Integer update(DataWorkDto entity) {
        String methodName = "update()";
        long start = System.currentTimeMillis();
        log.info(START_METHOD.getMessage(), methodName);
        log.debug(REQUEST_PARAMETERS.getMessage(), entity);

        Integer result;
        if (entity.getId() != null) {
            Map<Name, ?> setMap = Map.of(
                DSL.name("group"), entity.getGroupParameter(),
                DSL.name("count"), entity.getCountParameter(),
                DSL.name("name"), entity.getNameParameter(),
                DSL.name("price"), entity.getPriceParameter(),
                DSL.name("summa"), entity.getSummaParameter(),
                DSL.name("unit_meas"), entity.getUnitMeasParameter(),
                DSL.name("vat"), entity.getVatParameter()
            );

            result = accountRepository.update(dsl, setMap, DATA_WORK.ID.eq(entity.getIdParameter()));

            metrics.trackOperation("document-element.update.time", System.currentTimeMillis() - start);
            metrics.pushMetrics();
            log.debug("Since the object exists, we update it.");
        } else {
            Record record = Builder.to(dsl, entity);
            result = accountRepository.save(dsl, columns, record);
            log.debug("Since the object does not exist, we create it.");
        }

        log.debug(RESULT_METHOD.getMessage(), result);
        log.info(END_METHOD.getMessage(), methodName);
        return result;
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not support operation");
    }

}
