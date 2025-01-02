package org.example.businesspack.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.utils.QueryUtils;

public abstract class DataWorkRepository extends TableRepository<DataWork> {

    public abstract List<DataWork> get(String tab) throws SQLException;

    protected void buildPs(DataWork entity, PreparedStatement ps, int count) throws SQLException {
        QueryUtils.toString(count++, ps, entity.getGroup());
        QueryUtils.toString(count++, ps, entity.getCount());
        QueryUtils.toString(count++, ps, entity.getName());
        QueryUtils.toString(count++, ps, entity.getPrice());
        QueryUtils.toString(count++, ps, entity.getSumma());
        QueryUtils.toString(count++, ps, entity.getUnitMeas());
        QueryUtils.toString(count++, ps, entity.getVat());
        QueryUtils.toString(count, ps, entity.getTab());
    }

}
