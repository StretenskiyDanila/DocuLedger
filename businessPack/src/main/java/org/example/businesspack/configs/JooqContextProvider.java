package org.example.businesspack.configs;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;

public class JooqContextProvider {

    public static DSLContext getDSLContext(String url, String username, String password) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        return DSL.using(dataSource, SQLDialect.SQLITE);
    }

}
