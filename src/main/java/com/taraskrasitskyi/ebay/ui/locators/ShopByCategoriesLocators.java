package com.taraskrasitskyi.ebay.ui.locators;

import org.openqa.selenium.By;

public enum ShopByCategoriesLocators implements BaseLocators {
    CATEGORIES_LINK(By.cssSelector("a")),
    SEE_ALL_CATEGORIES(By.xpath("//a[@id='gh-shop-see-all']")),
    CATEGORIES_LIST(By.xpath("//h3[@class='gh-sbc-parent']")),
    SUB_CATEGORIES_LIST(By.cssSelector("h3.gh-sbc-parent + ul")),
    SUB_CATEGORY_ITEMS_LIST(By.cssSelector("li"));

    By path;

    ShopByCategoriesLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

    public String getString() {
        return path.toString().substring(10);
    }
}
