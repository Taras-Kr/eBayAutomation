package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {

    @Step("CategoryPage: get page caption")
    public String getPageCaption() {
        return $x("//h1").getText();
    }

    @Step("CategoryPage: get text from last chain navigate link")
    public String getLastChainNavigateLinkText() {
        List<SelenideElement> navLinkElements = $$x("//a[@class='seo-breadcrumb-text']/span");
        if (navLinkElements.size() > 0) {
            return navLinkElements.get(navLinkElements.size() - 1).getText();
        } else {
            return "";
        }
    }

    @Step("CategoryPage: get title banner caption")
    public String getTitleBannerCaption() {
        return $x("//h1").getText();
    }
}
