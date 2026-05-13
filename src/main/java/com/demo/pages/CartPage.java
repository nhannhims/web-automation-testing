package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class CartPage extends CommonPage {
    // Locators
    private String btnProceedToCheckout = "css=.check_out";
    private String txtCartPageHeading = "xpath=//li[@class='active' and text()='Shopping Cart']";
    private String txtProductName = "xpath=//td[@class='cart_description']//a[text()='%s']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify that cart page is displayed")
    public boolean isCartPageDisplayed() {
        return isElementVisible(txtCartPageHeading);
    }

    @Step("Verify product '{0}' is visible in cart")
    public boolean isProductInCart(String productName) {
        return isElementVisible(setDynamicLocator(txtProductName, productName));
    }

    @Step("Click 'Proceed To Checkout' button")
    public void clickProceedToCheckout() {
        click(btnProceedToCheckout);
    }
}
