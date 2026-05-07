package com.demo.tests.base;

import com.demo.constants.FrameworkConstants;
import com.demo.drivers.DriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters(FrameworkConstants.BROWSER)
    @BeforeMethod
    public void setUp(@Optional(FrameworkConstants.CHROME) String browser) {
        // Initialize browser based on parameter from testng.xml (default is chrome)
        DriverManager.initDriver(browser);
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Quit browser after each test method
        DriverManager.quitDriver();
    }
}
