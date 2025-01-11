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
            
            return getIdExecute(ps);
        }
    }

    @Override
    public void delete(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_DELETE_DATA_WORK)){
            ps.setLong(1, entity.getId());
            ps.execute();
        }
    }

    @Override
    public Long update(DataWork entity) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_UPDATE_DATA_WORK)){
            int count = 1;
            buildPs(entity, ps, count);
            ps.setLong(8, entity.getId());

            return getIdExecute(ps);
        }
    }

    @Override
    public List<DataWork> get(String... param) throws SQLException {
        try (PreparedStatement ps = da.getConnection().prepareStatement(StringQuery.QUERY_GET_DATA_WORK_FOR_TAB)) {
            ps.setString(1, param[0]);
            ResultSet rs = ps.executeQuery();

            List<DataWork> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(Builder.buildDataWork(rs));
            }
            return entities;
        }
    }

}
