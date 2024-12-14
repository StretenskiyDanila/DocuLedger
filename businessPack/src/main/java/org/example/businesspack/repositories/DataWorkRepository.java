package org.example.businesspack.repositories;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.factory.DataWorkFactory;
import org.example.businesspack.factory.EntityFactory;
import org.example.businesspack.utils.QueryUtils;
import org.example.businesspack.utils.StringQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataWorkRepository extends TableRepository<DataWork> {

    @Override
    protected String getTableName() {
        return new DataWork().getTableName();
    }

    @Override
    protected String getQueryGet() {
        return StringQuery.QUERY_GET_ALL;
    }

    @Override
    protected EntityFactory<DataWork> getEntityFactory() {
        return new DataWorkFactory();
    }

    @Override
    public Long save(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_INSERT_ACCOUNT)) {
            buildPs(entity, ps, 1);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        }
    }

    @Override
    public void delete(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_DELETE_ACCOUNT)){
            buildPs(entity, ps, 1);
            ps.execute();
        }
    }

    @Override
    public Long update(DataWork entityUpdate, DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_ACCOUNT)){
            buildPs(entityUpdate, ps, 1);
            buildPs(entity, ps, 8);
            ps.execute();

            return 1L;
        }
    }

    private void buildPs(DataWork entity, PreparedStatement ps, int count) throws SQLException {
        QueryUtils.toString(count++, ps, entity.getGroup());
        QueryUtils.toString(count++, ps, entity.getCount());
        QueryUtils.toString(count++, ps, entity.getName());
        QueryUtils.toString(count++, ps, entity.getPrice());
        QueryUtils.toString(count++, ps, entity.getSumma());
        QueryUtils.toString(count++, ps, entity.getUnitMeas());
        QueryUtils.toString(count, ps, entity.getVat());
    }

}
