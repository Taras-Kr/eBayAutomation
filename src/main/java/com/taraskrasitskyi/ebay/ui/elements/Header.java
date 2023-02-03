package com.taraskrasitskyi.ebay.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu.ShopByCategoryMenu;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.signin.UserNameSignInPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Header extends BasePage {

    @Step("Header: Open 'Shop By Category' menu")
    public ShopByCategoryMenu openShopByCategoryMenu() {
        $x("//button[@id = 'gh-shop-a']").click();
        return new ShopByCategoryMenu();
    }

    @Step("Header: Get 'Sign in' link")
    public SelenideElement getSignInLink(){
        return $x("//span[@class = 'gh-ug-guest']//a[text()='Sign in']");
    }

    @Step("Header: Open the 'User name sign in' page")
    public UserNameSignInPage openUserNameSignInPage(){
        getSignInLink().click();
        return new UserNameSignInPage();
    }
}
