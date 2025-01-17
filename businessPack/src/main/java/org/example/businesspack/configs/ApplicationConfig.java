package org.example.businesspack.configs;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class ApplicationConfig {

    private static final String FILE_NAME = "businessPack/src/main/resources/application.properties";
    private static final Properties properties = new Properties();

    static {
        try (var inputStream = new FileInputStream(FILE_NAME)) {
            properties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace(); //TODO: поправить на логирование
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

}
