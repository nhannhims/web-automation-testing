package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.demo.api.UserAPI;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.constants.TestDataConstants;
import com.demo.models.UserModel;
import com.demo.pages.*;
import com.demo.tests.base.BaseTest;
import com.demo.utils.CSVUtils;
import com.demo.utils.LogUtils;
import com.demo.utils.RandomGenerator;
import org.testng.annotations.AfterMethod;

public class PlaceOrderTest extends BaseTest {
    private UserModel testUser;

    @DataProvider(name = "placeOrderDataProvider")
    public Object[][] getPlaceOrderData() {
        return CSVUtils.getDataByTestCase(TestDataConstants.PLACE_ORDER_DATA_PATH, TestDataConstants.TC_PLACE_ORDER);
    }

    @Test(dataProvider = "placeOrderDataProvider", description = "TC015: Place Order: Register before Checkout")
    public void testPlaceOrderRegisterBeforeCheckout(String gender, String password, String day, String month, String year, String country) {
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());
        ProductsPage productsPage = PageFactoryManager.getProductsPage(getDriver());
        CartPage cartPage = PageFactoryManager.getCartPage(getDriver());
        CheckoutPage checkoutPage = PageFactoryManager.getCheckoutPage(getDriver());
        PaymentPage paymentPage = PageFactoryManager.getPaymentPage(getDriver());
        PaymentDonePage paymentDonePage = PageFactoryManager.getPaymentDonePage(getDriver());

        // --- STEP 1: API Setup - Create Account ---
        String name = RandomGenerator.generateRandomFirstName();
        String email = RandomGenerator.generateRandomEmail();
        
        testUser = UserModel.builder()
                .title(gender).name(name).email(email).password(password)
                .birth_date(day).birth_month(month).birth_year(year)
                .firstname(RandomGenerator.generateRandomFirstName())
                .lastname(RandomGenerator.generateRandomLastName())
                .company(RandomGenerator.generateRandomString() + " Corp")
                .address1(RandomGenerator.generateRandomAddress())
                .address2("Apt " + RandomGenerator.generateRandomNumber(3))
                .country(country)
                .state(RandomGenerator.generateRandomState())
                .city(RandomGenerator.generateRandomCity())
                .zipcode(RandomGenerator.generateRandomZipcode())
                .mobile_number(RandomGenerator.generateRandomNumber(10))
                .build();
        
        LogUtils.info("API Setup: Registering user " + email);
        UserAPI.createAccount(testUser);

        // --- STEP 2: UI Actions ---
        // 3. Verify that home page is visible successfully
        Assert.assertTrue(homePage.isLoggedInUserVisible() == false, "Home page should be visible, but user should not be logged in yet");

        // 4. Click 'Signup / Login' button & Login
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        loginPage.login(email, password);

        // 7. Verify ' Logged in as username' at top
        Assert.assertTrue(homePage.isLoggedInUserVisible(), "Logged in as username should be visible");

        // 8. Add products to cart
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.PRODUCTS_LINK);
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        // 9. Click 'Cart' button (Handled by clickViewCart in previous step, but let's be explicit if needed)
        // 10. Verify that cart page is displayed
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");

        // 11. Click Proceed To Checkout
        cartPage.clickProceedToCheckout();

        // 12. Verify Address Details and Review Your Order
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address Details should be visible");
        Assert.assertTrue(checkoutPage.isReviewOrderVisible(), "Review Your Order should be visible");

        // 13. Enter description in comment text area and click 'Place Order'
        checkoutPage.enterComment(TestDataConstants.ORDER_COMMENT);
        checkoutPage.clickPlaceOrder();

        // 14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.enterPaymentDetails(
                TestDataConstants.CARD_NAME,
                TestDataConstants.CARD_NUMBER,
                TestDataConstants.CARD_CVC,
                TestDataConstants.CARD_EXP_MONTH,
                TestDataConstants.CARD_EXP_YEAR
        );

        // 15. Click 'Pay and Confirm Order' button
        paymentPage.clickPayAndConfirmOrder();

        // 16. Verify success message 'Your order has been placed successfully!'
        Assert.assertTrue(paymentDonePage.isSuccessMessageVisible(), "Order success message should be visible");

        // Step 17 & 18 (Delete Account) are handled by API in @AfterMethod
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (testUser != null) {
            LogUtils.info("API Cleanup: Deleting user " + testUser.getEmail());
            UserAPI.deleteAccount(testUser.getEmail(), testUser.getPassword());
            testUser = null;
        }
    }
}
