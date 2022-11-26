package com.taraskrasitsky.ebay.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


public class TestRunner {
    protected static WebDriver driver;
    private PropertiesProvider properties = new PropertiesProvider();
    protected SoftAssert softAssert;

    @BeforeClass
    public void getDriver() {

        switch (properties.getBrowser()) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", properties.getChromeBrowser());
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", properties.getFireFoxBrowser());
                driver = new FirefoxDriver();

                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid browser");
            }
        }
        driver.manage().window().maximize();
        driver.get(properties.getBaseUrl());

    }

    @AfterClass
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

}
