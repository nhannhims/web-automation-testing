package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class HomePage extends CommonPage {
    // Locator
    private String txtLoggedInSuccessfullyHeading = "xpath=//a[contains(text(), 'Logged in as')]";

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if the 'Logged in as [username]' text is visible in the header.
     * @return true if visible, false otherwise
     */
    @Step("Verify 'Logged in as user' is visible")
    public boolean isLoggedInUserVisible() {
        return isElementVisible(txtLoggedInSuccessfullyHeading);
    }
}
