package com.demo.listeners;

import com.demo.drivers.DriverManager;
import com.demo.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test FAILED: " + result.getMethod().getMethodName());
        
        // Get WebDriver from DriverManager to take a screenshot
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            System.out.println("Taking failure screenshot...");
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        } else {
            System.out.println("Cannot take screenshot because WebDriver is not initialized!");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite finished: " + context.getName() + " ===");
    }
}
