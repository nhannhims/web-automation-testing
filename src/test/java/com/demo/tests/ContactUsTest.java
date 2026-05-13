package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.demo.constants.FrameworkConstants;
import com.demo.constants.HeaderNavigationConstants;
import com.demo.constants.TestDataConstants;
import com.demo.pages.ContactUsPage;
import com.demo.pages.HomePage;
import com.demo.pages.PageFactoryManager;
import com.demo.tests.base.BaseTest;
import com.demo.utils.CSVUtils;
import com.demo.utils.ConfigReader;
import com.demo.utils.NavigationUtils;

public class ContactUsTest extends BaseTest {

    @DataProvider(name = "contactUsDataProvider")
    public Object[][] getContactUsData() {
        return CSVUtils.getDataByTestCase(TestDataConstants.CONTACT_US_DATA_PATH,
                TestDataConstants.TC_CONTACT_US);
    }

    @Test(dataProvider = "contactUsDataProvider", description = "TC006: Contact Us Form")
    public void testContactUsForm(String name, String email, String subject, String message) {
        // Init Pages
        HomePage homePage = PageFactoryManager.getHomePage(getDriver());
        ContactUsPage contactUsPage = PageFactoryManager.getContactUsPage(getDriver());

        // 3. Verify that home page is visible successfully
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver())
                .contains(ConfigReader.getProperty(FrameworkConstants.URL)), "Home page is not visible!");

        // 4. Click on 'Contact Us' button
        homePage.clickHeaderNavMenu(HeaderNavigationConstants.CONTACT_LINK);

        // 5. Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "'GET IN TOUCH' is not visible!");

        // 6. Enter name, email, subject and message
        contactUsPage.fillContactForm(name, email, subject, message);

        // 7. Upload file
        contactUsPage.uploadFile(TestDataConstants.UPLOAD_FILE_PATH);

        // 8. Click 'Submit' button
        contactUsPage.clickSubmit();

        // 9. Click OK button (Handle browser alert)
        contactUsPage.acceptAlert();

        // 10. Verify success message 'Success! Your details have been submitted
        // successfully.' is visible
        String successMsg = contactUsPage.getSuccessMessageText();
        Assert.assertEquals(successMsg, TestDataConstants.CONTACT_US_SUCCESS_MSG, "Success message mismatch!");

        // 11. Click 'Home' button and verify that landed to home page successfully
        contactUsPage.clickHome();
        Assert.assertTrue(NavigationUtils.getCurrentUrl(getDriver())
                .equals(ConfigReader.getProperty(FrameworkConstants.URL) + "/"),
                "Did not land to home page successfully!");
    }
}
