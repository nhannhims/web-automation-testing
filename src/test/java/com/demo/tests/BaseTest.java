package com.demo.tests;

import com.demo.drivers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        // Initialize browser based on parameter from testng.xml (default is chrome)
        DriverManager.initDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        // Quit browser after each test method
        DriverManager.quitDriver();
    }
}
