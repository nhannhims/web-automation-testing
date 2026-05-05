package com.demo.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.constants.TimeConstants;
import com.demo.utils.LogUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TimeConstants.EXPLICIT_WAIT);
    }

    // Replace one or more format strings (e.g. %s) with dynamic values
    protected String setDynamicLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    // Get By object from locator string
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

    // Auto-identify xpath, css, id, etc. and get Element
    protected WebElement getElement(String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected void waitForElementVisible(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementClickable(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    @Step("Click on element: {0}")
    protected void click(String locator) {
        try {
            LogUtils.info("Clicking on element: " + locator);
            waitForElementVisible(locator);
            waitForElementClickable(locator);
            getElement(locator).click();
        } catch (Exception e) {
            LogUtils.error("Failed to click on element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Enter text '{1}' into element: {0}")
    protected void enterText(String locator, String text) {
        try {
            LogUtils.info("Entering text '" + text + "' into element: " + locator);
            waitForElementVisible(locator);
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            LogUtils.error("Failed to enter text into element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Click on element using JS: {0}")
    protected void jsClick(String locator) {
        try {
            LogUtils.info("Clicking on element using JavaScript: " + locator);
            waitForElementVisible(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", getElement(locator));
        } catch (Exception e) {
            LogUtils.error("Failed to JS click on element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Scroll to element: {0}")
    protected void scrollToElement(String locator) {
        try {
            LogUtils.info("Scrolling to element: " + locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
        } catch (Exception e) {
            LogUtils.error("Failed to scroll to element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Hover over element: {0}")
    protected void hoverOverElement(String locator) {
        try {
            LogUtils.info("Hovering over element: " + locator);
            waitForElementVisible(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(getElement(locator)).perform();
        } catch (Exception e) {
            LogUtils.error("Failed to hover over element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Double click on element: {0}")
    protected void doubleClick(String locator) {
        try {
            LogUtils.info("Double clicking on element: " + locator);
            waitForElementVisible(locator);
            Actions actions = new Actions(driver);
            actions.doubleClick(getElement(locator)).perform();
        } catch (Exception e) {
            LogUtils.error("Failed to double click on element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @Step("Right click on element: {0}")
    protected void rightClick(String locator) {
        try {
            LogUtils.info("Right clicking on element: " + locator);
            waitForElementVisible(locator);
            Actions actions = new Actions(driver);
            actions.contextClick(getElement(locator)).perform();
        } catch (Exception e) {
            LogUtils.error("Failed to right click on element: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    protected String getText(String locator) {
        try {
            waitForElementVisible(locator);
            return getElement(locator).getText();
        } catch (Exception e) {
            LogUtils.error("Failed to get text from element: " + locator + ". Error: " + e.getMessage());
            return "";
        }
    }

    protected boolean isElementVisible(String locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator))).isDisplayed();
        } catch (Exception e) {
            LogUtils.warn("Element is not visible: " + locator);
            return false;
        }
    }
}
