package com.demo.listeners;

import com.demo.drivers.DriverManager;
import com.demo.utils.ScreenshotUtils;
import io.qameta.allure.Allure;
import com.demo.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test FAILED: " + result.getMethod().getMethodName());
        
        // Get WebDriver from DriverManager to take a screenshot
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            LogUtils.info("Taking failure screenshot...");
            // Save to file
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
            // Attach to Allure
            Allure.addAttachment(result.getName() + "_Failure_Screenshot", "image/png", 
                new ByteArrayInputStream(ScreenshotUtils.getScreenshotAsBytes(driver)), ".png");
        } else {
            LogUtils.warn("Cannot take screenshot because WebDriver is not initialized!");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("=== Test Suite started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("=== Test Suite finished: " + context.getName() + " ===");
    }
}
