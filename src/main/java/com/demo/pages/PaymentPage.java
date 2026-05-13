package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class PaymentPage extends CommonPage {
    // Locators
    private String iptNameOnCard = "css=input[data-qa='name-on-card']";
    private String iptCardNumber = "css=input[data-qa='card-number']";
    private String iptCvc = "css=input[data-qa='cvc']";
    private String iptExpiryMonth = "css=input[data-qa='expiry-month']";
    private String iptExpiryYear = "css=input[data-qa='expiry-year']";
    private String btnPayAndConfirmOrder = "css=button[data-qa='pay-button']";

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter payment details")
    public void enterPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        enterText(iptNameOnCard, name);
        enterText(iptCardNumber, cardNumber);
        enterText(iptCvc, cvc);
        enterText(iptExpiryMonth, month);
        enterText(iptExpiryYear, year);
    }

    @Step("Click 'Pay and Confirm Order' button")
    public void clickPayAndConfirmOrder() {
        click(btnPayAndConfirmOrder);
    }
}
