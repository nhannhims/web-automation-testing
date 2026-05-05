package com.demo.pages;

import org.openqa.selenium.WebDriver;

import com.demo.pages.base.BasePage;

public class CommonPage extends BasePage {
    // Locators
    String headerNavMenu = "css=a[href='%s']";

    // Constructor
    public CommonPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void clickHeaderNavMenu(String menuName) {
        click(setDynamicLocator(headerNavMenu, menuName));
    }

}
