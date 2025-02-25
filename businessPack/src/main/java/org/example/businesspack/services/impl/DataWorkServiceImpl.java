package org.example.businesspack.services.impl;

import static org.example.businesspack.bd.Tables.DATA_WORK;

import java.util.List;
import java.util.Map;

import org.example.businesspack.bd.tables.records.DataWorkRecord;
import org.example.businesspack.configs.InitializeDatabase;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.repositories.DataWorkRepository;
import org.example.businesspack.repositories.impl.DataWorkRepositoryImpl;
import org.example.businesspack.services.DataWorkService;
import org.example.businesspack.utils.Builder;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataWorkServiceImpl implements DataWorkService {

    private final DataWorkRepository accountRepository = new DataWorkRepositoryImpl();
    private final DSLContext dsl = InitializeDatabase.dslContext;

    private final static List<TableField<DataWorkRecord, ?>> columns = List.of(
            DATA_WORK.NAME, DATA_WORK.UNIT_MEAS, DATA_WORK.COUNT, DATA_WORK.PRICE,
            DATA_WORK.VAT, DATA_WORK.SUMMA, DATA_WORK.GROUP, DATA_WORK.TAB);

    @Override
    public Integer save(DataWorkDto entity) {
        Record record = Builder.to(dsl, entity);
        return accountRepository.save(dsl, columns, record);
    }

    @Override
    public List<DataWorkDto> get(Condition condition) {
        return accountRepository.get(dsl, condition);
    }

    @Override
    public void delete(DataWorkDto entity) {
        accountRepository.delete(dsl, DATA_WORK.ID.eq(entity.getIdParameter()));
    }

    @Override
    public Integer update(DataWorkDto entity) {
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

            return accountRepository.update(dsl, setMap, DATA_WORK.ID.eq(entity.getIdParameter()));
        } else {
            Record record = Builder.to(dsl, entity);
            return accountRepository.save(dsl, columns, record);
        }
    
    }
    // "UPDATE data_work " +
    // "SET \"group\" = ?, count = ?, name = ?, price = ?, summa = ?, unit_meas = ?, vat = ? " +
    // "WHERE id = ? " +
    // "RETURNING id;";

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not support operation");
    }

}
