package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class CheckoutPage extends CommonPage {
    // Locators
    private String txtAddressDetailsHeading = "xpath=//h2[text()='Address Details']";
    private String txtReviewOrderHeading = "xpath=//h2[text()='Review Your Order']";
    private String txtCommentArea = "css=textarea[name='message']";
    private String btnPlaceOrder = "css=a[href='/payment']";

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify address details heading is visible")
    public boolean isAddressDetailsVisible() {
        return isElementVisible(txtAddressDetailsHeading);
    }

    @Step("Verify review order heading is visible")
    public boolean isReviewOrderVisible() {
        return isElementVisible(txtReviewOrderHeading);
    }

    @Step("Enter comment in text area: {0}")
    public void enterComment(String comment) {
        enterText(txtCommentArea, comment);
    }

    @Step("Click 'Place Order' button")
    public void clickPlaceOrder() {
        click(btnPlaceOrder);
    }
}
