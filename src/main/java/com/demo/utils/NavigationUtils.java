package com.demo.utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class NavigationUtils {

    /**
     * Navigate to a specific URL
     */
    public static void openUrl(WebDriver driver, String url) {
        if (driver != null) {
            System.out.println("Navigating to URL: " + url);
            driver.get(url);
        }
    }

    /**
     * Get the current URL
     */
    public static String getCurrentUrl(WebDriver driver) {
        if (driver != null) {
            return driver.getCurrentUrl();
        }
        return null;
    }

    /**
     * Get the title of the current page
     */
    public static String getPageTitle(WebDriver driver) {
        if (driver != null) {
            return driver.getTitle();
        }
        return null;
    }

    /**
     * Refresh the current page
     */
    public static void refreshPage(WebDriver driver) {
        if (driver != null) {
            System.out.println("Refreshing the page...");
            driver.navigate().refresh();
        }
    }

    /**
     * Navigate back to the previous page
     */
    public static void navigateBack(WebDriver driver) {
        if (driver != null) {
            System.out.println("Navigating back to the previous page...");
            driver.navigate().back();
        }
    }

    /**
     * Navigate forward to the next page
     */
    public static void navigateForward(WebDriver driver) {
        if (driver != null) {
            System.out.println("Navigating forward to the next page...");
            driver.navigate().forward();
        }
    }

    /**
     * Switch to a newly opened window/tab
     */
    public static void switchToNewWindow(WebDriver driver) {
        if (driver != null) {
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            
            for (String window : allWindows) {
                if (!window.equals(currentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("Switched to new window/tab: " + driver.getTitle());
                    break;
                }
            }
        }
    }

    /**
     * Close current window and switch back to main window
     */
    public static void closeAndSwitchToMainWindow(WebDriver driver, String mainWindowHandle) {
        if (driver != null) {
            driver.close();
            driver.switchTo().window(mainWindowHandle);
            System.out.println("Closed current window and switched back to main window");
        }
    }
}
