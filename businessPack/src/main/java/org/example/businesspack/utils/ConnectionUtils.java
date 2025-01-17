package org.example.businesspack.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.example.businesspack.configs.ApplicationConfig;

import lombok.Getter;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        connection = v;
    }

}
