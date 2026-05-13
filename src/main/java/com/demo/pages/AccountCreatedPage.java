package com.demo.pages;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class AccountCreatedPage extends CommonPage {
    // Locators
    private String txtAccountCreatedHeading = "xpath=//h2[contains(., 'Account Created')]";
    private String btnContinue = "css=a[data-qa='continue-button']";

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    /**
     * Verify that the 'ACCOUNT CREATED!' success message is visible.
     * @return true if visible, false otherwise
     */
    @Step("Check if account created heading is visible")
    public boolean isAccountCreatedVisible() {
        return isElementVisible(txtAccountCreatedHeading);
    }

    /**
     * Click on the 'Continue' button to proceed after account creation.
     */
    @Step("Click continue")
    public void clickContinue() {
        click(btnContinue);
    }
}
