package com.demo.pages;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class AccountDeletedPage extends CommonPage {
    // Locators
    private String txtAccountDeletedHeading = "xpath=//h2[contains(., 'Account Deleted!')]";
    private String btnContinue = "css=a[data-qa='continue-button']";

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    /**
     * Verify that the 'ACCOUNT DELETED!' success message is visible.
     * @return true if visible, false otherwise
     */
    @Step("Check if account deleted heading is visible")
    public boolean isAccountDeletedHeadingVisible() {
        return isElementVisible(txtAccountDeletedHeading);
    }

    /**
     * Click on the 'Continue' button to proceed after account deletion.
     */
    @Step("Click continue")
    public void clickContinue() {
        click(btnContinue);
    }
}
