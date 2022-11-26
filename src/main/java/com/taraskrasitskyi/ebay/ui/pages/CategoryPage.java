package com.taraskrasitskyi.ebay.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.taraskrasitskyi.ebay.ui.locators.CategoryPageLocators.*;

public class CategoryPage extends BasePage{

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get title banner caption")
    public String getTitleBannerCaption(){
        return driver.findElement(TITLE_BANNER_CAPTION.getPath()).getText();
    }

    @Step("Get page caption")
    public String  getPageCaption(){
        return driver.findElement(PAGE_CAPTION.getPath()).getText();
    }

    @Step("Get text from last chain navigate link")
    public String getLastChainNavigateLinkText(){
        List<WebElement> navLinkElements = driver.findElements(NAVIGATE_LINK.getPath());
        if (navLinkElements.size() > 0){
            return navLinkElements.get(navLinkElements.size()-1).getText();
        }else{
            return "";
        }
    }
}
