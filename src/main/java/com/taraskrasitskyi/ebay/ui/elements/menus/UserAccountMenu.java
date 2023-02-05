package com.taraskrasitskyi.ebay.ui.elements.menus;

import com.taraskrasitskyi.ebay.ui.elements.Header;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class UserAccountMenu extends BasePage {

    @Step("UserAccountMenu: Sign out")
    public Header signOut(){
        $("li#gh-uo a").click();
        return new Header();
    }
}
