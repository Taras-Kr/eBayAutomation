package com.taraskrasitskyi.ebay.ui.locators;


import org.openqa.selenium.By;

public enum TopMenuLocators implements BaseLocators {
    TOP_MENU_ELECTRONICS(By.xpath("//li[@class='hl-cat-nav__js-tab']//a[text()='Electronics']")),
    TOP_MENU_MOST_POPULAR_CATEGORIES(By.xpath("/..//nav[@aria-label='Most popular categories']//li//a"));

    private final By path;

    TopMenuLocators(By path) {
        this.path = path;
    }

    @Override
    public By getPath() {
        return path;
    }

    @Override
    public String toString(){
        return path.toString().substring(10);
    }
}
