package com.demo.pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
    private PageFactoryManager() {
    }

    public static HomePage getHomePage(WebDriver driver) {
        return new HomePage(driver);
    }

    public static LoginPage getLoginPage(WebDriver driver) {
        return new LoginPage(driver);
    }
    
    // Add other pages as needed
}
