package org.example.businesspack.configs;

import org.flywaydb.core.Flyway;

public class FlywayContextProvider {

    public static void initialize(String url, String userName, String password) {
        Flyway flyway = Flyway.configure()
                .dataSource(url, userName, password)
                .load();

        flyway.migrate();
        System.out.println("Миграции успешно применены."); //TODO: поменять на логирование
    }

}
