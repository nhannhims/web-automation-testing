package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import com.demo.pages.base.BasePage;

public class ProductsPage extends BasePage {

    // Locators
    private final String txtAllProductsHeading = "xpath=//h2[contains(@class, 'title') and text()='All Products']";
    private final String productsList = "class=features_items";
    private final String btnViewProduct = "xpath=(//a[text()='View Product'])[1]";
    private final String txtSearchInput = "id=search_product";
    private final String btnSearch = "id=submit_search";
    private final String txtSearchedProductsHeading = "xpath=//h2[contains(@class, 'title') and text()='%s']";
    private final String listProductItems = "xpath=//div[@class='features_items']//div[@class='col-sm-4']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'ALL PRODUCTS' page is visible")
    public boolean isAllProductsPageVisible() {
        return isElementVisible(txtAllProductsHeading);
    }

    @Step("Verify products list is visible")
    public boolean isProductsListVisible() {
        return isElementVisible(productsList);
    }

    @Step("Click on 'View Product' of first product")
    public void clickViewProductOfFirstProduct() {
        click(btnViewProduct);
    }

    @Step("Enter product name: {0} and click search button")
    public void searchProduct(String productName) {
        enterText(txtSearchInput, productName);
        click(btnSearch);
    }

    @Step("Verify searched products heading: {0} is visible")
    public boolean isSearchedProductsHeadingVisible(String heading) {
        return isElementVisible(setDynamicLocator(txtSearchedProductsHeading, heading));
    }

    @Step("Verify all the products related to search are visible")
    public boolean isProductsRelatedToSearchVisible() {
        return isElementVisible(listProductItems);
    }
}
