package org.example.businesspack.configs;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ApplicationConfig {

    private final String FILE_NAME = "businessPack/src/main/resources/application.properties";
    private static final ApplicationConfig configFile = new ApplicationConfig();
    private final Properties properties = new Properties();
    private String msg = "";

    public ApplicationConfig() {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(FILE_NAME);
            properties.load(inputStream);
        }
        catch (IOException e) {
            msg = "Can't find/open property file";
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }

    public static ApplicationConfig getInstance() {
        return configFile;
    }

}
