package com.demo.drivers;

import com.demo.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import com.demo.constants.TimeConstants;

public class DriverManager {

    // Use ThreadLocal to support safe parallel testing
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        if (getDriver() == null) {
            WebDriver webDriver;
            
            switch (browser.toLowerCase()) {
                case FrameworkConstants.FIREFOX:
                    webDriver = FirefoxDriverManager.createDriver();
                    break;
                case FrameworkConstants.EDGE:
                    webDriver = EdgeDriverManager.createDriver();
                    break;
                case FrameworkConstants.CHROME:
                default:
                    webDriver = ChromeDriverManager.createDriver();
                    break;
            }
            
            // Set default timeouts from TimeConstants
            webDriver.manage().timeouts().implicitlyWait(TimeConstants.IMPLICIT_WAIT);
            webDriver.manage().timeouts().pageLoadTimeout(TimeConstants.PAGE_LOAD_WAIT);
            
            driver.set(webDriver);
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Remove ThreadLocal to avoid memory leak
        }
    }
}
