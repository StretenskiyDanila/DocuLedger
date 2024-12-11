package org.example.businesspack.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringQuery {

    public final String QUERY_GET_ALL =
            "SELECT * FROM %s;";

    public final String QUERY_INSERT_ACCOUNT =
            "INSERT INTO account(\"group\", count, name, price, summa, unit_meas, vat) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

    public final String QUERY_DELETE_ACCOUNT =
            "DELETE FROM account " +
                    "WHERE \"group\" = ? AND count = ? AND name = ? AND price = ? AND summa = ? AND unit_meas = ? AND vat = ?;";

    public final String QUERY_UPDATE_ACCOUNT =
            "UPDATE account " +
                    "SET \"group\" = ?, count = ?, name = ?, price = ?, summa = ?, unit_meas = ?, vat = ? " +
                    "WHERE \"group\" = ? AND count = ? AND name = ? AND price = ? AND summa = ? AND unit_meas = ? AND vat = ?;";

}
