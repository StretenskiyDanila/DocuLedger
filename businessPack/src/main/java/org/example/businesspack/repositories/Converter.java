package org.example.businesspack.repositories;

import org.example.businesspack.entities.DataWork;
import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.PersonRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {

    public static Object makeEntity(ResultSet resultSet, String tableName) throws SQLException {
        if (tableName.equals("data_work")) {
            return DataWork.builder()
                    .count(resultSet.getString("count"))
                    .summa(resultSet.getString("summa"))
                    .vat(resultSet.getString("vat"))
                    .price(resultSet.getString("price"))
                    .name(resultSet.getString("name"))
                    .group(resultSet.getString("group"))
                    .unitMeas(resultSet.getString("unit_meas"))
                    .build();
        } else if (tableName.equals("person")) {
            return Person.builder()
                    .name(resultSet.getString("name"))
                    .role(PersonRole.valueOf(resultSet.getString("role")))
                    .build();
        } else {
            return null;
        }
    }

}
