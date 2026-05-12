package com.demo.drivers;

import com.demo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager {

    public static WebDriver createDriver() {

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }

        return new EdgeDriver(options);
    }
}
