package com.demo.drivers;

import com.demo.utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager {

    public static WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize window
        options.addArguments("--disable-notifications"); // Disable notifications
        
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        
        return new ChromeDriver(options);
    }
}
