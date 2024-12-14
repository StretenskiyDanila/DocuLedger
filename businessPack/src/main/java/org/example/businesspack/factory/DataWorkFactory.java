package org.example.businesspack.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.businesspack.entities.DataWork;

public class DataWorkFactory implements EntityFactory<DataWork> {

    @Override
    public DataWork create(ResultSet resultSet) throws SQLException {
        return DataWork.builder()
                .count(resultSet.getString("count"))
                .summa(resultSet.getString("summa"))
                .vat(resultSet.getString("vat"))
                .price(resultSet.getString("price"))
                .name(resultSet.getString("name"))
                .group(resultSet.getString("group"))
                .unitMeas(resultSet.getString("unit_meas"))
                .build();
    }

}
