package org.example.businesspack.configs;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;

public class InitializeDatabase {

    private static final String URL = ApplicationConfig.getProperties("url");

    public static final DSLContext dslContext;

    static {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(URL);

        dslContext = DSL.using(dataSource, SQLDialect.SQLITE);
        //FlywayContextProvider.initialize(URL, USER_NAME, PASSWORD);
    }

}
