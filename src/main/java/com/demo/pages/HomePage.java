package com.demo.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends CommonPage {
    // Locator
    private String txtLoggedInSuccessfullyHeading = "xpath=//a[contains(text(), 'Logged in as')]";

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public boolean isLoggedInUserVisible() {
        return isElementVisible(txtLoggedInSuccessfullyHeading);
    }
}
