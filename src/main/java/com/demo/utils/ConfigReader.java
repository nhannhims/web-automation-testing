package com.demo.utils;

import com.demo.constants.FrameworkConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/configs/config.properties";

    static {
        String env = System.getProperty(FrameworkConstants.ENV);
        String configFileName;
        
        if (env == null || env.isEmpty()) {
            configFileName = "config.properties";
        } else {
            configFileName = env.toLowerCase() + ".properties";
        }
        
        String fullConfigPath = "src/test/resources/configs/" + configFileName;
        
        try (FileInputStream fileInputStream = new FileInputStream(fullConfigPath)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("WARNING: Could not load config file at " + fullConfigPath + ". Falling back to config.properties");
            try (FileInputStream defaultStream = new FileInputStream(CONFIG_PATH)) {
                properties = new Properties();
                properties.load(defaultStream);
            } catch (IOException ex) {
                throw new RuntimeException("Could not load even the default config.properties!");
            }
        }
    }

    /**
     * Get property value by key from config.properties
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }
}
