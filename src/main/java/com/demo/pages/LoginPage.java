package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends CommonPage {
    // Locators
    private String txtLoginHeading = "xpath=//h2[contains(text(), 'Login to your account')]";
    private String iptUsername = "css=input[data-qa='login-email']";
    private String iptPassword = "css=input[data-qa='login-password']";
    private String btnLogin = "css=button[data-qa='login-button']";
    private String txtErrorMessage = "css=.login-form p";

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    @Step("Enter username: {0}")
    public void enterUsername(String username) {
        enterText(iptUsername, username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        enterText(iptPassword, password);
    }

    @Step("Click Login button")
    public void clickLogin() {
        click(btnLogin);
    }

    public boolean isLoginHeadingVisible() {
        return isElementVisible(txtLoginHeading);
    }

    public String getErrorMessage() {
        return getText(txtErrorMessage);
    }
}
