package com.demo.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager {

    public static WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        // Add Firefox specific options here
        // options.addArguments("-headless"); 
        
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize(); // Maximize window for Firefox
        return driver;
    }
}
