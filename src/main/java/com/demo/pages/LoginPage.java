package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends CommonPage {
    // Locators
    private String txtLoginHeading = "xpath=//h2[contains(text(), 'Login to your account')]";
    private String iptUsername = "css=input[data-qa='login-email']";
    private String iptPassword = "css=input[data-qa='login-password']";
    private String btnLogin = "css=button[data-qa='login-button']";
    private String txtSignupHeading = "xpath=//h2[contains(text(), 'New User Signup!')]";
    private String iptSignupName = "css=input[data-qa='signup-name']";
    private String iptSignupEmail = "css=input[data-qa='signup-email']";
    private String btnSignup = "css=button[data-qa='signup-button']";
    private String txtLoginErrorMessage = "css=.login-form p";
    private String txtSignupErrorMessage = "xpath=//p[contains(text(), 'Email Address already exist!')]";

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    @Step("Enter signup name: {0} and email: {1}")
    public void enterSignupForm(String name, String email) {
        enterSignUpName(name);
        enterSignUpEmail(email);
    }

    @Step("Enter signup name: {0}")
    public void enterSignUpName(String name) {
        enterText(iptSignupName, name);
    }

    @Step("Enter signup email: {0}")
    public void enterSignUpEmail(String email) {
        enterText(iptSignupEmail, email);
    }

    @Step("Click Signup button")
    public void clickSignup() {
        click(btnSignup);
    }

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

    @Step("Check if login heading is visible")
    public boolean isLoginHeadingVisible() {
        return isElementVisible(txtLoginHeading);
    }

    @Step("Check if signup heading is visible")
    public boolean isSignupHeadingVisible() {
        return isElementVisible(txtSignupHeading);
    }

    @Step("Get login error message")
    public String getLoginErrorMessage() {
        return getText(txtLoginErrorMessage);
    }

    @Step("Get signup error message")
    public String getSignupErrorMessage() {
        return getText(txtSignupErrorMessage);
    }
}
