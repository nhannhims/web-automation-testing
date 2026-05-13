package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.constants.TestDataConstants;
import com.demo.pages.*;
import com.demo.tests.base.BaseTest;

public class SearchVerifyCartAfterLoginTest extends BaseTest {

    @Test(description = "TC020: Search Products and Verify Cart After Login")
    public void testSearchProductsAndVerifyCartAfterLogin() {
        // Init pages
        // homePage
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        // productsPage
        ProductsPage productsPage = PageFactoryManager.getProductsPage(getDriver());
        // cartPage
        CartPage cartPage = PageFactoryManager.getCartPage(getDriver());
        // loginPage
        LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());

        // 1. Launch browser (Handled by BaseTest)
        // 2. Navigate to url 'http://automationexercise.com' (Handled by BaseTest)

        // 3. Click on 'Products' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.PRODUCTS_LINK);

        // 4. Verify user is navigated to ALL PRODUCTS page successfully
        Assert.assertTrue(productsPage.isAllProductsPageVisible(), "ALL PRODUCTS page is not visible!");

        // 5. Enter product name in search input and click search button
        productsPage.searchProduct(TestDataConstants.SEARCH_PRODUCT_NAME);

        // 6. Verify 'SEARCHED PRODUCTS' is visible
        Assert.assertTrue(productsPage.isSearchedProductsHeadingVisible(TestDataConstants.SEARCHED_PRODUCTS_HEADING),
                "'" + TestDataConstants.SEARCHED_PRODUCTS_HEADING + "' heading is not visible!");

        // 7. Verify all the products related to search are visible
        Assert.assertTrue(productsPage.isProductsRelatedToSearchVisible(), "Products related to search are not visible!");

        // 8. Add those products to cart
        productsPage.addSearchedProductsToCart();

        // 9. Click 'Cart' button and verify that products are visible in cart
        productsPage.clickHeaderNavMenu(HeaderNavigationConstants.CART_LINK);
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed!");
        Assert.assertTrue(cartPage.isProductInCart(TestDataConstants.SEARCH_PRODUCT_NAME), 
                "Product '" + TestDataConstants.SEARCH_PRODUCT_NAME + "' is not visible in cart!");

        // 10. Click 'Signup / Login' button and submit login details
        cartPage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        loginPage.login(TestDataConstants.VALID_USER_EMAIL, TestDataConstants.VALID_USER_PASSWORD);

        // 11. Again, go to Cart page
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.CART_LINK);

        // 12. Verify that those products are visible in cart after login as well
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page is not displayed after login!");
        Assert.assertTrue(cartPage.isProductInCart(TestDataConstants.SEARCH_PRODUCT_NAME), 
                "Product '" + TestDataConstants.SEARCH_PRODUCT_NAME + "' is not visible in cart after login!");
    }
}
