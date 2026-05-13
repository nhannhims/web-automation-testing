package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class PaymentDonePage extends CommonPage {
    // Locators
    private String txtSuccessMsg = "css=[data-qa='order-placed']";
    private String btnContinue = "css=a[data-qa='continue-button']";

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify success message is visible")
    public boolean isSuccessMessageVisible() {
        return isElementVisible(txtSuccessMsg);
    }

    @Step("Click 'Continue' button")
    public void clickContinue() {
        click(btnContinue);
    }
}
