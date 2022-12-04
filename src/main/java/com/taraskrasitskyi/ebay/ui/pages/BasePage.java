package com.taraskrasitskyi.ebay.ui.pages;

import com.taraskrasitskyi.ebay.ui.elements.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {

    @Step("Get Home page")
    public HomePage getHomePage() {
        $x("//a[@href='https://www.ebay.com/']").click();
        return new HomePage();
    }

    @Step("Get Header")
    public Header getHeader() {
        return new Header();
    }

    @Step("Get page caption")
    public String getPageCaption(){
        return $x("//h1").getText();
    }
}
