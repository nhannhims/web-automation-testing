package com.demo.drivers;

import com.demo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager {

    public static WebDriver createDriver() {

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);

        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("-headless");
            options.addArguments("-width", "1920");
            options.addArguments("-height", "1080");
        }

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); // Maximize window for Firefox
        return driver;
    }
}
