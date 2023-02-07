package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.Selenide;
import com.taraskrasitskyi.ebay.ui.elements.menus.topmenu.TopMenu;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    @Step("HomePage: Open eBay home page")
    public HomePage open() {
        Selenide.open("https://www.ebay.com/");
        return this;
    }

    @Step("HomePage: Get top menu")
    public TopMenu getTopMenu() {
        return new TopMenu();
    }

}
