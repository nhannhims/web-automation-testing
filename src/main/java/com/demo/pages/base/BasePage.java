package com.demo.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.constants.TimeConstants;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TimeConstants.EXPLICIT_WAIT);
    }

    // Thay thế một hoặc nhiều chuỗi định dạng (ví dụ: %s) bằng giá trị động
    protected String setDynamicLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    // Lấy đối tượng By từ chuỗi locator
    private By getByLocator(String locator) {
        By by;
        if (locator.startsWith("id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("class=")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("name=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("css=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("xpath=") || locator.startsWith("//") || locator.startsWith("(//")
                || locator.startsWith("./")) {
            by = By.xpath(locator.replace("xpath=", ""));
        } else {
            by = By.cssSelector(locator);
        }
        return by;
    }

    // Tự động nhận diện xpath, css, id, ... và lấy Element
    protected WebElement getElement(String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected void waitForElementVisible(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    @Step("Click on element: {0}")
    protected void click(String locator) {
        waitForElementVisible(locator);
        getElement(locator).click();
    }

    @Step("Enter text '{1}' into element: {0}")
    protected void enterText(String locator, String text) {
        waitForElementVisible(locator);
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(String locator) {
        waitForElementVisible(locator);
        return getElement(locator).getText();
    }

    protected boolean isElementVisible(String locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
