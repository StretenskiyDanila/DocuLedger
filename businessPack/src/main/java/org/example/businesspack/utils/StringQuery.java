package org.example.businesspack.utils;

import org.example.businesspack.configs.InitializeDatabase;

import org.example.businesspack.Tables;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringQuery {

        /*
         * Запросы с таблицей выполненных работ
         */

        public void s() {
                var dsl = InitializeDatabase.dslContext;
                dsl.select(Tables)
        }

        public final String QUERY_GET_DATA_WORK = "SELECT * FROM data_work;";

        public final String QUERY_GET_DATA_WORK_FOR_TAB = "SELECT * FROM data_work WHERE tab = ?;";

        public final String QUERY_INSERT_DATA_WORK = "INSERT INTO data_work(\"group\", count, name, price, summa, unit_meas, vat, tab) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                        "RETURNING id;";

        public final String QUERY_DELETE_DATA_WORK = "DELETE FROM data_work " +
                        "WHERE id = ?";

        public final String QUERY_UPDATE_DATA_WORK = "UPDATE data_work " +
                        "SET \"group\" = ?, count = ?, name = ?, price = ?, summa = ?, unit_meas = ?, vat = ? " +
                        "WHERE id = ? " +
                        "RETURNING id;";

        /*
         * Запросы с физ.лицами
         */

        public final String QUERY_GET_PERSON_FOR_ROLE = "SELECT * FROM person WHERE role = ? AND tab = ?;";

        public final String QUERY_INSERT_PERSON = "INSERT INTO person(name, role, tab) " +
                        "VALUES (?, ?, ?) " +
                        "RETURNING id;";

        public final String QUERY_UPDATE_PERSON = "UPDATE person " +
                        "SET last_used = current_date, usage_count = (SELECT usage_count FROM person WHERE id = ?) + 1 "
                        +
                        "WHERE id = ? " +
                        "RETURNING id;";

        public final String QUERY_DELETE_PERSON = "DELETE FROM person " +
                        "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month') AND usage_count <= 1;";

        public final String QUERY_UPDATE_MONTH_PERSON = "UPDATE person " +
                        "SET usage_count = 0, last_used = CURRENT_DATE " +
                        "WHERE CURRENT_DATE = DATE(CURRENT_DATE, 'start of month')";

}
