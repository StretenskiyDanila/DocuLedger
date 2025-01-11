package org.example.businesspack.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.enums.PersonRole;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Builder {

    public DataWork buildDataWork(ResultSet resultSet) throws SQLException {
        return DataWork.builder()
                .id(resultSet.getLong("id"))
                .count(resultSet.getInt("count"))
                .summa(resultSet.getBigDecimal("summa"))
                .vat(resultSet.getBigDecimal("vat"))
                .price(resultSet.getBigDecimal("price"))
                .name(resultSet.getString("name"))
                .group(resultSet.getString("group"))
                .unitMeas(resultSet.getString("unit_meas"))
                .tab(resultSet.getString("tab"))
                .build();
    }

    public Person buildPerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .role(PersonRole.valueOf(resultSet.getString("role").toUpperCase()))
                .lastUsed(Date.valueOf(resultSet.getString("last_used")).toLocalDate())
                .build();
    }

}
