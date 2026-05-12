package com.demo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
            LogUtils.error("Driver is null. Cannot take screenshot!");
            return "";
        }
        
        String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + dateName + ".png";
        String directory = System.getProperty("user.dir") + "/screenshots/";
        String filePath = directory + fileName;

        try {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            if (!destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LogUtils.info("Screenshot saved at: " + filePath);
            return filePath;
        } catch (IOException e) {
            LogUtils.error("Exception while taking screenshot: " + e.getMessage());
            return "";
        }
    }
    public static byte[] getScreenshotAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
