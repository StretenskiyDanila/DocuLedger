package org.example.businesspack.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.example.businesspack.configs.ApplicationConfig;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionUtils {

    @Getter
    private static final Connection connection;

    private static final String URL = ApplicationConfig.getProperties("db_url");
    private static final String USER_NAME = ApplicationConfig.getProperties("db_user");
    private static final String PASSWORD = ApplicationConfig.getProperties("db_pwd");

    static {
        Connection v = null;
        try {
            v = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            log.error("Не удалось получить подлкючение к БД. Error: ", e.getMessage());
        }
        connection = v;
    }

}
