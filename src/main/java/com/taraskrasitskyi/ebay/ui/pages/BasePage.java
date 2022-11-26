package com.taraskrasitskyi.ebay.ui.pages;

import com.taraskrasitskyi.ebay.ui.elements.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.taraskrasitskyi.ebay.ui.locators.BasePageLocators.*;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        driver.findElement(HOME_PAGE_LINK.getPath()).click();
        return new HomePage(driver);
    }

    public boolean isElementVisible(By locator, long seconds) {
        try {
            WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            waitElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Header getHeader() {
        return new Header(driver);
    }
}
