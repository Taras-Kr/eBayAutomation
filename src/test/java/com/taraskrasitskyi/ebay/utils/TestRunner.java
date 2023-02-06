package com.taraskrasitskyi.ebay.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Configuration.*;
import static com.taraskrasitskyi.ebay.utils.PropertiesProvider.*;


public class TestRunner {

    @BeforeClass
    public void setUp() {
        browser = getBrowserProperty();
        browserSize = getBrowserSizeProperty();
        pageLoadTimeout = getPageLoadTimeout();
        timeout = getTimeoutProperty();
    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
