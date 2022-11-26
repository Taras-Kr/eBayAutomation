package com.taraskrasitskyi.ebay.ui.locators;

import org.openqa.selenium.By;

public enum BasePageLocators implements BaseLocators {
    HOME_PAGE_LINK(By.xpath("//a[@href='https://www.ebay.com/']"));

    By path;

    BasePageLocators(By xPath) {
        this.path = xPath;
    }

    @Override
    public By getPath() {
        return path;
    }

}
