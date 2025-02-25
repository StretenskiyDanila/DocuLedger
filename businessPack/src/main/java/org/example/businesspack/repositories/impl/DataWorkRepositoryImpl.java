package org.example.businesspack.repositories.impl;

import static org.example.businesspack.bd.Tables.DATA_WORK;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.mapper.RecordDataWorkMapper;
import org.example.businesspack.repositories.DataWorkRepository;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;

public class DataWorkRepositoryImpl implements DataWorkRepository {

    @Override
    public Integer save(DSLContext dsl, Collection<? extends Field<?>> columns, Record record) {
        return dsl
                .insertInto(DATA_WORK, columns)
                .values(record)
                .returning(DATA_WORK.ID)
                .fetchOne()
                .getId();
    }
    // "INSERT INTO data_work(\"group\", count, name, price, summa, unit_meas, vat, tab) "
    //     +
    //     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
    //     "RETURNING id;"
    // try (PreparedStatement ps = ConnectionUtils.getConnection()
    //         .prepareStatement(StringQuery.QUERY_INSERT_DATA_WORK)) {
    //     buildPs(entity, ps, 1);

    //     return getIdExecute(ps);
    // }
    @Override
    public void delete(DSLContext dsl, Condition condition) {
        dsl.deleteFrom(DATA_WORK)
                .where(condition)
                .execute();
    }


    // "DELETE FROM data_work " +
    // "WHERE id = ?";
    // try (PreparedStatement ps =
    // ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_DELETE_DATA_WORK)){
    // ps.setLong(1, entity.getId().getValue());
    // ps.execute();
    // }
    @Override
    public Integer update(DSLContext dsl, Map<Name, ?> parameter, Condition condition) {
        var fetch = dsl.update(DATA_WORK)
                .set(parameter)
                .where(condition)
                .returningResult(DATA_WORK.ID)
                .fetchOne();
        return fetch == null ? null : fetch.getValue(DATA_WORK.ID);
    }
    // "UPDATE data_work " +
    // "SET \"group\" = ?, count = ?, name = ?, price = ?, summa = ?, unit_meas = ?,
    // vat = ? " +
    // "WHERE id = ? " +
    // "RETURNING id;";
    // try (PreparedStatement ps =
    // ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_DATA_WORK)){
    // int count = 1;
    // buildPs(entity, ps, count);
    // ps.setLong(8, entity.getId().getValue());

    // return getIdExecute(ps);
    // }

    @Override
    public List<DataWorkDto> get(DSLContext dsl, Condition condition) {
        return dsl.selectFrom(DATA_WORK)
                .where(condition)
                .fetch()
                .map(new RecordDataWorkMapper());
    }
    // "SELECT * FROM data_work WHERE tab = ?;"
    // try (PreparedStatement ps =
    // ConnectionUtils.getConnection().prepareStatement(StringQuery.QUERY_GET_DATA_WORK_FOR_TAB))
    // {
    // ps.setString(1, param[0]);
    // ResultSet rs = ps.executeQuery();

    // List<DataWork> entities = new ArrayList<>();
    // while (rs.next()) {
    // entities.add(Builder.buildDataWork(rs));
    // }
    // return entities;
    // }
    // }

    
    @Override
    public Optional<DataWorkDto> getEntity(DSLContext dsl, Condition condition) {
        return Optional.ofNullable(dsl.selectFrom(DATA_WORK)
                .where(condition)
                .fetchOne()
                .into(DataWorkDto.class));
    }

}
