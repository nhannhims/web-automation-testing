package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ContactUsPage extends CommonPage {
    // Locators
    private String txtGetInTouchHeading = "xpath=//h2[contains(text(),'Get In Touch')]";
    private String iptName = "css=input[data-qa='name']";
    private String iptEmail = "css=input[data-qa='email']";
    private String iptSubject = "css=input[data-qa='subject']";
    private String iptMessage = "css=textarea[data-qa='message']";
    private String iptUploadFile = "name=upload_file";
    private String btnSubmit = "css=input[data-qa='submit-button']";
    private String txtSuccessMessage = "css=div.status.alert.alert-success";
    private String btnHome = "css=a.btn.btn-success";

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'GET IN TOUCH' is visible")
    public boolean isGetInTouchVisible() {
        return isElementVisible(txtGetInTouchHeading);
    }

    @Step("Fill Contact Us form: {0}, {1}, {2}, {3}")
    public void fillContactForm(String name, String email, String subject, String message) {
        enterText(iptName, name);
        enterText(iptEmail, email);
        enterText(iptSubject, subject);
        enterText(iptMessage, message);
    }

    @Step("Upload file: {0}")
    public void uploadFile(String filePath) {
        getElement(iptUploadFile).sendKeys(filePath);
    }

    @Step("Click Submit button")
    public void clickSubmit() {
        click(btnSubmit);
    }

    @Step("Verify success message is visible")
    public boolean isSuccessMessageVisible() {
        return isElementVisible(txtSuccessMessage);
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        return getText(txtSuccessMessage);
    }

    @Step("Click Home button")
    public void clickHome() {
        click(btnHome);
    }
}
