package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.Selenide;
import com.taraskrasitskyi.ebay.ui.elements.TopMenu;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    @Step ("HomePage : open eBay home page")
    public HomePage open(){
        Selenide.open("https://www.ebay.com/");
        return this;
    }

    @Step("HomePage : get top menu")
    public TopMenu getTopMenu() {
        return new TopMenu();
    }

}