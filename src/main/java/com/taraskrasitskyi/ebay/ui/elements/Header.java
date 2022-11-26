package com.taraskrasitskyi.ebay.ui.elements;

import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.taraskrasitskyi.ebay.ui.locators.HeaderLocators.SHOP_BY_CATEGORY_MENU;

public class Header extends BasePage {

    public Header(WebDriver driver) {
        super(driver);
    }

    @Step("Open Shop By Category Menu")
    public ShopByCategoryMenu openShopByCategoryMenu() {
        driver.findElement(SHOP_BY_CATEGORY_MENU.getPath()).click();
        return new ShopByCategoryMenu(driver);
    }
}
