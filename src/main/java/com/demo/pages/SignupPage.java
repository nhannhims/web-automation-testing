package com.demo.pages;

import org.openqa.selenium.WebDriver;

import com.demo.models.UserModel;

import io.qameta.allure.Step;

public class SignupPage extends CommonPage {
    // Locators
    private String txtEnterAccountInformationHeading = "xpath=//h2[contains(., 'Enter Account Information')]";
    private String rdGenderTitle = "xpath=//label[contains(., '%s')]//input";
    private String iptName = "css=input[data-qa='name']";
    private String iptPassword = "css=input[data-qa='password']";
    private String cbbDay = "css=select[data-qa='days']";
    private String cbbMonth = "css=select[data-qa='months']";
    private String cbbYear = "css=select[data-qa='years']";
    private String chkNewsLetter = "css=#newsletter";
    private String chkReceiveSpecialOffers = "css=#optin";
    private String txtAddressInfomationHeading = "xpath=//h2[contains(., 'Address Information')]";
    private String iptFirstName = "css=input[data-qa='first_name']";
    private String iptLastName = "css=input[data-qa='last_name']";
    private String iptCompany = "css=input[data-qa='company']";
    private String iptAddress = "css=input[data-qa='address']";
    private String iptAddress2 = "css=input[data-qa='address2']";
    private String cbbCountry = "css=select[data-qa='country']";
    private String iptState = "css=input[data-qa='state']";
    private String iptCity = "css=input[data-qa='city']";
    private String iptZipcode = "css=input[data-qa='zipcode']";
    private String iptMobile = "css=input[data-qa='mobile_number']";
    private String btnCreateAccount = "css=button[data-qa='create-account']";

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public boolean isEnterAccountInformationHeadingVisible() {
        return isElementVisible(txtEnterAccountInformationHeading);
    }

    @Step("Select gender: {0}")
    public void selectGender(String gender) {
        click(setDynamicLocator(rdGenderTitle, gender));
    }

    @Step("Enter name: {0}")
    public void enterName(String name) {
        enterText(iptName, name);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        enterText(iptPassword, password);
    }

    @Step("Select day: {0}")
    public void selectDay(String day) {
        selectComboBox(cbbDay, day);
    }

    @Step("Select month: {0}")
    public void selectMonth(String month) {
        selectComboBox(cbbMonth, month);
    }

    @Step("Select year: {0}")
    public void selectYear(String year) {
        selectComboBox(cbbYear, year);
    }

    @Step("Check news letter")
    public void checkNewsLetter() {
        if (!isElementSelected(chkNewsLetter)) {
            click(chkNewsLetter);
        }
    }

    @Step("Check receive special offers")
    public void checkReceiveSpecialOffers() {
        if (!isElementSelected(chkReceiveSpecialOffers)) {
            click(chkReceiveSpecialOffers);
        }
    }

    /**
     * Fill the initial account information form.
     * @param gender "Mr" or "Mrs"
     * @param userName Name to display
     * @param password Account password
     * @param day Birth day
     * @param month Birth month
     * @param year Birth year
     */
    @Step("Fill account information form")
    public void fillAccountInformationForm(String gender, String userName, String password, String day, String month,
            String year) {
        selectGender(gender);
        enterName(userName);
        enterPassword(password);
        selectDay(day);
        selectMonth(month);
        selectYear(year);
        checkNewsLetter();
        checkReceiveSpecialOffers();
    }

    /**
     * Fill the secondary address information form.
     */
    @Step("Fill address information form")
    public void fillAddressInformationForm(String firstName, String lastName, String company, String address1,
            String address2, String country, String state, String city, String zipcode, String mobile) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress(address1);
        enterAddress2(address2);
        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZipcode(zipcode);
        enterMobile(mobile);
    }

    /**
     * Helper method to fill the entire signup registration form using a UserModel.
     * @param user UserModel object containing all registration data
     */
    @Step("Fill signup form with user data")
    public void fillSignupForm(UserModel user) {
        fillAccountInformationForm(user.getTitle(), user.getName(), user.getPassword(), user.getBirth_date(),
                user.getBirth_month(), user.getBirth_year());
        fillAddressInformationForm(user.getFirstname(), user.getLastname(), user.getCompany(), user.getAddress1(),
                user.getAddress2(), user.getCountry(), user.getState(), user.getCity(), user.getZipcode(),
                user.getMobile_number());
    }

    public boolean isAddressInfomationHeadingVisible() {
        return isElementVisible(txtAddressInfomationHeading);
    }

    @Step("Enter first name: {0}")
    public void enterFirstName(String firstName) {
        enterText(iptFirstName, firstName);
    }

    @Step("Enter last name: {0}")
    public void enterLastName(String lastName) {
        enterText(iptLastName, lastName);
    }

    @Step("Enter company: {0}")
    public void enterCompany(String company) {
        enterText(iptCompany, company);
    }

    @Step("Enter address: {0}")
    public void enterAddress(String address) {
        enterText(iptAddress, address);
    }

    @Step("Enter address 2: {0}")
    public void enterAddress2(String address2) {
        enterText(iptAddress2, address2);
    }

    @Step("Select country: {0}")
    public void selectCountry(String country) {
        selectComboBox(cbbCountry, country);
    }

    @Step("Enter state: {0}")
    public void enterState(String state) {
        enterText(iptState, state);
    }

    @Step("Enter city: {0}")
    public void enterCity(String city) {
        enterText(iptCity, city);
    }

    @Step("Enter zipcode: {0}")
    public void enterZipcode(String zipcode) {
        enterText(iptZipcode, zipcode);
    }

    @Step("Enter mobile: {0}")
    public void enterMobile(String mobile) {
        enterText(iptMobile, mobile);
    }

    @Step("Click create account")
    public void clickCreateAccount() {
        click(btnCreateAccount);
    }
}
