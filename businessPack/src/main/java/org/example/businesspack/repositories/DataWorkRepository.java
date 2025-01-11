package org.example.businesspack.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.utils.QueryUtils;

public abstract class DataWorkRepository extends TableRepository<DataWork> {

    protected void buildPs(DataWork entity, PreparedStatement ps, int count) throws SQLException {
        QueryUtils.toString(count++, ps, entity.getGroup());
        QueryUtils.toInt(count++, ps, entity.getCount());
        QueryUtils.toString(count++, ps, entity.getName());
        QueryUtils.toBigDecimal(count++, ps, entity.getPrice());
        QueryUtils.toBigDecimal(count++, ps, entity.getSumma());
        QueryUtils.toString(count++, ps, entity.getUnitMeas());
        QueryUtils.toBigDecimal(count++, ps, entity.getVat());
        QueryUtils.toString(count, ps, entity.getTab());
    }

}
