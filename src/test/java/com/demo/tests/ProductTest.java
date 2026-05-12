package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demo.constants.FrameworkConstants;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.pages.HomePage;
import com.demo.pages.ProductsPage;
import com.demo.pages.ProductDetailPage;
import com.demo.pages.PageFactoryManager;
import com.demo.tests.base.BaseTest;
import com.demo.utils.ConfigReader;
import com.demo.utils.NavigationUtils;

public class ProductTest extends BaseTest {

    @Test(description = "TC008: Verify All Products and product detail page")
    public void testVerifyAllProductsAndProductDetail() {
        // Init Pages
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        ProductsPage productsPage = PageFactoryManager.getProductsPage(getDriver());
        ProductDetailPage productDetailPage = PageFactoryManager.getProductDetailPage(getDriver());

        // 1. Launch browser & 2. Navigate to url 'http://automationexercise.com'
        NavigationUtils.openUrl(getDriver(), ConfigReader.getProperty(FrameworkConstants.URL));

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver())
                .contains(ConfigReader.getProperty(FrameworkConstants.URL)), "Home page is not visible!");

        // 4. Click on 'Products' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.PRODUCTS_LINK);

        // 5. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "ALL PRODUCTS page is not visible!");

        // 6. The products list is visible
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible!");

        // 7. Click on 'View Product' of first product
        productsPage.clickViewProductOfFirstProduct();

        // 8. User is landed to product detail page
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver())
                .contains(HeaderNavigationConstants.PRODUCT_DETAILS_LINK), "Did not land to product detail page!");

        // 9. Verify that detail detail is visible: product name, category, price,
        // availability, condition, brand
        Assert.assertTrue(productDetailPage.isProductNameVisible(), "Product Name is not visible!");
        Assert.assertTrue(productDetailPage.isCategoryVisible(), "Category is not visible!");
        Assert.assertTrue(productDetailPage.isPriceVisible(), "Price is not visible!");
        Assert.assertTrue(productDetailPage.isAvailabilityVisible(), "Availability is not visible!");
        Assert.assertTrue(productDetailPage.isConditionVisible(), "Condition is not visible!");
        Assert.assertTrue(productDetailPage.isBrandVisible(), "Brand is not visible!");
    }
}
