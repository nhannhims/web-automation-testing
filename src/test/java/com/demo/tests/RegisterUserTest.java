package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.demo.api.UserAPI;
import com.demo.constants.FrameworkConstants;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.constants.TestDataConstants;
import com.demo.models.UserModel;
import com.demo.pages.AccountCreatedPage;
import com.demo.pages.AccountDeletedPage;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.pages.PageFactoryManager;
import com.demo.pages.SignupPage;
import com.demo.tests.base.BaseTest;
import com.demo.utils.CSVUtils;
import com.demo.utils.ConfigReader;
import com.demo.utils.LogUtils;
import com.demo.utils.NavigationUtils;
import com.demo.utils.RandomGenerator;
import org.testng.annotations.AfterMethod;

public class RegisterUserTest extends BaseTest {
        private String registeredEmail;
        private String registeredPassword;

        @DataProvider(name = "registrationDataProvider")
        public Object[][] getRegistrationData() {
                return CSVUtils.getDataByTestCase(TestDataConstants.REGISTRATION_DATA_PATH,
                                TestDataConstants.TC_REGISTER_USER);
        }

        @Test(dataProvider = "registrationDataProvider", description = "TC001: Register User")
        public void testRegisterUser(String gender, String password, String day, String month, String year,
                        String country) {
                // Init Page
                HomePage homePage = PageFactoryManager.getHomePage(getDriver());
                LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());
                SignupPage signupPage = PageFactoryManager.getSignupPage(getDriver());
                AccountCreatedPage accountCreatedPage = PageFactoryManager.getAccountCreatedPage(getDriver());
                AccountDeletedPage accountDeletedPage = PageFactoryManager.getAccountDeletedPage(getDriver());

                // 2. Verify that home page is visible successfully
                Assert.assertTrue(
                                NavigationUtils.getCurrentUrl(getDriver())
                                                .contains(ConfigReader.getProperty(FrameworkConstants.URL)));

                // 3. Click on 'Signup / Login' button
                homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);

                // 4. Verify that 'New User Signup!' is visible
                Assert.assertTrue(loginPage.isSignupHeadingVisible(), "Heading 'New User Signup!' is not visible!");

                // 6. Enter name and email address (Randomly generated for uniqueness)
                String userName = RandomGenerator.generateRandomString();
                this.registeredEmail = RandomGenerator.generateRandomEmail();
                this.registeredPassword = password;
                loginPage.enterSignupForm(userName, this.registeredEmail);

                // 7. Click 'Signup' button
                loginPage.clickSignup();

                // 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
                Assert.assertTrue(signupPage.isEnterAccountInformationHeadingVisible(),
                                "Heading 'ENTER ACCOUNT INFORMATION' is not visible!");

                // 9-10. Map CSV data and random data into UserModel for cleaner processing
                UserModel user = UserModel.builder()
                                .title(gender).name(userName).password(password)
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

                // 9-10. Fill details using the UserModel
                signupPage.fillSignupForm(user);

                // 11. Click 'Create Account' button
                signupPage.clickCreateAccount();

                // 12. Verify that 'ACCOUNT CREATED!' is visible
                Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(),
                                "Heading 'ACCOUNT CREATED!' is not visible!");

                // 13. Click 'Continue' button
                accountCreatedPage.clickContinue();

                // 14. Verify that 'Logged in as username' is visible
                Assert.assertTrue(homePage.isLoggedInUserVisible(), "Heading 'Logged in as username' is not visible!");

                // 15. Click 'Delete Account' button
                homePage.clickHeaderNavMenu(HeaderNavigationConstants.DELETE_ACCOUNT_LINK);

                // 16. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
                Assert.assertTrue(accountDeletedPage.isAccountDeletedHeadingVisible(),
                                "Heading 'ACCOUNT DELETED!' is not visible!");
                accountDeletedPage.clickContinue();

                // Reset data after successful deletion via UI
                this.registeredEmail = null;
        }

        @DataProvider(name = "registrationExistingDataProvider")
        public Object[][] getRegistrationExistingData() {
                return CSVUtils.getDataByTestCase(TestDataConstants.REGISTRATION_DATA_PATH,
                                TestDataConstants.TC_REGISTER_EXISTING_USER);
        }

        @Test(dataProvider = "registrationExistingDataProvider", description = "TC005: Register User with existing email")
        public void testRegisterUserWithExistingEmail(String gender, String password, String day, String month,
                        String year,
                        String country) {
                // API Setup - Register a user via API first
                UserModel existingUser = UserModel.builder()
                                .name(RandomGenerator.generateRandomString())
                                .email(RandomGenerator.generateRandomEmail())
                                .password(password).title(gender)
                                .birth_date(day).birth_month(month).birth_year(year)
                                .firstname(RandomGenerator.generateRandomFirstName())
                                .lastname(RandomGenerator.generateRandomLastName())
                                .company(RandomGenerator.generateRandomString() + " Corp")
                                .address1(RandomGenerator.generateRandomAddress())
                                .country(country)
                                .state(RandomGenerator.generateRandomState())
                                .city(RandomGenerator.generateRandomCity())
                                .zipcode(RandomGenerator.generateRandomZipcode())
                                .mobile_number(RandomGenerator.generateRandomNumber(10))
                                .build();

                LogUtils.info("API Setup: Registering user " + existingUser.getEmail());
                UserAPI.createAccount(existingUser);

                // Store info for cleanup in case test fails
                this.registeredEmail = existingUser.getEmail();
                this.registeredPassword = existingUser.getPassword();

                // Init Pages
                HomePage homePage = PageFactoryManager.getHomePage(getDriver());
                LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());

                // 2. Verify that home page is visible successfully
                Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver())
                                .contains(ConfigReader.getProperty(FrameworkConstants.URL)));

                // 3. Navigate to Login/Signup Page
                homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);

                // 4. Verify that 'New User Signup!' is visible
                Assert.assertTrue(loginPage.isSignupHeadingVisible(), "Heading 'New User Signup!' is not visible!");

                // 6-8. Fill signup form with existing email
                loginPage.enterSignupForm(existingUser.getName(), existingUser.getEmail());
                loginPage.clickSignup();

                String errorMessage = loginPage.getSignupErrorMessage();
                Assert.assertEquals(errorMessage, "Email Address already exist!", "Error message is not correct!");
        }

        @AfterMethod(alwaysRun = true)
        public void cleanup() {
                if (registeredEmail != null) {
                        try {
                                LogUtils.info("API Cleanup: Attempting to delete account " + registeredEmail);
                                UserAPI.deleteAccount(registeredEmail, registeredPassword);
                                // We don't assert here because if it's already deleted, it's fine.
                        } catch (Exception e) {
                                LogUtils.warn("API Cleanup failed for " + registeredEmail + ": " + e.getMessage());
                        } finally {
                                // Always reset to prevent interference
                                registeredEmail = null;
                                registeredPassword = null;
                        }
                }
        }
}
