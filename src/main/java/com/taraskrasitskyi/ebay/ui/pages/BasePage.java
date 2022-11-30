package com.taraskrasitskyi.ebay.ui.pages;

import com.taraskrasitskyi.ebay.ui.elements.Header;

import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {

    public HomePage getHomePage() {
        $x("//a[@href='https://www.ebay.com/']").click();
        return new HomePage();
    }

    public Header getHeader() {
        return new Header();
    }
}
