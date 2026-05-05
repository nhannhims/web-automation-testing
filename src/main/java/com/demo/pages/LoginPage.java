package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends CommonPage {
    // Locators
    private String txtLoginHeading = "xpath=//h2[contains(text(), 'Login to your account')]";
    private String iptUsername = "css=input[data-qa='login-email']  ";
    private String iptPassword = "css=input[data-qa='login-password']";
    private String btnLogin = "css=button[data-qa='login-button']";
    private String txtErrorMessage = "css=.login-form p";

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void enterUsername(String username) {
        enterText(iptUsername, username);
    }

    public void enterPassword(String password) {
        enterText(iptPassword, password);
    }

    public void clickLogin() {
        click(btnLogin);
    }

    @Step("Login with username: {0} and password: {1}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginHeadingVisible() {
        return isElementVisible(txtLoginHeading);
    }

    public String getErrorMessage() {
        return getText(txtErrorMessage);
    }
}
