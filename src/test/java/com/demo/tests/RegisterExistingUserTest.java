package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.api.UserAPI;
import com.demo.constants.FrameworkConstants;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.models.UserModel;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.pages.PageFactoryManager;
import com.demo.tests.base.BaseTest;
import com.demo.utils.ConfigReader;
import com.demo.utils.LogUtils;
import com.demo.utils.NavigationUtils;
import com.demo.utils.RandomGenerator;

public class RegisterExistingUserTest extends BaseTest {
    private UserModel existingUser;

    @BeforeMethod(description = "Setup: Create a user via API to use for 'Existing Email' test")
    public void setupTestData() {
        existingUser = UserModel.builder()
                .name(RandomGenerator.generateRandomString())
                .email(RandomGenerator.generateRandomEmail())
                .password("Password@123")
                .title("Mr")
                .birth_date("10").birth_month("May").birth_year("1995")
                .firstname(RandomGenerator.generateRandomFirstName())
                .lastname(RandomGenerator.generateRandomLastName())
                .company("E2E Test")
                .address1(RandomGenerator.generateRandomAddress())
                .country("Singapore")
                .state("Singapore")
                .city("Singapore")
                .zipcode("12345")
                .mobile_number(RandomGenerator.generateRandomNumber(10))
                .build();

        LogUtils.info("API Setup: Registering user " + existingUser.getEmail());
        UserAPI.createAccount(existingUser);
    }

    @Test(description = "Test Case 5: Register User with existing email")
    public void testRegisterUserWithExistingEmail() {
        // Init Page
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());

        // 1-2. Launch browser and Navigate to url
        NavigationUtils.openUrl(getDriver(), ConfigReader.getProperty(FrameworkConstants.URL));

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(
                NavigationUtils.getCurrentUrl(getDriver()).contains(ConfigReader.getProperty(FrameworkConstants.URL)));

        // 4. Click on 'Signup / Login' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);

        // 5. Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.isSignupHeadingVisible(), "Heading 'New User Signup!' is not visible!");

        // 6. Enter name and already registered email address
        loginPage.enterSignupForm(existingUser.getName(), existingUser.getEmail());

        // 7. Click 'Signup' button
        loginPage.clickSignup();

        // 8. Verify error 'Email Address already exist!' is visible
        String errorMessage = loginPage.getSignupErrorMessage();
        Assert.assertEquals(errorMessage, "Email Address already exist!", "Error message is not correct!");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupTestData() {
        if (existingUser != null) {
            LogUtils.info("API Cleanup: Deleting user " + existingUser.getEmail());
            UserAPI.deleteAccount(existingUser.getEmail(), existingUser.getPassword());
        }
    }
}
