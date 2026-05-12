package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ContactUsPage extends CommonPage {
    // Locators
    private String lblGetInTouch = "xpath=//h2[contains(text(),'Get In Touch')]";
    private String txtName = "css=input[data-qa='name']";
    private String txtEmail = "css=input[data-qa='email']";
    private String txtSubject = "css=input[data-qa='subject']";
    private String txtMessage = "css=textarea[data-qa='message']";
    private String btnUploadFile = "name=upload_file";
    private String btnSubmit = "css=input[data-qa='submit-button']";
    private String lblSuccessMessage = "css=div.status.alert.alert-success";
    private String btnHome = "css=a.btn.btn-success";

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'GET IN TOUCH' is visible")
    public boolean isGetInTouchVisible() {
        return isElementVisible(lblGetInTouch);
    }

    @Step("Fill Contact Us form: {0}, {1}, {2}, {3}")
    public void fillContactForm(String name, String email, String subject, String message) {
        enterText(txtName, name);
        enterText(txtEmail, email);
        enterText(txtSubject, subject);
        enterText(txtMessage, message);
    }

    @Step("Upload file: {0}")
    public void uploadFile(String filePath) {
        getElement(btnUploadFile).sendKeys(filePath);
    }

    @Step("Click Submit button")
    public void clickSubmit() {
        click(btnSubmit);
    }

    @Step("Verify success message is visible")
    public boolean isSuccessMessageVisible() {
        return isElementVisible(lblSuccessMessage);
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        return getText(lblSuccessMessage);
    }

    @Step("Click Home button")
    public void clickHome() {
        click(btnHome);
    }
}
