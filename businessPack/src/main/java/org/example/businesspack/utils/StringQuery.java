package org.example.businesspack.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringQuery {

    /*
    * Общий для всех таблиц геттер
    * */

    public final String QUERY_GET_ALL =
            "SELECT * FROM %s;";


    /*
    * Запросы с таблицей выполненных работ
    * */

    public final String QUERY_INSERT_ACCOUNT =
            "INSERT INTO data_work(\"group\", count, name, price, summa, unit_meas, vat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

    public final String QUERY_DELETE_ACCOUNT =
            "DELETE FROM data_work " +
                    "WHERE \"group\" = ? AND count = ? AND name = ? AND price = ? AND summa = ? AND unit_meas = ? AND vat = ?;";

    public final String QUERY_UPDATE_ACCOUNT =
            "UPDATE data_work " +
                    "SET \"group\" = ?, count = ?, name = ?, price = ?, summa = ?, unit_meas = ?, vat = ? " +
                    "WHERE \"group\" = ? AND count = ? AND name = ? AND price = ? AND summa = ? AND unit_meas = ? AND vat = ?;";

    /*
    * Запросы с физ.лицами
    * */

    public final String QUERY_GET_PERSON_FOR_ROLE =
            "SELECT * FROM person WHERE role = ?;";

    public final String QUERY_INSERT_PERSON =
            "INSERT INTO person(name, role) " +
                    "VALUES (?, ?);";

    public final String QUERY_UPDATE_PERSON = 
             "UPDATE person " +
                    "SET last_used = ?, usage_count = ? " + 
                    "WHERE name = ? AND role = ?;";

    public final String QUERY_DELETE_PERSON =
             "DELETE FROM person " +
                    "WHERE last_used < DATE('now', '-1 month') AND usage_count = 1;";

    public final String QUERY_UPDATE_MONTH_PERSON =
             "UPDATE person " +
                    "SET usage = 0 " + 
                    "WHERE last_used < DATE('now', '-1 month');";

}
