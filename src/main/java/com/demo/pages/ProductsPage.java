package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import com.demo.pages.base.BasePage;

public class ProductsPage extends BasePage {

    // Locators
    private final String allProductsTitle = "xpath=//h2[contains(@class, 'title') and text()='All Products']";
    private final String productsList = "class=features_items";
    private final String viewProductButton = "xpath=(//a[text()='View Product'])[1]";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ALL PRODUCTS' page is visible")
    public boolean isAllProductsPageVisible() {
        return isElementVisible(allProductsTitle);
    }

    @Step("Verify products list is visible")
    public boolean isProductsListVisible() {
        return isElementVisible(productsList);
    }

    @Step("Click on 'View Product' of first product")
    public void clickViewProductOfFirstProduct() {
        click(viewProductButton);
    }
}
