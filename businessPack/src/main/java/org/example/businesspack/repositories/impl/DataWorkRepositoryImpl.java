package org.example.businesspack.repositories.impl;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.repositories.DataWorkRepository;
import org.example.businesspack.utils.Builder;
import org.example.businesspack.utils.StringQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataWorkRepositoryImpl extends DataWorkRepository {

    @Override
    public Long save(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_INSERT_DATA_WORK)) {
            buildPs(entity, ps, 1);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        }
    }

    @Override
    public void delete(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_DELETE_DATA_WORK)){
            buildPs(entity, ps, 1);
            ps.execute();
        }
    }

    @Override
    public Long update(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_DATA_WORK)){
            buildPs(entity, ps, 1);
            ps.setLong(8, entity.getId());
            ps.execute();

            return 1L;
        }
    }

    @Override
    public List<DataWork> get() throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_GET_DATA_WORK)) {
            ResultSet rs = ps.executeQuery();

            List<DataWork> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(Builder.buildDataWork(rs));
            }
            return entities;
        }
    }

}
