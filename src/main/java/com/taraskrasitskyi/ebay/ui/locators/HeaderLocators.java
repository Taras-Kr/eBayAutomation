package com.taraskrasitskyi.ebay.ui.locators;

import org.openqa.selenium.By;

public enum HeaderLocators implements BaseLocators{
    SHOP_BY_CATEGORY_MENU(By.xpath("//button[@id = 'gh-shop-a']"));

    By path;

    HeaderLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
