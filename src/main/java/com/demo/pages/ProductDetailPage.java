package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import com.demo.pages.base.BasePage;

public class ProductDetailPage extends BasePage {

    // Locators
    private final String productName = "xpath=//div[@class='product-information']/h2";
    private final String category = "xpath=//div[@class='product-information']/p[contains(text(), 'Category:')]";
    private final String price = "xpath=//div[@class='product-information']/span/span";
    private final String availability = "xpath=//p[b[contains(text(), 'Availability:')]]";
    private final String condition = "xpath=//p[b[contains(text(), 'Condition:')]]";
    private final String brand = "xpath=//p[b[contains(text(), 'Brand:')]]";

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify product name is visible")
    public boolean isProductNameVisible() {
        return isElementVisible(productName);
    }

    @Step("Verify category is visible")
    public boolean isCategoryVisible() {
        return isElementVisible(category);
    }

    @Step("Verify price is visible")
    public boolean isPriceVisible() {
        return isElementVisible(price);
    }

    @Step("Verify availability is visible")
    public boolean isAvailabilityVisible() {
        return isElementVisible(availability);
    }

    @Step("Verify condition is visible")
    public boolean isConditionVisible() {
        return isElementVisible(condition);
    }

    @Step("Verify brand is visible")
    public boolean isBrandVisible() {
        return isElementVisible(brand);
    }
    
    @Step("Get product name")
    public String getProductName() {
        return getText(productName);
    }
}
