package org.example.businesspack.configs;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Slf4j
public class ApplicationConfig {

    private static final String FILE_NAME = "src/main/resources/application.yaml";
    private static final Properties properties = new Properties();

    private static final String MESSAGE_ERROR = "Не удалось загрузить параметры. Error: {}";

    static {
        try (var inputStream = new FileInputStream(FILE_NAME)) {
            properties.load(inputStream);
        }
        catch (IOException e) {
            log.error(MESSAGE_ERROR, e.getMessage());
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

}
