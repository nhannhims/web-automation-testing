package com.demo.tests;

import com.demo.constants.HeaderNavigationConstants;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.tests.base.BaseTest;
import com.demo.utils.CSVUtils;
import com.demo.utils.ConfigReader;
import com.demo.utils.NavigationUtils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginDataProvider")
    public Object[][] getLoginData(Method method) {
        String filePath = "src/test/resources/data/login_data.csv";
        if (method.getName().equals("testValidLogin")) {
            return CSVUtils.getDataByTestCase(filePath, "TC002");
        } else if (method.getName().equals("testInvalidLogin")) {
            return CSVUtils.getDataByTestCase(filePath, "TC003");
        }
        return null;
    }

    @Test(dataProvider = "loginDataProvider", description = "TC002: Login User with correct email and password")
    public void testValidLogin(String username, String password, String expectedResult) {
        // Init Page
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        // 1. Navigate to application URL
        NavigationUtils.openUrl(driver, ConfigReader.getProperty("url"));
        
        // 2. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(driver).contains(ConfigReader.getProperty("url")));
        
        // 3. Click on 'Signup / Login' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        
        // 4. Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginHeadingVisible(), "Heading 'Login to your account' is not visible!");
        
        // 5. Enter correct email address and password from CSV
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        
        // 6. Click 'login' button
        loginPage.clickLogin();
        
        // 7. Verify that 'Logged in successfully' is visible
        Assert.assertTrue(homePage.isLoginHeadingVisible(), "Heading 'Logged in successfully' is not visible!");
    }

    @Test(dataProvider = "loginDataProvider", description = "TC003: Login User with incorrect email and password")
    public void testInvalidLogin(String username, String password, String expectedResult) {
        // Init Page
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        // 1. Navigate to application URL
        NavigationUtils.openUrl(driver, ConfigReader.getProperty("url"));
        
        // 2. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(driver).contains(ConfigReader.getProperty("url")));
        
        // 3. Click on 'Signup / Login' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        
        // 4. Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginHeadingVisible(), "Heading 'Login to your account' is not visible!");
        
        // 5. Enter incorrect email address and password from CSV
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        
        // 6. Click 'login' button
        loginPage.clickLogin();
        
        // 7. Verify error message from CSV is visible
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedResult),
                "Error message '" + expectedResult + "' is not visible!");
    }
}
