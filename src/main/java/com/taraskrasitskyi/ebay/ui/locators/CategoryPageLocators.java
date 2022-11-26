package com.taraskrasitskyi.ebay.ui.locators;

import org.openqa.selenium.By;

public enum CategoryPageLocators implements BaseLocators {
    TITLE_BANNER_CAPTION(By.xpath("//h1[@class='title-banner__title']")),
    NAVIGATE_LINK(By.xpath("//a[@class='seo-breadcrumb-text']/span")),
    PAGE_CAPTION(By.xpath("//h1"));

    private final By path;

    CategoryPageLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }
}
