package org.example.businesspack.configs;

import lombok.Getter;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccessor {

    private static final ApplicationConfig appProperties = ApplicationConfig.getInstance();

    private static final String URL = appProperties.getProperties("db_url");
    private static final String USER_NAME = appProperties.getProperties("db_user");
    private static final String PASSWORD = appProperties.getProperties("db_pwd");

    @Getter
    private static DataAccessor dataAccessor = new DataAccessor(URL, USER_NAME, PASSWORD);

    @Getter
    private Connection connection;

    private DataAccessor(String url, String userName, String password) {
        try {
            connection = DriverManager.getConnection(url, userName, password);

            Flyway flyway = Flyway.configure()
                    .dataSource(url, userName, password)
                    .load();

            flyway.migrate();
            System.out.println("Миграции успешно применены.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
