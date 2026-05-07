package com.demo.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    /**
     * Internal method to identify locator type and return a WebElement.
     * @param locator The locator string
     * @return The WebElement found
     */
    protected WebElement getElement(String locator) {
        return driver.findElement(getByLocator(locator));
    }

    /**
     * Wait for an element to be visible on the page.
     * @param locator The locator string
     */
    protected void waitForElementVisible(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    /**
     * Wait for an element to be clickable.
     * @param locator The locator string
     */
    protected void waitForElementClickable(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    /**
     * Click on an element using its locator string.
     * Includes explicit wait for visibility and clickability.
     * @param locator The locator string (e.g., "css=.btn")
     */
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

    /**
     * Enter text into an input field.
     * @param locator The locator string
     * @param text The text to enter
     */
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

    /**
     * Click on an element using JavaScript. Useful for hidden or intercepted elements.
     * @param locator The locator string
     */
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

    /**
     * Scroll the page until the element is in view.
     * @param locator The locator string
     */
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

    /**
     * Select a value from a standard HTML ComboBox (Select tag) by visible text.
     * @param locator The locator string
     * @param value The text to select
     */
    @Step("Select value '{1}' from combobox: {0}")
    protected void selectComboBox(String locator, String value) {
        try {
            LogUtils.info("Selecting value '" + value + "' into combobox: " + locator);
            waitForElementVisible(locator);
            Select select = new Select(getElement(locator));
            select.selectByVisibleText(value);
        } catch (Exception e) {
            LogUtils.error("Failed to select value from combobox: " + locator + ". Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get the visible text of an element.
     * @param locator The locator string
     * @return The text content of the element
     */
    protected String getText(String locator) {
        try {
            waitForElementVisible(locator);
            return getElement(locator).getText();
        } catch (Exception e) {
            LogUtils.error("Failed to get text from element: " + locator + ". Error: " + e.getMessage());
            return "";
        }
    }

    /**
     * Check if an element is currently displayed on the page.
     * @param locator The locator string
     * @return true if visible, false otherwise
     */
    protected boolean isElementVisible(String locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator))).isDisplayed();
        } catch (Exception e) {
            LogUtils.warn("Element is not visible: " + locator);
            return false;
        }
    }

    /**
     * Check if a checkbox or radio button is selected.
     * @param locator The locator string
     * @return true if selected, false otherwise
     */
    protected boolean isElementSelected(String locator) {
        try {
            waitForElementVisible(locator);
            return getElement(locator).isSelected();
        } catch (Exception e) {
            LogUtils.error("Failed to check if element is selected: " + locator + ". Error: " + e.getMessage());
            return false;
        }
    }
}
