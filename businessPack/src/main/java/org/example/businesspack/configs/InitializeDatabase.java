package org.example.businesspack.configs;

import org.jooq.DSLContext;

public class InitializeDatabase {

    private static final String URL = ApplicationConfig.getProperties("db_url");
    private static final String USER_NAME = ApplicationConfig.getProperties("db_user");
    private static final String PASSWORD = ApplicationConfig.getProperties("db_pwd");

    public static final DSLContext dslContext = JooqContextProvider.getDSLContext(URL, USER_NAME, PASSWORD);

    public static void initialize() {
        FlywayContextProvider.initialize(URL, USER_NAME, PASSWORD);
    }

}
