package com.demo.tests;

import com.demo.constants.FrameworkConstants;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.constants.TestDataConstants;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.pages.PageFactoryManager;
import com.demo.tests.base.BaseTest;
import com.demo.utils.CSVUtils;
import com.demo.utils.ConfigReader;
import com.demo.utils.NavigationUtils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginDataProvider", parallel = true)
    public Object[][] getLoginData(Method method) {
        if (method.getName().equals("testValidLogin")) {
            return CSVUtils.getDataByTestCase(TestDataConstants.LOGIN_DATA_PATH, TestDataConstants.TC_LOGIN_VALID);
        } else if (method.getName().equals("testInvalidLogin")) {
            return CSVUtils.getDataByTestCase(TestDataConstants.LOGIN_DATA_PATH, TestDataConstants.TC_LOGIN_INVALID);
        }
        return null;
    }

    @Test(dataProvider = "loginDataProvider", description = "TC002: Login User with correct email and password")
    public void testValidLogin(String username, String password, String expectedResult) {
        // Init Pages
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());

        // 1. Navigate to application URL
        NavigationUtils.openUrl(getDriver(), ConfigReader.getProperty(FrameworkConstants.URL));
        
        // 2. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver()).contains(ConfigReader.getProperty(FrameworkConstants.URL)));
        
        // 3. Click on 'Signup / Login' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        
        // 4. Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginHeadingVisible(), "Heading 'Login to your account' is not visible!");
        
        // 5-6. Perform Login actions
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        
        // 7. Verify that 'Logged in successfully' is visible
        Assert.assertTrue(homePage.isLoggedInUserVisible(), "Heading 'Logged in successfully' is not visible!");
    }

    @Test(dataProvider = "loginDataProvider", description = "TC003: Login User with incorrect email and password")
    public void testInvalidLogin(String username, String password, String expectedResult) {
        // Init Pages
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        LoginPage loginPage = PageFactoryManager.getLoginPage(getDriver());

        // 1. Navigate to application URL
        NavigationUtils.openUrl(getDriver(), ConfigReader.getProperty(FrameworkConstants.URL));
        
        // 2. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver()).contains(ConfigReader.getProperty(FrameworkConstants.URL)));
        
        // 3. Click on 'Signup / Login' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.LOGIN_SIGNUP_LINK);
        
        // 4. Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.isLoginHeadingVisible(), "Heading 'Login to your account' is not visible!");
        
        // 5-6. Perform Login actions (failure case)
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        
        // 7. Verify error message
        Assert.assertTrue(loginPage.getLoginErrorMessage().contains(expectedResult),
                "Error message '" + expectedResult + "' is not visible!");
    }
}
