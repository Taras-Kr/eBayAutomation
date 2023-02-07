package com.taraskrasitskyi.ebay.ui.elements;

import com.taraskrasitskyi.ebay.ui.elements.menus.UserAccountMenu;
import com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu.ShopByCategoryMenu;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.signin.EnterEmailOrUserNamePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header extends BasePage {

    private final String ENTER_EMAIL_OR_USER_NAME_LINK_XPATH = "//span[@class = 'gh-ug-guest']//a[text()='Sign in']";
    private final String USER_ACCOUNT_BUTTON_CSS_SELECTOR = "button#gh-ug";

    @Step("Header: Open 'Shop By Category' menu")
    public ShopByCategoryMenu openShopByCategoryMenu() {
        $x("//button[@id = 'gh-shop-a']").click();
        return new ShopByCategoryMenu();
    }

    public boolean isEnterEmailOrUserNameLinkIsDisplayed() {
        return $x(ENTER_EMAIL_OR_USER_NAME_LINK_XPATH).isDisplayed();
    }

    @Step("Header: Open the 'User name sign in' page")
    public EnterEmailOrUserNamePage openEnterEmailOrUserNamePage() {
        $x(ENTER_EMAIL_OR_USER_NAME_LINK_XPATH).click();
        return new EnterEmailOrUserNamePage();
    }

    @Step("Header: Check if user account button is displayed")
    public boolean isUserAccountButtonDisplayed() {
        return $(USER_ACCOUNT_BUTTON_CSS_SELECTOR).isDisplayed();
    }

    @Step("Header: Press user account button")
    public UserAccountMenu getUserAccountMenu() {
        $(USER_ACCOUNT_BUTTON_CSS_SELECTOR).click();
        return new UserAccountMenu();
    }
}
