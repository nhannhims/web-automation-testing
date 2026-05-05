package com.demo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Take a screenshot and save it to the screenshots/ directory
     * @param driver WebDriver instance
     * @param testName Name of the test case (used for file naming)
     * @return Path to the saved screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot!");
            return "";
        }
        
        String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + dateName + ".png";
        String directory = System.getProperty("user.dir") + "/screenshots/";
        String filePath = directory + fileName;

        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved at: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return "";
        }
    }
    public static byte[] getScreenshotAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
