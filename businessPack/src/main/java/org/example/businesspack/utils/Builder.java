package org.example.businesspack.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.entities.Person;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Builder {

    public DataWork buildDataWork(ResultSet resultSet) throws SQLException {
        return DataWork.builder()
                .id(resultSet.getLong("id"))
                .count(resultSet.getString("count"))
                .summa(resultSet.getString("summa"))
                .vat(resultSet.getString("vat"))
                .price(resultSet.getString("price"))
                .name(resultSet.getString("name"))
                .group(resultSet.getString("group"))
                .unitMeas(resultSet.getString("unit_meas"))
                .build();
    }

    public Person buildPerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .name(resultSet.getString("name"))
                .build();
    }

}
