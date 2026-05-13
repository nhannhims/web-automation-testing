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

    public static SignupPage getSignupPage(WebDriver driver) {
        return new SignupPage(driver);
    }

    public static AccountCreatedPage getAccountCreatedPage(WebDriver driver) {
        return new AccountCreatedPage(driver);
    }

    public static AccountDeletedPage getAccountDeletedPage(WebDriver driver) {
        return new AccountDeletedPage(driver);
    }

    public static ContactUsPage getContactUsPage(WebDriver driver) {
        return new ContactUsPage(driver);
    }

    public static ProductsPage getProductsPage(WebDriver driver) {
        return new ProductsPage(driver);
    }

    public static ProductDetailPage getProductDetailPage(WebDriver driver) {
        return new ProductDetailPage(driver);
    }

    public static CartPage getCartPage(WebDriver driver) {
        return new CartPage(driver);
    }

    public static CheckoutPage getCheckoutPage(WebDriver driver) {
        return new CheckoutPage(driver);
    }

    public static PaymentPage getPaymentPage(WebDriver driver) {
        return new PaymentPage(driver);
    }

    public static PaymentDonePage getPaymentDonePage(WebDriver driver) {
        return new PaymentDonePage(driver);
    }
}
