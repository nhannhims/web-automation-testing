package com.demo.pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

import com.demo.pages.base.BasePage;

public class CommonPage extends BasePage {
    // Locators
    String btnHeaderNavMenu = "css=a[href='%s']";

    // Constructor
    public CommonPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Click on a menu item in the header navigation bar.
     * @param menuPath The path/href of the menu item (use HeaderNavigationConstants)
     */
    @Step("Click on header nav menu: {0}")
    public void clickHeaderNavMenu(String menuPath) {
        click(setDynamicLocator(btnHeaderNavMenu, menuPath));
    }

}
