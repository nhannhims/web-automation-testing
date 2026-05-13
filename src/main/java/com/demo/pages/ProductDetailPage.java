package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import com.demo.pages.base.BasePage;

public class ProductDetailPage extends BasePage {

    // Locators
    private final String txtProductName = "xpath=//div[@class='product-information']/h2";
    private final String txtCategory = "xpath=//div[@class='product-information']/p[contains(text(), 'Category:')]";
    private final String txtPrice = "xpath=//div[@class='product-information']/span/span";
    private final String txtAvailability = "xpath=//p[b[contains(text(), 'Availability:')]]";
    private final String txtCondition = "xpath=//p[b[contains(text(), 'Condition:')]]";
    private final String txtBrand = "xpath=//p[b[contains(text(), 'Brand:')]]";

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify product name is visible")
    public boolean isProductNameVisible() {
        return isElementVisible(txtProductName);
    }

    @Step("Verify category is visible")
    public boolean isCategoryVisible() {
        return isElementVisible(txtCategory);
    }

    @Step("Verify price is visible")
    public boolean isPriceVisible() {
        return isElementVisible(txtPrice);
    }

    @Step("Verify availability is visible")
    public boolean isAvailabilityVisible() {
        return isElementVisible(txtAvailability);
    }

    @Step("Verify condition is visible")
    public boolean isConditionVisible() {
        return isElementVisible(txtCondition);
    }

    @Step("Verify brand is visible")
    public boolean isBrandVisible() {
        return isElementVisible(txtBrand);
    }
    
    @Step("Get product name")
    public String getProductName() {
        return getText(txtProductName);
    }
}
